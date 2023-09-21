resource "cloudamqp_instance" "acme-mb" {
  name = "acme-mb"
  plan = "lemur"
  region = "azure-arm::westeurope"
}