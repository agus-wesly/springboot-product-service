package com.productservice.demo.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.productservice.demo.ApiResponseData;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping()
	public ApiResponseData<List<Product>> getAllProducts() {
		List<Product> products = productService.getAll();
		return ApiResponseData.<List<Product>>builder().data(products).build();
	} 

	@GetMapping(
		path = "/{id}",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ApiResponseData<Product> getProductById(@PathVariable Long id) {
		var product = productService.getProductById(id);
		return ApiResponseData.<Product>builder().data(product).build();
	} 

	@GetMapping(
		path = "/search",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ApiResponseData<Product> getProductByName(@RequestParam String name) {
		var product = productService.getProductByName(name);
		return ApiResponseData.<Product>builder().data(product).build();
	} 

	@PostMapping(
		produces = MediaType.APPLICATION_JSON_VALUE,
		consumes = MediaType.APPLICATION_JSON_VALUE
	)
	public ApiResponseData<Product> createProduct(@Valid @RequestBody CreateProductRequest productRequest) {
		var newProduct = productService.create(productRequest);
		return ApiResponseData.<Product>builder().data(newProduct).build();
	}

	@PutMapping(
		path = "/{id}",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ApiResponseData<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody UpdateProductRequest productRequest) {

		var updatedProduct = productService.update(id, productRequest);
		return ApiResponseData.<Product>builder().data(updatedProduct).build();
	}

	@DeleteMapping(
		path = "/{id}",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ApiResponseData<String> deleteProduct(@PathVariable Long id) {
		productService.delete(id);
		return ApiResponseData.<String>builder().data("Successfully delete").build();
	}
}
