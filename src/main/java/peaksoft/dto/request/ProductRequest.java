package peaksoft.dto.request;

import lombok.Data;
import peaksoft.enums.Size;
import peaksoft.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private long quantity;
    private String imageUrl;
    private List<Size> sizeList;
    private List<String> colors;


    public Product dtoToEntity() {
        Product product = new Product();
        product.setName(this.name);
        product.setDescription(this.description);
        product.setPrice(this.price);
        product.setQuantity(this.quantity);
        product.setImageUrl(this.imageUrl);
        if (product.getSize() == null) {product.setSize(new ArrayList<>());}
        product.getSize().addAll(this.sizeList);
        if (product.getColors() == null) {product.setColors(new ArrayList<>());}
        product.getColors().addAll(this.colors);
        return product;
    }
}
