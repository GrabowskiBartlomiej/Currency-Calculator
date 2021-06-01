# Currency-Calculator
Currency calculator exercise for Kambu. Junior Java Developer.

Functionalities:
* loading currencies from NBP API and saving as objects in SQL DB on startup,
* REST:
  * returning list of all currency objects as json in GET Request : http://localhost:8080/currencies
  * returning calculation object as json in POST Request and saving it in DB :  http://localhost:8080/currencies/{moneyInput}/{codeFrom}/{codeTo}
  * returning list of all calculations made as json in GET : http://localhost:8080/currencies/calculations
  * deleting calculation object on id in DELETE Request also removing it from DB : http://localhost:8080/currencies/calculations/{id}
  * returning list of currency objects by given code in GET Request : http://localhost:8080/currencies/rates/{code}&{code}&...
  * all operations are being saved with details as objects in DB and reachable from GET Request : http://localhost:8080/currencies/service-calls
  
* WEB:
  * home page with all the currencies in table
  * calculator where user can input the money to convert and currencies from -> to
  * calculations are stored in DB accessible from another jsp view
  * about page



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
