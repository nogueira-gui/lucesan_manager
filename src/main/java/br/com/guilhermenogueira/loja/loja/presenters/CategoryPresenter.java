package br.com.guilhermenogueira.loja.loja.presenters;

import br.com.guilhermenogueira.loja.loja.models.Category;

public class CategoryPresenter {
    private final Long id;
    private final String code;
    private final String description;
    private final Boolean active;
    private final ImageCloudPresenter image;

    public CategoryPresenter(Category category, ImageCloudPresenter image) {
        this.id = category.getId();
        this.code = category.getCode();
        this.description = category.getDescription();
        this.active = category.isActive();
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

    public Boolean isActive() {
        return active;
    }

    public ImageCloudPresenter getImage() { return image; }
}
