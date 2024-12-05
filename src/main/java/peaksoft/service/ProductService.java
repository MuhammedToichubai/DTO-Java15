package peaksoft.service;

import peaksoft.dto.request.ProductRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.enums.Category;

public interface ProductService {
    SimpleResponse save(Category category, ProductRequest request);

    SimpleResponse addOrRemoveToFavorite(Long userId, Long productId);
}
