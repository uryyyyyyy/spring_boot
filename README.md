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


## some feature (Japanese)

### DB(H2) access with springJDBC
build.gradleファイルに、H2のライブラリを取ってくる処理を書いているのでeclipseから見れるはずです。
H2をserverとしてRunさせておいて、Application#createDataSourceの設定を確認してください。
同じように設定されていればアクセスできます。

SpringではJDBCTemplateというものが用意されていて、これを使うとConnectionとかしなくてもSQLを発行できます。

### enable transaction
トランザクション処理をさせたいメソッドに@Transactionをつけると例外時にはCallBackしてくれるようです。

### file download / upload
Application#multipartConfigElementでファイルサイズなどの設定をしておいて、UploadされたファイルをMultipartFileで受け取ると処理できます。
ダウンロードはサーブレットの仕組みそのままにHttpServletResponseにgetOutputStream()でいけるようです。

### use thymeLeaf template
これもbuildファイルに記述されているので普通に使えるようになresources/templatesっています。
src/main/resources/templates配下にhtmlファイルを置いておくとControllerから名前だけで判別してくれるようです。
変数の設定の仕方は、ControllerからModelにsetAttributeすればその変数を利用できます。もちろんListやオブジェクトも扱えます。
細かい仕様は公式ページをどうぞ。

### use static files(like css, image)
src/main/webapp/配下に置いたものはそのまま利用できるようです。
今回の例では、webapp/tmp/* には、localhost:8080/tmp/*でアクセスできるようです。


## Spring4 structure

