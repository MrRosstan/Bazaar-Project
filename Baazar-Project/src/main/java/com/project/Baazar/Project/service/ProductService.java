
package com.project.Baazar.Project.service;

import com.project.Baazar.Project.dto.GetProductDTO;
import com.project.Baazar.Project.dto.ProductBySellDTO;
import com.project.Baazar.Project.dto.ProductBySellDTOAddOn;
import com.project.Baazar.Project.model.Product;
import com.project.Baazar.Project.model.Sell;
import com.project.Baazar.Project.repositoryy.IProductRepository;
import com.project.Baazar.Project.repositoryy.ISellRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {

    @Autowired 
    private IProductRepository proRep;
    @Autowired
    private ISellRepository sellRep;
    
    @Override
    public void saveProduct(Product pro) {
        proRep.save(pro);
    }

    @Override
    public List<GetProductDTO> getAllProduct() {
        
        // Initialize a list to store DTOs for all products
        List<GetProductDTO> allProDTOList = new ArrayList<>();
        // Fetch all products from the repository
        List<Product> productList = proRep.findAll();
        // Iterate over each product in the product list
        
        for ( Product eachProduct : productList){
            // Create a new DTO for the current product
             GetProductDTO allProDTO = new GetProductDTO();
             
            // Set the DTO fields with the product's attributes         
            allProDTO.setId_product(eachProduct.getId_product());
            allProDTO.setName_product(eachProduct.getName_product());
            allProDTO.setBrand_product(eachProduct.getBrand_product());
            allProDTO.setCost_product(eachProduct.getCost_product());
            allProDTO.setQuantity_product(eachProduct.getQuantity_product());
            
            // Add the populated DTO to the list of DTOs
            allProDTOList.add(allProDTO);
        }
        // Return the list of all product DTOs
        return allProDTOList;
        
    }

    @Override
    public GetProductDTO getOneProduct(Long id_product) {
        // Getting one product from the data base
        Product oneProduct = proRep.findById(id_product).orElse(null); 
        
        // Check if the product was found
        if (oneProduct == null) {
            throw new RuntimeException("Product not found: " + id_product);
        }
        
        // Create a DTO to hold product details
        GetProductDTO getProdDTO = new GetProductDTO();
        
        // Populate the DTO with product information
        getProdDTO.setId_product(oneProduct.getId_product());
        getProdDTO.setName_product(oneProduct.getName_product());
        getProdDTO.setBrand_product(oneProduct.getBrand_product());
        getProdDTO.setCost_product(oneProduct.getCost_product());
        getProdDTO.setQuantity_product(oneProduct.getQuantity_product());
        
        // Return the populated DTO
        return getProdDTO;
    }

    @Override
    public void deleteProduct(Long id_product) {
        // Delete a product in the data base
        proRep.deleteById(id_product);
    }

    @Override
    public void editProduct(Product pro) {
        // Edit a product in the data base
        this.saveProduct(pro);
    }

    @Override
    public List<Product> getLessThan5Product() {
        
        // Initialize a list to store products with low stock (quantity <= 5)
        List<Product> lowStockProductList = new ArrayList<Product>();
        // Fetch all products from the repository
        List<Product> allProducts = proRep.findAll();
        
        // Iterate over each product in the list of all products
        for(Product prod:  allProducts){
            // Check if the product quantity is less than or equal to 5
            if ( prod.getQuantity_product()<= 5){
                // Add the product to the low stock list
                lowStockProductList.add(prod);
            }
        }
        // Return the list of products with low stock
        return lowStockProductList;
    }

    @Override
    public ProductBySellDTO getListProductFromSell(Long id_sell) {
        
       // Fetch the Sell entity using its ID
        Sell sell = sellRep.findById(id_sell).orElseThrow((null));

        // Create a new DTO and populate it with the data
        ProductBySellDTO listOfProductsSell = new ProductBySellDTO();
        
        // Create a list were is going to be adding the products
        List<ProductBySellDTOAddOn> productList = new ArrayList<>();
        
        // Create a for loop were its going to retrieve the data from the list of products asociated with that sell in the "product" object
        for (Product product : sell.getProductList() ){
            // Create the product object where is going to be added the data for each product.
            ProductBySellDTOAddOn prodBySellSettings =new ProductBySellDTOAddOn();
            
            prodBySellSettings.setName_product(product.getName_product());
            prodBySellSettings.setBrand(product.getBrand_product());
            prodBySellSettings.setCost(product.getCost_product());
            
            // Store that product object in the product list.
            productList.add(prodBySellSettings);
        }
        
        // Setting the data into de DTO
        listOfProductsSell.setId_sell(id_sell);
        listOfProductsSell.setProductInfoList(productList);
        
        return listOfProductsSell;

        
        
        
        
    }
    
}
