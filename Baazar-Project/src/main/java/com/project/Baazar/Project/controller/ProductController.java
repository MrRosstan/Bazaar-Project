
package com.project.Baazar.Project.controller;

import com.project.Baazar.Project.dto.GetProductDTO;
import com.project.Baazar.Project.dto.ProductBySellDTO;
import com.project.Baazar.Project.model.Product;
import com.project.Baazar.Project.service.IProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ProductController {

    @Autowired 
    private IProductService proServ;
    
    // Save product
    @PostMapping("/product/create")
    public String creatProduct(@RequestBody Product pro){
        proServ.saveProduct(pro);
        return "The Product has being created propertly";
    }
    
    // Get all products
    @GetMapping("/product/find/all")
    public List<GetProductDTO> getAllProduct(){
        return proServ.getAllProduct();
    }
    
    // Find an specific product
    @GetMapping("/product/find/{id_product}")
    public GetProductDTO getOneProduct(@PathVariable Long id_product){
        return proServ.getOneProduct(id_product);
    }
    
    // Delete a product
    @DeleteMapping("/product/delete/{id_product}")
    public String deleteProduct(@PathVariable Long id_product){
        proServ.deleteProduct(id_product);
        return "The product has being deleted";
    }
    
    // Edit a product
    @PutMapping("/product/edit")
    public GetProductDTO  editProduct(@RequestBody Product pro){
        proServ.editProduct(pro);
        return this.getOneProduct(pro.getId_product());
    }
    
    // Get products that are less or equal to 5
    @GetMapping("/product/lowStock")
    public List<Product> getLessThan5Product(){
        return proServ.getLessThan5Product();
    }

    // Get list of products from a sell
    @GetMapping("/product/fromSell/{id_sell}")
    public ProductBySellDTO getListProductBySell(@PathVariable Long id_sell){
        return proServ.getListProductFromSell(id_sell);
    }
}
