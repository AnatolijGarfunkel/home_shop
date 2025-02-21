package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.Cart;
import project.entity.CartItems;
import project.exception.NotFoundException;
import project.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemsService cartItemsService;


    @Override
    public Cart add(Long productId, Integer quantity) {
        Cart cart = getByCurrentUser();
        Long cartId = cart.getId();
        CartItems item = cartItemsService.add(cartId, productId, quantity);
        cart.getItems().add(item);
        return cart;
    }

    @Override
    public Cart getByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public Cart getByCurrentUser() {
        Long userId = userService.getCurrentUserId();
        return repository.findByUserId(userId);
    }

    @Override
    public Cart deleteItems() {
        Long userId = userService.getCurrentUserId();
        Cart cart = getByUserId(userId);
        Long cartId = cart.getId();
        cartItemsService.deteleItems(cartId);
        return null;
    }

    @Override
    public Cart getById(Long cartId) {
        return repository.findById(cartId)
                .orElseThrow(
                        () -> new NotFoundException("Cart with id " + cartId + " not found")
                );
    }
}
