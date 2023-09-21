resource "azurerm_resource_group" "acme-rg" {
  name     = "acme-rg-dev"
  location = "West Europe"
}

resource "azurerm_log_analytics_workspace" "acme-log-analytics" {
  name                = "acme-analytics-dev"
  location            = azurerm_resource_group.acme-rg.location
  resource_group_name = azurerm_resource_group.acme-rg.name
}

resource "azurerm_container_app_environment" "acme-app-env" {
  name                       = "acme-app-dev-env"
  location                   = azurerm_resource_group.acme-rg.location
  resource_group_name        = azurerm_resource_group.acme-rg.name
  log_analytics_workspace_id = azurerm_log_analytics_workspace.acme-log-analytics.id
}