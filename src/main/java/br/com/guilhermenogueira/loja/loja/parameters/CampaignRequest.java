package br.com.guilhermenogueira.loja.loja.parameters;

public class CampaignRequest {
    private String code;
    private String description;
    private Double priceCut;
    private Boolean active;
    private String image;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPriceCut() {
        return priceCut;
    }

    public void setPriceCut(Double priceCut) {
        this.priceCut = priceCut;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
