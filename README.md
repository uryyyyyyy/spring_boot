spring_boot
===========

# check spring MVC 201404

create simple SpringBoot's web content with Eclipse and gradle.

referd URL:
http://spring.io/guides/gs/serving-web-content/
etc..

## Environment Settings

1. import STS and GradleIDE(Eclipse plugin)

- download gradle(local machine)

- set path to gradle/bin

## Create New Project

### Terminal

1. create new Folder and build.gradle file

- execute [mkdir -p src/main/java/hello] and [mkdir -p src/main/resources/templates]

- refer to the URL, set build.gradle -- import spring_boot and some libraries

- execute [gradle wrapper] -- create wrapper script use everywhere

- execute [./gradlew eclipse] -- .project file will create

### Eclipse

1. create GreetingController.java, Application.java, greeting.html -- (Ctrl + Shift + O) is useful

- Run as springBoot -- or execute [./gradlew bootRun] in terminal

- if Spring AA are shown, you may success. check http://localhost:8080/greeting


## some feature

### DB(H2) access with springJDBC

### enable transaction

### file download / upload

### use thymeLeaf template

### use static files(like css, image)


## Spring4 structure

