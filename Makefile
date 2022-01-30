PORT := 8080

help:
	@echo "Make commands."
	@echo "  build\tBuild docker image for maven project."
	@echo "  run\tRun the docker image from docker image created using 'make build' specify 'PORT' argument."
	@echo "  compose\tDocker Compose the project with PostgreSQL."
	@echo "  import_db\tImport the database to project."
	@echo "  access_db\tAccess docker image, PostgreSQL."

build: Dockerfile pom.xml src/
	@echo "Creating docker image."
	@docker build -f Dockerfile -t kth-iv1201-recruitment --rm .
	@echo "Docker image created."

run: Dockerfile
	@echo "Run detached docker image of application to port $(PORT)."
	@docker run -it --rm -e PORT=$(PORT) -p $(PORT):$(PORT) -d kth-iv1201-recruitment:latest
	@echo "Docker image is running!"

compose: docker-compose.yml
	@docker-compose up --build

import_db: existing-database.sql
	@docker container exec -i $(docker-compose ps -q db) psql -U postgres postgres < existing-database.sql

access_db:
	@docker-compose exec db psql -U postgres
