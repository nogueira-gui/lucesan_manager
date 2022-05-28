package br.com.guilhermenogueira.loja.loja.repositories;

import br.com.guilhermenogueira.loja.loja.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM PRODUCT P WHERE P.ACTIVE=1", nativeQuery = true)
    List<Product> findAllByActive();
}
