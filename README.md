# springboot-jpa-rest-jms-angular
springboot-jpa-rest-jms-angular

# Instructions 
1- Run the Application.java
2- Access the http://localhost:8080 for the Angularjs interface
3- Rest endpoints:
   * http://localhost:8080/commodities/all - List all commodities
   * http://localhost:8080/commodities/cod/{cod} - Search by commodity code
   * http://localhost:8080/commodities/save - Save a commodity
      -> Json example by post -> {"id":1,"cod":"BRA-098","value":400,"description":"Celular Sang"}
   * http://localhost:8080/invoices/all - List all invoices
   * http://localhost:8080/invoices/cod/{cod} - Search by invoice code
   * http://localhost:8080/invoices/emitter/{emitter} - Search by invoice emitter
   * http://localhost:8080/invoices/commodities/{cod} - Search an invoice by the commodity code
   * http://localhost:8080/invoices/save - Save an invoice
      -> Json example by post -> {"id":1,"cod":"003","emitter":"Mercado","description":"NF-10","commodities":[{"id":2,"cod":"BRA-055","value":295.50,"description":"Cadeira James"}]}
   * http://localhost:8080/invoices/create_data - Create a data mass test
   
