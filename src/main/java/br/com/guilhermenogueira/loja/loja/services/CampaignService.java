package br.com.guilhermenogueira.loja.loja.services;

import br.com.guilhermenogueira.loja.loja.parameters.CampaignRequest;
import br.com.guilhermenogueira.loja.loja.presenters.CampaignPresenter;

import java.io.IOException;
import java.util.List;

public interface CampaignService {

    List<CampaignPresenter> findAll();

    List<CampaignPresenter> findAllActives();

    CampaignPresenter findById(Long campaignId);

    Boolean exists(Long id);

    void delete(Long id) throws IOException;

    CampaignPresenter createCampaign(CampaignRequest request);

    CampaignPresenter deactivate(Long id);
}
