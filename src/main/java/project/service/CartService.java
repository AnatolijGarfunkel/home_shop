package project.service;

import project.entity.Cart;

public interface CartService {

    Cart create(Cart cart);

    Cart add(Long productId, Integer quantity);

    Cart getByUserId(Long id);

    Cart getByCurrentUser();

    void deleteItems(Cart cart);

    Cart getById(Long cartId);

}
