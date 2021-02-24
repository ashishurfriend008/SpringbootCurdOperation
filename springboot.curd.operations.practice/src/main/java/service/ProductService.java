package service;

import model.Product;

import java.util.List;

public interface ProductService {

    Product createProduct(Product product);
    Product updateProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(long productId);
    void deleteProduct(long id);
}
