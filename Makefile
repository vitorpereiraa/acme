default: dr

run:
	./mvnw spring-boot:run

dr: docker-clean docker-build
	docker run -p 8080:8080 --name acme docker.io/1191244/arqsoft/acme

docker-build: 
	DOCKER_BUILDKIT=1 docker build -t 1191244/arqsoft/acme .	

docker-clean: 
	docker container stop acme || true
	docker container rm acme || true
