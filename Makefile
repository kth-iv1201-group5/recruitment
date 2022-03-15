SHELL = /bin/sh
.SUFFIXES:

.DEFAULT_GOAL := start

start: existing-database.sql users.sql competence-translation.sql password-token.sql
	@type "docker" >/dev/null 2>&1 || { echo "Missing Docker"; exit 1; }
	@docker run -itdp 5432:5432 --rm \
		--volume=$(pwd):/data \
		--workdir=/data \
		--name=iv1201_db \
		-e POSTGRES_USERNAME=postgres \
		-e POSTGRES_PASSWORD=postgres \
		-e POSTGRES_DB="iv1201" \
		postgres:13-alpine
	@sleep 5s
	@docker exec -i iv1201_db psql -U postgres -d iv1201 < ./existing-database.sql
	@docker exec -i iv1201_db psql -U postgres -d iv1201 < ./users.sql
	@docker exec -i iv1201_db psql -U postgres -d iv1201 < ./competence-translation.sql
	@docker exec -i iv1201_db psql -U postgres -d iv1201 < ./password-token.sql
	@echo "Database is active"

run:
	@./mvnw clean install
	@./mvnw spring-boot:run
	@docker stop iv1201_db

stop:
	@docker stop iv1201_db
