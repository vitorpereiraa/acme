up:
	docker compose up --build --attach app

down:
	docker compose down

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