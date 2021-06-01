# Currency-Calculator
Currency calculator exercise for Kanbu. Junior Java Developer.

Functionalities:
* loading currencies from NBP API and saving as objects in SQL DB on startup,
* REST:
  * returning calculation object as json in Get Request :  http://localhost:8080/currencies/{moneyInput}/{codeFrom}/{codeTo}
  * returning list of all currency objects as json in Get Request : http://localhost:8080/currencies
  * returning currency object as json by given id in Get Request : http://localhost:8080/currencies/{id}
  * returning list of currency objects by given code in Get Request : http://localhost:8080/currencies/rates/{code}&{code}&...
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
