terraform {
  required_providers {
    azurerm = {
      source = "hashicorp/azurerm"
      version = "3.75.0"
    }

    kubernetes = {
      source = "hashicorp/kubernetes"
      version = "2.23.0"
    }
  }
}

provider "azurerm" {
  features {}
}

locals {
  project_name = "acme"
  location = "West Europe"
  image = "docker.io/1191244/arqsoft-acme:dev"
}

resource "azurerm_resource_group" "rg" {
  name = local.project_name
  location = local.location
}

resource "azurerm_kubernetes_cluster" "aks" {
  name = "${local.project_name}-aks"
  location = local.location
  resource_group_name = azurerm_resource_group.rg.name
  dns_prefix = local.project_name
  sku_tier  = "Free"

  default_node_pool {
    name       = "default"
    node_count = 1
    vm_size    = "standard_b2s"
  }

  identity {
    type = "SystemAssigned"
  }
}

provider "kubernetes" {
  host                   = azurerm_kubernetes_cluster.aks.kube_config.0.host
  username               = azurerm_kubernetes_cluster.aks.kube_config.0.username
  password               = azurerm_kubernetes_cluster.aks.kube_config.0.password
  client_certificate     = base64decode(azurerm_kubernetes_cluster.aks.kube_config.0.client_certificate)
  client_key             = base64decode(azurerm_kubernetes_cluster.aks.kube_config.0.client_key)
  cluster_ca_certificate = base64decode(azurerm_kubernetes_cluster.aks.kube_config.0.cluster_ca_certificate)
}

resource "kubernetes_namespace" "ns" {
  metadata {
    name = local.project_name
  }
}

resource "kubernetes_deployment_v1" "deployment" {
  metadata {
    name = local.project_name
    labels = {
      test = local.project_name
    }
  }

  spec {
    replicas = 1

    selector {
      match_labels = {
        test = local.project_name
      }
    }

    template {
      metadata {
        labels = {
          test = local.project_name
        }
      }

      spec {
        container {
          image = local.image
          name  = local.project_name

          port {
            container_port = 8080
          }

          resources {
            limits = {
              cpu    = "0.5"
              memory = "512Mi"
            }
            requests = {
              cpu    = "250m"
              memory = "50Mi"
            }
          }
        }
      }
    }
  }
}

resource "kubernetes_service" "service" {
  metadata {
    name = local.project_name
  }
  spec {
    selector = {
      app = local.project_name
    }
    port {
      port        = 80
      target_port = 8080
    }
    type = "LoadBalancer"
    session_affinity = "ClientIP"
  }
}