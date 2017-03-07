# springboot-jpa-rest-jms-angular
springboot-jpa-rest-jms-angular

# Instructions 
1- Run the Application.java
#
2 - Access "http://localhost:8080/invoices/create_data" for Rest endpoint starts a JMS queue that creates the mass of test data.
#
3 - Access "http://localhost:8080" for uses the Angularjs interface.
#
4 - Rest endpoints:

   * "http://localhost:8080/commodities/all" - List all commodities
   
   * "http://localhost:8080/commodities/cod/{cod}" - Search by commodity code
   
   * "http://localhost:8080/commodities/save" - Save a commodity
   
      -> Json example by post -> {"id":1,"cod":"BRA-098","value":400,"description":"Celular Sang"}
      
      
   * "http://localhost:8080/invoices/all" - List all invoices
   
   * "http://localhost:8080/invoices/cod/{cod}" - Search by invoice code
   
   * "http://localhost:8080/invoices/emitter/{emitter}" - Search by invoice emitter
   
   * "http://localhost:8080/invoices/commodities/{cod}" - Search an invoice by the commodity code
   
   * "http://localhost:8080/invoices/save" - Save an invoice
   
      -> Json example by post -> {"id":1,"cod":"003","emitter":"Mercado","description":"NF-10","commodities":[{"id":2,"cod":"BRA-055","value":295.50,"description":"Cadeira James"}]}
      
   * "http://localhost:8080/invoices/create_data" - Create a data mass test
#
5 - This file "SoapUI 5.3.0 Test Rest EndPoints.xml" on the project root, contains the Rest endpoints tests used on the SoapUI 5.3.0.
#
6 - Queues from ActiveMQ executed on the embedded VM:

   * "queue_invoice"
   
   * "queue_commodity"
#
7 - Access "http://localhost:8080/h2-console" for H2 embedded DataBase
  
  * JDBC URL = jdbc:h2:mem:challenge
  
  * USER = sa
  
  * PASSWORD = 
