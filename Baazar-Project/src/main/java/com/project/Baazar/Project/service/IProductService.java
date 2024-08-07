
package com.project.Baazar.Project.service;

import com.project.Baazar.Project.dto.GetProductDTO;
import com.project.Baazar.Project.dto.ProductBySellDTO;
import com.project.Baazar.Project.model.Product;
import java.util.List;


public interface IProductService {
    
    // Create a product
    public void saveProduct(Product pro);
    
    // Bring all the products
    public List<GetProductDTO> getAllProduct();
    
    // Bring one product
    public GetProductDTO getOneProduct(Long id_product);
    
    // Delete a product
    public void deleteProduct(Long id_product);
    
    // Edit a product
    public void editProduct(Product pro);
    
    // Get the products that are less or equal to 5
    public List<Product> getLessThan5Product();
    
    // Get the list of products from a corresponding sell 
    public ProductBySellDTO getListProductFromSell(Long id_sell);
    
}
