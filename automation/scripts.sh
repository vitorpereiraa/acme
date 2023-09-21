docker run -d --hostname my-rabbit --name some-rabbit -p 15672:15672 -p 5672:5672 rabbitmq:3-management

h2-console:
  url: jdbc:h2:file:./ProductsC/data/acme;MV_STORE=FALSE;AUTO_SERVER=true;

# API Manager
# Publisher Portal: localhost:9443/publisher credentials: admin/admin
# Dev Portal: localhost:9443/devportal credentials: admin/admin
docker run -it -p 9443:9443 -p 8243:8243 -p 8280:8280 docker.wso2.com/wso2am

# Micro Integrator
docker run -it -p 8290:8290 -p 8253:8253 -p 9164:9164 --name micro-integrator wso2/wso2mi:4.0.0


# Docker Hub token
username:1191244
password:dckr_pat_zWKoW9s_VBdQ-22WqMF9GYMTGVw

