package service;

import exception.ResourceNotFoundException;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ProductRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Optional<Product> optionalProduct = productRepository.findById(product.getId());

        if(optionalProduct.isPresent()){
            Product updateProduct = optionalProduct.get();
            updateProduct.setId(product.getId());
            updateProduct.setName(product.getName());
            updateProduct.setDescription(product.getDescription());
            productRepository.save(updateProduct);
            return updateProduct;
        }else{
            throw new ResourceNotFoundException("Resource not found with id " +product.getId());
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public Product getProductById(long productId) {
        Optional<Product> product = this.productRepository.findById(productId);

        if(product.isPresent()){
            return product.get();
        }else {
            throw new ResourceNotFoundException("Resource not found with id" + productId);
        }
    }

    @Override
    public void deleteProduct(long productId) {
        Optional<Product> product = productRepository.findById(productId);

        if(product.isPresent()){
            this.productRepository.delete(product.get());
        }else{
            throw new ResourceNotFoundException("Resource not found with id" +productId);
        }
    }
}
