package br.com.guilhermenogueira.loja.loja.presenters;

import br.com.guilhermenogueira.loja.loja.models.Campaign;

public class CampaignPresenter {
    private final Long id;
    private final String code;
    private final String description;
    private final Double priceCut;
    private final Boolean active;
    private final ImageCloudPresenter image;

    public CampaignPresenter(Campaign campaign, ImageCloudPresenter image) {
        this.id = campaign.getId();
        this.code = campaign.getCode();
        this.description = campaign.getDescription();
        this.priceCut = campaign.getPriceCut();
        this.active = campaign.isActive();
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public Double getPriceCut() {
        return priceCut;
    }

    public Boolean isActive() {
        return active;
    }

    public ImageCloudPresenter getImage() {
        return image;
    }
}
