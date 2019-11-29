### Mock web service solution

You can test a custom-configured http response by simply adding a clear .json file

Features:
* Spring Boot application
* Only **GET, POST, DELETE, PUT** http methods are supported
* Simple and clear web service configuration by .json files

Usage:
* Add a new web service configuration in application.properties ${endpoints.config.folder}
* Run Spring Boot application with `mvn spring-boot:run`
* Enjoy