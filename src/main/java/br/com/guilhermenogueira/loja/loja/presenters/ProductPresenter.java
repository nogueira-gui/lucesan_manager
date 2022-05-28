package br.com.guilhermenogueira.loja.loja.presenters;

import br.com.guilhermenogueira.loja.loja.models.ImageCloud;
import br.com.guilhermenogueira.loja.loja.models.Product;

import java.math.BigDecimal;

public class ProductPresenter {

    private final Long id;
    private final String name;
    private final String description;
    private final ImageCloudPresenter images;
    private final Boolean active;
    private final Boolean available;
    private final Boolean highlighted;
    private final String category;
    private final String subCategory;
    private final BigDecimal price;
    private final Long quantity;

    public ProductPresenter(Product productResponse) {
        this.id = productResponse.getId();
        this.name = productResponse.getName();
        this.description = productResponse.getDescription();
        this.images = new ImageCloudPresenter(new ImageCloud("", productResponse.getImageUrl(), ""));
        this.active = productResponse.isActive();
        this.highlighted = productResponse.isHighlighted();
        this.category = productResponse.getCategory();
        this.subCategory = productResponse.getSubCategory();
        this.price = productResponse.getPrice();
        this.quantity = productResponse.getQuantity();
        this.available = productResponse.isAvailable();
    }
    public ProductPresenter(Product productResponse, ImageCloudPresenter image) {
        this.id = productResponse.getId();
        this.name = productResponse.getName();
        this.description = productResponse.getDescription();
        this.images = image;
        this.active = productResponse.isActive();
        this.highlighted = productResponse.isHighlighted();
        this.category = productResponse.getCategory();
        this.subCategory = productResponse.getSubCategory();
        this.price = productResponse.getPrice();
        this.quantity = productResponse.getQuantity();
        this.available = productResponse.isAvailable();
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

    public ImageCloudPresenter getImages() {
        return images;
    }

    public Boolean getActive() {
        return active;
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

    public BigDecimal getPrice() {
        return price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Boolean getAvailable() {
        return available;
    }
}
