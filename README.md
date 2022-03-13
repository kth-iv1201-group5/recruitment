# Recruitment

> Apply for a job, get approved for the job. This is some random tagline that might need to change.

This project is tasked by the course IV1201, Design of Global Applications, at KTH, Stockholm, Sweden.  
The project is about creating a recruitment platform where client can apply for jobs and recruiter can read about the application.

## Installing / Getting started

This project is built on Spring boot together with Thymeleaf and PostgresSQL as the database. To get started you can run
these commands.

```shell
make start 
```
Or you can start the project yourself.
```shell
./mvnw clean install
./mvnw spring-boot:run
```

### If you have problem with database not connecting

If you have postgres problem it may be that the default parameter is not the same.

1. First download your example database from project description in Canvas.
2. Move the downloaded SQL file to the root of project.
3. Enter the command to start database in detached mode:
<!-- Use MinGW in windows -->

```shell
docker run -it -d -p 5432:5432 --rm --volume=$(pwd):/data --workdir=/data --name=iv1201_db \
-e POSTGRES_USERNAME=postgres \
-e POSTGRES_PASSWORD=postgres \
-e POSTGRES_DB="iv1201" \
postgres:13-alpine
```

5. In the same directory run this command to import database to docker database:

```shell
docker exec -i iv1201_db psql -U postgres -W postgres -d iv1201 < ./existing-database.sql
```

### Initial Configuration

This project makes use of some known environment variables that are used in the production which makes stuff easier.

```.env
PORT=8080
H2CONSOLE=true
DEBUG=true
JDBC_DATABASE_URL=postgresql://localhost:5432/iv1201
POSTGRES_USERNAME=postgres
POSTGRES_PASSWORD=postgres
SMTP_USER=example@gmail.com
SMTP_PASSWORD=example
```

It also good idea to start the project with `./mvnw clean install`

## Developing

For contributers you can clone down the repository and start working directly. Follow the issue stated in GitHub for
better view on what you can be working one.

```shell
git clone https://github.com/kth-iv1201-group5/recruitment.git
cd recruitment/
make start
```

Here again you should state what actually happens when the code above gets executed.

### Deploying / Publishing

This project is deployed at Heroku and uses the addon for PostgresSQL. The environment variables below are added in your
GitHub repository to be able to use the actions happening.

```env
HEROKU_API_KEY=GET-FROM-HEROKU
HEROKU_APP_NAME=GET-FROM-HEROKU
HEROKU_EMAIL=GET-FROM-HEROKU
SMTP_USER=example@gmail.com
SMTP_PASSWORD=example
```

## Features

This project will fulfill these functionalities:

* User authentication.
* User registration.
* Applicant applications list.
* Applicants application profile.
* Requesting new password.

## Contributing

<img src="https://avatars.githubusercontent.com/u/25460850?v=4" alt="Arif Jedhda-Oh" width="200px">
<img src="https://avatars.githubusercontent.com/u/25460850?v=4" alt="Arif Jedhda-Oh" width="200px">
<img src="https://avatars.githubusercontent.com/u/25460850?v=4" alt="Arif Jedhda-Oh" width="200px">
If you'd like to contribute, please fork the repository and use a feature branch. Pull requests are warmly welcome.

<!-- If there's anything else the developer needs to know (e.g. the code style
guide), you should link it here. If there's a lot of things to take into
consideration, it is common to separate this section to its own file called
`CONTRIBUTING.md` (or similar). If so, you should say that it exists here. -->

## Links

- Project homepage: https://recruitment-group5.herokuapp.com
- Repository: https://github.com/kth-iv1201-group5/recruitment
- Issue tracker: https://github.com/kth-iv1201-group5/recruitment/issues
  - In case of sensitive bugs like security vulnerabilities, please contact my@email.com directly instead of using issue
    tracker. We value your effort to improve the security and privacy of this project!

## Licensing

The code in this project is licensed under MIT license.
