spring_boot
===========

# check spring MVC 201404

create simple SpringBoot's web content with Eclipse and gradle.

referd URL:
http://spring.io/guides/gs/serving-web-content/
etc..

## Environment Settings

### my own environment

- Ubuntu13.10(VirtualBox)
- gradle 1.11
- eclipse 4.3
- thymeleaf(template engine)
- refered http://spring.io/guides/gs/serving-web-content/
	http://spring.io/guides/gs/relational-data-access/
	http://spring.io/guides/tutorials/web/

1. import STS and GradleIDE(Eclipse plugin)

2. download gradle(local machine)

3. set path to gradle/bin

## Create New Project

### Terminal

1. create new Folder and build.gradle file

2. execute [mkdir -p src/main/java/hello] and [mkdir -p src/main/resources/templates]

3. refer to the URL, set build.gradle -- import spring_boot and some libraries

4. execute [gradle wrapper] -- create wrapper script use everywhere

5. execute [./gradlew eclipse] -- .project file will create

### Eclipse

1. create GreetingController.java, Application.java, greeting.html -- (Ctrl + Shift + O) is useful

2. Run as springBoot -- or execute [./gradlew bootRun] in terminal

3. if Spring AA are shown, you may success. check http://localhost:8080/greeting


## some feature

### DB(H2) access with springJDBC

### enable transaction

### file download / upload

### use thymeLeaf template

### use static files(like css, image)


## Spring4 structure

