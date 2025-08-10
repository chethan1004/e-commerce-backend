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

in this we just added get method and i created an Rest API theta will fetch the data from the controller layer to frontend



********************** 3rd version *********************




now i will add get methods to fetch the details of the single product 

and created a method called getProduct method that takes two parameters @PathVariable and Long id 

and in service layer also i created a method called 
public Product getproductById(Long id){
        return repo.findById(id).orElse(new Product());
    }
it will fetch the data from the Repository layer 




******************* 4th version **************************

sometimes it is better to send the status code also because if something went wrong then by checking the status code we can analyse what is the problem and where we have t debug 

in this we updated the 

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.FOUND);
    }

it will send the data and at the same time it will send 302 status code 
302 -> FOUND

OK -> 200
NOT_FOUND -> 404 // famous error 

from now onwards we should send the data along with status code 
