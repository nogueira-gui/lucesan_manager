package br.com.guilhermenogueira.loja.loja.presenters;

import br.com.guilhermenogueira.loja.loja.models.Campaign;

public class CampaignPresenter {
    private final Long id;
    private final String name;
    private final String description;
    private final Boolean active;
    private final ImageCloudPresenter image;

    public CampaignPresenter(Campaign campaign, ImageCloudPresenter image) {
        this.id = campaign.getId();
        this.name = campaign.getName();
        this.description = campaign.getDescription();
        this.active = campaign.isActive();
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Boolean isActive() {
        return active;
    }

    public ImageCloudPresenter getImage() {
        return image;
    }
}
