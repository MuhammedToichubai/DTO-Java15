package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.response.AuthResponse;
import peaksoft.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

   Optional<User> findUserByEmail(String email);

    default User findOrThrow(Long userId){
        return findById(userId).orElseThrow(
                () -> new IllegalArgumentException("User with id " + userId + " not found")
        );
    }

    @Query("""
       select new peaksoft.dto.response.AuthResponse(u.id, u.email, u.role)
              from User u
                     where u.id = :id
       """)
    AuthResponse findByUser(Long id);
}