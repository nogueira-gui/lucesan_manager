package br.com.guilhermenogueira.loja.loja.repositories;

import br.com.guilhermenogueira.loja.loja.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Boolean existsByCode(String code);

    @Query(value = "SELECT * FROM CATEGORY C WHERE C.ACTIVE=1",nativeQuery = true)
    List<Category> findAllByActives();
}
