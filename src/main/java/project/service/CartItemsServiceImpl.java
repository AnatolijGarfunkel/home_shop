package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.Cart;
import project.entity.CartItems;
import project.entity.Product;
import project.repository.CartItemsRepository;

@Service
public class CartItemsServiceImpl implements CartItemsService {

    @Autowired
    private CartItemsRepository repository;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;


    @Override
    public CartItems add(Long cartId, Long productId, Integer quantity) {
        CartItems item = getByCartIdAndByProductId(cartId, productId);
        if (item != null) {
            item.setQuantity(item.getQuantity() + quantity);
        }
        else {
            Cart cart = cartService.getById(cartId);
            Product product = productService.getById(productId);
            item = new CartItems(cart, product, quantity);
        }
        return repository.save(item);
    }

    @Override
    public CartItems getByCartIdAndByProductId(Long cartId, Long productId) {
        return repository.findByCartIdAndProductId(cartId, productId).orElse(null);
    }

    @Override
    public void deteleItems(Long cartId) {
        repository.getCartItemsByCartId(cartId);

    }
}
