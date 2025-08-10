************ 1st version *****************

here just added the spring boot ready made application 
no changed done in the project 




**************** 2nd version ***************

first added sql configuration properties in the application properties 

now i created 4 folders in the demo folder that are 
model -> Products
Controller -> ProductController
Service -> ProductService
Repository -> ProductRepository

3 layer architecture 
now ProductRepository is interface just exteded the JpaRepository class
now i created ProductService.java file in this dependecy injection is done from the ProductRepository
now i created a ProductController.java class in that dependecy injection is done from the ProductService
now using api's we can fetch the data to the frontend 

in model folder i created a Products class in that database entity is created with the data 


data flow 
 database -> repository layes -> service layer -> controller layer -> frontend 


