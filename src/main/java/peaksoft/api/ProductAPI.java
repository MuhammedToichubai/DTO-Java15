package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.ProductRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.enums.Category;
import peaksoft.service.ProductService;


@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductAPI {
    private final ProductService productService;

    @PostMapping
    public SimpleResponse save(@RequestParam Category category,
                               @RequestBody ProductRequest request) {

        return productService.save(category, request);
    }

    @GetMapping("/{userId}/{productId}")
    public SimpleResponse addOrRemoveToFavorite(@PathVariable Long userId,
                                                @PathVariable Long productId) {
        return productService.addOrRemoveToFavorite(userId, productId);
    }

}
