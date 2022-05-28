package br.com.guilhermenogueira.loja.loja.presenters;

import br.com.guilhermenogueira.loja.loja.models.ImageCloud;

public class ImageCloudPresenter {

    private int id;
    private String name;
    private String url;
    private String imageId;

    public ImageCloudPresenter() {
        //
    }

    public ImageCloudPresenter(ImageCloud imageCloud) {
        this.id = imageCloud.getId();
        this.name = imageCloud.getName();
        this.url = imageCloud.getUrl();
        this.imageId = imageCloud.getImageId();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getImageId() {
        return imageId;
    }
}
