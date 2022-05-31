package br.com.guilhermenogueira.loja.loja.services.impl;

import br.com.guilhermenogueira.loja.loja.converters.inbound.CampaignConverterInbound;
import br.com.guilhermenogueira.loja.loja.converters.outbound.CampaignConverterOutbound;
import br.com.guilhermenogueira.loja.loja.models.Campaign;
import br.com.guilhermenogueira.loja.loja.parameters.CampaignRequest;
import br.com.guilhermenogueira.loja.loja.presenters.CampaignPresenter;
import br.com.guilhermenogueira.loja.loja.presenters.ImageCloudPresenter;
import br.com.guilhermenogueira.loja.loja.repositories.CampaignRepository;
import br.com.guilhermenogueira.loja.loja.services.CampaignService;
import br.com.guilhermenogueira.loja.loja.services.CloudinaryService;
import br.com.guilhermenogueira.loja.loja.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CampaignServiceImpl implements CampaignService {
    @Autowired
    CampaignRepository campaignRepository;

    @Autowired
    private ImageService imageService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public List<CampaignPresenter> findAll() {
        final var campaignList = campaignRepository.findAll();
        return campaignList.stream().map(response ->
                new CampaignPresenter(response, new ImageCloudPresenter(imageService.findByUrl(response.getImageUrl()))
                )).collect(Collectors.toList());
    }

    @Override
    public List<CampaignPresenter> findAllActives() {
        final var campaignList = campaignRepository.findAllByActives();
        return campaignList.stream().map(CampaignConverterOutbound::converter).collect(Collectors.toList());
    }

    @Override
    public CampaignPresenter findById(Long campaignId) {
        return CampaignConverterOutbound.converter(campaignRepository.getById(campaignId));
    }

    @Override
    public Boolean exists(Long id) {
        return campaignRepository.existsById(id);
    }

    @Override
    public void delete(Long id) throws IOException {
        final var campaign = campaignRepository.findById(id);
        if (campaign.isPresent()) {
            final var image = imageService.findByUrl(campaign.get().getImageUrl());
            cloudinaryService.delete(image.getImageId(), "lucesan/banner");
            imageService.delete(image.getId());
            campaignRepository.deleteById(id);
        }
    }

    @Override
    public CampaignPresenter createCampaign(CampaignRequest request) {
        final var saveResult = campaignRepository.save(CampaignConverterInbound.converter(request));
        return CampaignConverterOutbound.converter(saveResult);
    }

    @Override
    public CampaignPresenter deactivate(Long id) {
        Optional<Campaign> campaign = campaignRepository.findById(id);
        Campaign request = new Campaign();
        campaign.ifPresent(value -> {
            request.setDescription(value.getDescription());
            request.setName(value.getName());
            request.setImageUrl(value.getImageUrl());
            request.setActive(false);
            request.setId(value.getId());
        });
        if (request.getName() == null || request.getDescription() == null) {
            return null;
        }
        return CampaignConverterOutbound.converter(campaignRepository.save(request));
    }
}
