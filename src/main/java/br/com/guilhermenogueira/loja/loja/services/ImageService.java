package br.com.guilhermenogueira.loja.loja.services;

import br.com.guilhermenogueira.loja.loja.models.ImageCloud;
import br.com.guilhermenogueira.loja.loja.presenters.ImageCloudPresenter;

import java.util.List;
import java.util.Optional;

public interface ImageService {
    List<ImageCloudPresenter> list();

    Optional<ImageCloud> getOne(int id);

    void save(ImageCloud image);

    void delete(int id);

    boolean exists(int id);

    ImageCloud findByUrl(String url);
}
