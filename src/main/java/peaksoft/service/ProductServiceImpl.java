package peaksoft.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import peaksoft.dto.request.ProductRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.enums.Category;
import peaksoft.model.Product;
import peaksoft.model.User;
import peaksoft.repository.ProductRepository;
import peaksoft.repository.UserRepository;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;


    @Override
    public SimpleResponse save(Category category, ProductRequest request) {
        try {
            Product product = request.dtoToEntity();
            product.setCategory(category);
            productRepository.save(product);
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }
        return new SimpleResponse(
                HttpStatus.OK,
                "Product successfully saved!"
        );

    }

    @Override
    @Transactional
    public SimpleResponse addOrRemoveToFavorite(Long userId, Long productId) {
        User user = userRepository.findOrThrow(userId);
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (user.getFavoriteProducts() == null) {user.setFavoriteProducts(new ArrayList<>());}

        if (user.getFavoriteProducts().contains(product)) {
            user.getFavoriteProducts().remove(product);
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Product successfully removed!")
                    .build();
        }else {
            user.getFavoriteProducts().add(product);
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Product successfully added!")
                    .build();
        }
    }
}
