package peaksoft.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String role;
    private String password;
    private LocalDate createdAt;

    @ManyToMany
    private List<Product> basketProducts;

    @ManyToMany
    private List<Product> favoriteProducts;

    @PrePersist
    @PreUpdate
    public void prePersist() {
        createdAt = LocalDate.now();
    }


}
