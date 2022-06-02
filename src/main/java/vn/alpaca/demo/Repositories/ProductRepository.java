package vn.alpaca.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.alpaca.demo.Domain.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
