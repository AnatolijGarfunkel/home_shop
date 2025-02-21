package project.service;

import project.entity.CartItems;

public interface CartItemsService {

    CartItems add(Long cartId, Long productId, Integer quantity);

    CartItems getByCartIdByProductId(Long userId, Long productId);

    void deteleItems(Long cartId);
}
