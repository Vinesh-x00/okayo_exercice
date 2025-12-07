package fr.okayo.exercice.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
@Tag(name="Product", description = "Endpoints for creating creating and retrieving products")
public class ProductController {

    private final ProductService productService;

    @Operation(
            summary = "get product by id"
    )
    @GetMapping("/{id}")
    public Product findById(
            @Parameter(description = "uuid of product") @NotNull @PathVariable UUID id) {
        return productService.findById(id);
    }
    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    @Operation(summary = "Create new product")
    @PostMapping
    public Product createProduct(
            @Parameter(description = "product information's")
            @NotNull @RequestBody ProductDTO dto) {
        return productService.createProduct(dto);
    }
}
