package br.com.guilhermenogueira.loja.loja.repositories;

import br.com.guilhermenogueira.loja.loja.models.ImageCloud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<ImageCloud, Integer> {
    List<ImageCloud> findByOrderById();
    ImageCloud findByUrl(final String url);
}
