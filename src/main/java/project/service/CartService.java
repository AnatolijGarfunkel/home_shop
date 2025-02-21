package project.service;

import project.entity.Cart;

public interface CartService {

    Cart add(Long productId, Integer quantity);

    Cart getByUserId(Long id);

    Cart getByCurrentUser();

    Cart deleteItems();

    Cart getById(Long cartId);
}
