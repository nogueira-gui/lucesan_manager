package br.com.guilhermenogueira.loja.loja.models;

import javax.persistence.*;

@Entity
@Table(name="campaign")
public class Campaign {

    @Id
    @SequenceGenerator(
            name = "campaign_sequence",
            sequenceName = "campaign_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "campaign_sequence"
    )
    private Long id;
    private String code;
    private String description;
    private Double priceCut;
    private Boolean active;
    private String imageUrl;

    public Campaign() {
        //emptyArgs
    }

    public Campaign(Long id, String code, String description, Double priceCut, Boolean active, String imageUrl) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.priceCut = priceCut;
        this.active = active;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
