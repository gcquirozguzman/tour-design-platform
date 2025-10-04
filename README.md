<p align="center">
    <a href="https://angular.io/" target="_blank">
        <img src="https://upload.wikimedia.org/wikipedia/commons/c/cf/Angular_full_color_logo.svg" width=150 alt="Angular Logo">
    </a>
    +
    <a href="https://spring.io/projects/spring-boot" target="_blank">
        <img src="https://upload.wikimedia.org/wikipedia/commons/7/79/Spring_Boot.svg" width=130 alt="Spring Logo">
    </a>
</p>

<p align="center"><a href="https://www.google.com/" target="_blank">Demo Online</a></p>

# 🧩 Solution Architecture

<p align="center">
  <img src="./architecture/Diagrama%20Arquitectura%20Solucion%20v1.png" width="800" alt="Solution Architecture Diagram">
</p>

You can also find the editable version in [`architecture/Diagrama Arquitectura Solucion v1.drawio`](./architecture/Diagrama%20Arquitectura%20Solucion%20v1.drawio).

# Frontend - Angular

## How to run it locally

1. [Download](https://github.com/gcquirozguzman/tour-design-platform/archive/refs/heads/main.zip) or clone the [repository](https://github.com/gcquirozguzman/tour-design-platform) to your local machine:

```bash
$ git clone git@github.com:gcquirozguzman/tour-design-platform.git
```

2. Go to directory `cd frontend/tour-design-web` inside:
3. Run `npm install` inside the downloaded/cloned folder:

```bash
$ npm install
```

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Build to production serve

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI Overview and Command Reference](https://angular.io/cli) page.

# Backend - Spring Boot

## Requirements

For building and running the application you need:

- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven 4](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `payment_slip\backend\paymentSlip\src\main\java\com\osinergmin\paymentSlip\PaymentSlipApplication.java` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```
