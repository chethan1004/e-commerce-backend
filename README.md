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


**************** 5th version ********************

here we implemented Post method in the controller layer to accept the data from the front end 
ow it will send to the service layer and saying save in the database 

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestBody Product product){
        try {
            
            Product product1 = service.addProduct(product);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

and in ther service layer

    public Product addProduct(Product product) {
        return repo.save(product);
    }

now in the postman i choose Post method and used url 
localhost:8080/api/product

and in the body -> raw -> json at the end of taht line 

{
    "name": "Catberry",
    "description": "Comes in box",
    "price": 30.0,
    "quantity": 100
}
just added this and enter 
here no need of adding the id because it will create id itself 


and implemented two more methods put(update) and delete method that will update and delete the elements in the database 
code for update and delete in the controller layer is 


    @PutMapping("/products")
    public void updateproduct(@RequestBody Product id){
        service.updateproduct(id);

    }


    @DeleteMapping("/products/{id}")
    public void deleteproduct(@PathVariable Long id) {
        service.deleteproduct(id);
    }

and code in the service layer is 

    public void updateproduct(Product id) {
        repo.save(id);
    }

    public void deleteproduct(Long id) {
        repo.deleteById(id);
    }


here the end of all the crud operatuions 

create , read , update , delete 


******************* 6th version ******************

implemented search option also now we can search any element by there variables except id 

code for that is in the repo , service , controller is 

@Query("SELECT p FROM Product p WHERE " +
           "(:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
           "(:description IS NULL OR LOWER(p.description) LIKE LOWER(CONCAT('%', :description, '%'))) AND " +
           "(:price IS NULL OR p.price = :price) AND " +
           "(:quantity IS NULL OR p.quantity = :quantity)")
    List<Product> searchProducts(@Param("name") String name,
                                 @Param("description") String description,
                                 @Param("price") Double price,
                                 @Param("quantity") Integer quantity);


in service layer is 

    public List<Product> searchProducts(String name, String description, Double price, Integer quantity) {
        return repo.searchProducts(name, description, price, quantity);
    }


in controller layer is 

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) Integer quantity) {

        List<Product> results = service.searchProducts(name, description, price, quantity);
        return ResponseEntity.ok(results);
    }


****************** 7th version ************