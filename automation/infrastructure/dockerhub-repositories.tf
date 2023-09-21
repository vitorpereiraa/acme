resource "dockerhub_repository" "acme" {
  name        = "acme"
  namespace   = "1191244"
  description = "Monolith application repository"
}

resource "dockerhub_repository" "productsC" {
  name        = "productsc"
  namespace   = "1191244"
  description = "Products Command Service"
}

resource "dockerhub_repository" "productsQ" {
  name        = "productsq"
  namespace   = "1191244"
  description = "Products Query Service"
}

resource "dockerhub_repository" "productsBootstrap" {
  name        = "productsbootstrap"
  namespace   = "1191244"
  description = "Products Bootstrap Service"
}

resource "dockerhub_repository" "reviewsC" {
  name        = "reviewsc"
  namespace   = "1191244"
  description = "Reviews Command Service"
}

resource "dockerhub_repository" "reviewsQ" {
  name        = "reviewsq"
  namespace   = "1191244"
  description = "Reviews Query Service"
}

resource "dockerhub_repository" "reviewsBootstrapper" {
  name        = "reviewsbootstrapper"
  namespace   = "1191244"
  description = "Reviews Bootstrap Service"
}

resource "dockerhub_repository" "votesC" {
  name        = "votesc"
  namespace   = "1191244"
  description = "Votes Command Service"
}

resource "dockerhub_repository" "votesQ" {
  name        = "votesq"
  namespace   = "1191244"
  description = "Votes Query Service"
}

resource "dockerhub_repository" "votesBootstrap" {
  name        = "votesbootstrap"
  namespace   = "1191244"
  description = "Votes Bootstrap Service"
}