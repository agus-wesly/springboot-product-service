package com.productservice.demo.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {
    
    @Size(max = 100)
    @NotBlank(message = "Name should not be empty")
    private String name;

    @NotNull(message = "Price should not be empty")
    private Long price;
}
