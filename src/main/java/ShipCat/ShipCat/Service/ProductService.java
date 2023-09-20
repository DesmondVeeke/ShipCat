package ShipCat.ShipCat.Service;

import ShipCat.ShipCat.Interface.ProductRepository;
import ShipCat.ShipCat.Model.Product;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final Logger logger;

    @Autowired
    public ProductService(ProductRepository productRepository, Logger logger) {
        this.productRepository = productRepository;
        this.logger = logger;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product createProduct(Product product) {
        logger.info("Created: " + product.toString());
        return productRepository.save(product);
    }

    public Product updateProduct(Product updatedProduct) {
        Product existingProduct = productRepository.findById(updatedProduct.getId()).orElse(null);
        if (existingProduct != null) {
            existingProduct.setName(updatedProduct.getName());
            return productRepository.save(existingProduct);
        }
        logger.error("Could not find product associated with the sent updated product: " + updatedProduct);
        return null;
    }

    public boolean deleteProduct(Long id) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct != null) {
            productRepository.delete(existingProduct);
            return true;
        }
        logger.error("Could not delete product associated with the sent id: " + id + ". It was probably not found.");
        return false;
    }
}
