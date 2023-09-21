PRODUCTSC_HOST=https://productsc-dev.salmonhill-ffb1f28b.westeurope.azurecontainerapps.io
PRODUCTSQ_HOST=https://productsq-dev.salmonhill-ffb1f28b.westeurope.azurecontainerapps.io
REVIEWSC_HOST=https://reviewsc-dev.salmonhill-ffb1f28b.westeurope.azurecontainerapps.io
REVIEWSQ_HOST=https://reviewsq-dev.salmonhill-ffb1f28b.westeurope.azurecontainerapps.io
VOTESC_HOST=https://votesc-dev.salmonhill-ffb1f28b.westeurope.azurecontainerapps.io
VOTESQ_HOST=https://votesq-dev.salmonhill-ffb1f28b.westeurope.azurecontainerapps.io

newman run acme-microservices-it.postman_collection.json -e dev.postman_environment.json