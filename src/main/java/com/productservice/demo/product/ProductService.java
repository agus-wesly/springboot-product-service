package com.productservice.demo.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.transaction.annotation.Transactional;


@Service
class ProductService {

    @Autowired
    ProductRepository productRepository;

	@Transactional(readOnly = true)
    public List<Product> getAll() {
		var products =  productRepository.findAll();
        return products;
    }

    public Product getProductById(Long id) {
		var product = productRepository.findById(id)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

    return product;
    }

    public Product getProductByName(String name) {
		var product = productRepository.findFirstByName(name)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

    return product;
    }

    public Product create(CreateProductRequest productRequest) {
		var product = new Product();
		product.setName(productRequest.getName());
		product.setPrice(productRequest.getPrice());
		var newProduct = productRepository.save(product);
        return newProduct;
    }

    public Product update(Long id, UpdateProductRequest productRequest) {
		var existingProduct = productRepository.findById(id)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

		if (productRequest.getName() != null) {
			existingProduct.setName(productRequest.getName());
		}
		if (productRequest.getPrice() != null) {
			existingProduct.setPrice(productRequest.getPrice());
		}

		var updatedProduct = productRepository.save(existingProduct);
        return updatedProduct;
    }

    public void delete(Long id) {
		var existingProduct = productRepository.findById(id)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

		productRepository.delete(existingProduct);
    }
}