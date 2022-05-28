package br.com.guilhermenogueira.loja.loja.models;

import javax.persistence.*;

@Entity
@Table
public class ImageCloud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String url;
    private String imageId;

    public ImageCloud() {
        //empty
    }

    public ImageCloud(String name, String url, String imageId) {
        this.name = name;
        this.url = url;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public int getId() {
        return id;
    }
}
