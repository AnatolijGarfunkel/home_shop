package project.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import project.entity.Order;
import project.entity.Product;
import project.entity.Storage;
import project.enums.OrderStatus;
import project.exception.NotFoundException;
import project.repository.StorageRepository;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageRepository repository;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;


    @Override
    public Storage addProduct(Product product, Integer quantity) {
        Storage storage = new Storage(product, quantity);
        return repository.save(storage);
    }

    @Override
    public Storage addProductQuantity(Long productId, Integer quantity) {
        Storage storage = getByProductId(productId);
        storage.setQuantity(storage.getQuantity() + quantity);
        return repository.save(storage);
    }

    @Override
    public Storage pool(Long productId, Integer quantity) {
        Storage storage = getByProductId(productId);
        storage.setQuantity(storage.getQuantity() - quantity);
        return repository.save(storage);
    }

    @Override
    public Product getProductByProductId(Long id) {
        return productService.getById(id);
    }

    @Override
    public List<Storage> getAll() {
        return repository.findAll();
    }

    @Override
    public Storage getByProductId(Long productId) {
        return repository.findByProductId(productId)
                .orElseThrow(
                        () -> new NotFoundException("Product with id " + productId + " not found in storage")
                );
    }

    @Override
    @Transactional
    @Scheduled(initialDelay = 15, fixedRate = 15, timeUnit = TimeUnit.SECONDS)
    public void toReduceProductQuantity() {
        List<Order> orders = orderService.getAllByPaid();
        orders.stream()
                .map(Order::getItems)
                .flatMap(Collection::stream)
                .forEach(
                        item -> {
                            Long productId = item.getProduct().getId();
                            Integer quantity = item.getQuantity();
                            Storage storage = getByProductId(productId);
                            storage.setQuantity(storage.getQuantity() - quantity);
                            repository.save(storage);
                        }
        );

        orders.forEach(
                        order -> {
                            order.setStatus(OrderStatus.SENT);
                            orderService.update(order);
                        }
                );
    }
}
