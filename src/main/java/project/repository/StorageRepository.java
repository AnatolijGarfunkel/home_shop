package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.entity.Storage;

import java.util.Optional;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Long> {
    @Query(
            nativeQuery = true,
            value = "select * from storage where product_id = :productId"
    )
    Optional<Storage> findByProductId(Long productId);

    boolean existsByProductIdAndQuantityIsNot(Long productId, Integer quantity);

    @Query(
            nativeQuery = true,
            value = "select quantity from storage where product_id = :productId"
    )
    Integer findQuantityByProductId(Long productId);

    boolean existsByProductIdAndQuantityIsGreaterThanEqual(Long productId, Integer quantityIsGreaterThan);
}
