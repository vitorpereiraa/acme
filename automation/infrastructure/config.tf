terraform {
  required_providers {
    cloudamqp = {
      source  = "cloudamqp/cloudamqp"
      version = "1.24.0"
    }
    azurerm = {
      source  = "hashicorp/azurerm"
      version = "3.46.0"
    }
    dockerhub = {
      source  = "BarnabyShearer/dockerhub"
      version = "0.0.15"
    }
  }
}
