# Currency-Calculator

Currency calculator exercise for Kambu. Junior Java Developer.

* Swagger ui available under http://localhost:8080/swagger-ui/#/
* Docker instructions #
    * clone the repository
    * open the repository path where docker-compose.yaml is, in terminal
    * type command: docker-compose up
    * enjoy!

* Nginx and testing the routing - for test purposes the delay is applied  only on one endpoint
* how to:
  * run the container: docker-compose up,
  * in postman open two tabs
  * in the first one under "Headers" tab add key "delay" and value "30000"
  * send the get request to the following address : localhost:8080/currencies
  * now you have one of three applications sleeping for 30 seconds as under this endpoint i used Thread.sleep(value)
  * then just start sending requests to the same address from the second tab in postman
  * you should see in intellij's console System.out.printlns saying "this is app 2" and "this is app 3" until the 30 seconds delay is over on app 1.




Functionalities:

* loading currencies from NBP API and saving as objects in SQL DB on startup and with @Schedule i reload new currencies every day at 1pm
* REST:
    * returning list of all currency objects 
    * returning calculation object and saving it in DB 
    * returning list of all calculations made
    * deleting calculation object on id from DB
    * returning list of currency objects by given code
    * all operations are being saved with details as objects in DB


* WEB: (currently works only when project is started from intellij SpringBootApplication file)
    * home page with all the currencies in table
    * calculator where user can input the money to convert and currencies from -> to
    * calculations are stored in DB accessible from another jsp view

Technologies used:

* Spring Boot
* JPARepository
* Hibernate
* SQL Database
* REST
* JSON
* Lombok
* jsp
* CSS
* Swagger ui
* Awaitility
* Docker
* nginx - load balancer
