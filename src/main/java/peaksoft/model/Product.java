package peaksoft.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.enums.Category;
import peaksoft.enums.Size;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private long quantity;
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Size> size;

    @ElementCollection
    private List<String> colors;
}
