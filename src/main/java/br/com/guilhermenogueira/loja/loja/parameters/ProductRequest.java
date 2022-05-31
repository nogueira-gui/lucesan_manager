package br.com.guilhermenogueira.loja.loja.parameters;

import java.math.BigDecimal;

public class ProductRequest {
    private String name;
    private String description;
    private String images;
    private Boolean highlighted;
    private Boolean available;
    private String category;
    private String subCategory;
    private Long campaignId;
    private String campaign;
    private BigDecimal price;
    private Long quantity;

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public void setHighlighted(Boolean highlighted) {
        this.highlighted = highlighted;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImages() {
        return images;
    }

    public Boolean getHighlighted() {
        return highlighted;
    }

    public String getCategory() {
        return category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public String getCampaign() {
        return campaign;
    }

    public void setCampaign(String campaign) {
        this.campaign = campaign;
    }

    public Boolean isAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
