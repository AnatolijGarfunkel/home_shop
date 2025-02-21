package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

    @Query(
            nativeQuery = true,
            value = "select * from carts where user_id = :userId"
    )
    Cart findByUserId(Long userId);
}
