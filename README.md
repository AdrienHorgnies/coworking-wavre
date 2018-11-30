# coworking-wavre
> For a business view of this project, please consult the [wiki] of this Github repository.

Responsive web application to manage renting of coworking spaces at Wavre.
Conducted as part of the "Integration and Development Project" and "E-Business" courses at IFOSUP Wavre, academic year 2018-19.

## Stakeholders
### Lecturers
- Stéphane Hughier: "Integration and Development Project"
- Xavier Jeunejean: "E-Business"

### Contributors
- Adrien Horgnies: technical & project lead
- Stéphane Thiry: back-end, functional analysis & business analysis
- Phirum Chan: front-end, database architect & business analysis
- Katia Baran: front-end, ui designer & business analysis
- Steve Leveau: business analysis

## Front end with Angular

The front end has been built upon the javascript framework Angular.

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 6.1.1.

### Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

### Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

### Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `--prod` flag for a production build.

### Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

### Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).

### Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).

## Back end with Spring Boot

The back end use the java framework Spring Boot.

### Testing

To launch your application's tests, run:

    mvn clean test

### Using docker to simplify development (optional)

You can use Docker to improve your development experience. A docker-compose configuration is available in the [src/main/docker](src/main/docker) folder to launch required third party services.
For example, to start a mysql database in a docker container, run:

    docker-compose -f src/main/docker/docker-compose.yml up -d mysql-coworking

To stop it: 

    docker-compose -f src/main/docker/docker-compose.yml stop mysql-coworking

and remove the container, run:

    docker-compose -f src/main/docker/docker-compose.yml kill mysql-coworking

### Running the application

After the database has boot up, you can start the spring-boot application:

    mvn spring-boot:run

[wiki]: https://github.com/AdrienHorgnies/coworking-wavres/wiki
