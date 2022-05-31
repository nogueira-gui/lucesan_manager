package br.com.guilhermenogueira.loja.loja.repositories;

import br.com.guilhermenogueira.loja.loja.models.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CampaignRepository extends JpaRepository<Campaign,Long> {
    Boolean existsByName(String name);

    @Query(value = "SELECT * FROM CAMPAIGN C WHERE C.ACTIVE=1",nativeQuery = true)
    List<Campaign> findAllByActives();
}
