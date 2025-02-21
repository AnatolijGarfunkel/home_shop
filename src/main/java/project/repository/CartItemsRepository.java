package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.entity.CartItems;

import java.util.Optional;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, Long> {

    void getCartItemsByCartId(Long cartId);

    Optional<CartItems> findByCartIdAndProductId(Long cartId, Long productId);
}
