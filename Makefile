up-mongo:
	docker compose -f ./docker-compose-mongodb.yml up --build --attach app

down-mongo:
	docker compose -f ./docker-compose-mongodb.yml down 

up-postgres:
	docker compose -f ./docker-compose-postgres.yml up --build --attach app
	
down-postgres:
	docker compose -f ./docker-compose-postgres.yml down 

run:
	mvn spring-boot:run

dr: docker-clean docker-build
	docker run -p 8080:8080 --name acme docker.io/1191244/arqsoft/acme

docker-build: 
	docker build -t 1191244/arqsoft/acme .	

docker-clean: 
	docker container stop acme || true
	docker container rm acme || true

cicd:
	dagger run go run infrastructure/cicd/dagger.go