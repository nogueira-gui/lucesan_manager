package br.com.guilhermenogueira.loja.loja.services;

import br.com.guilhermenogueira.loja.loja.parameters.CategoryRequest;
import br.com.guilhermenogueira.loja.loja.presenters.CategoryPresenter;

import java.io.IOException;
import java.util.List;

public interface CategoryService {
    List<CategoryPresenter> findAll();

    List<CategoryPresenter> findAllActives();

    CategoryPresenter findById(Long categoryId);

    Boolean exists(Long id);

    void delete (Long id) throws IOException;

    CategoryPresenter createCategory(CategoryRequest request);

    CategoryPresenter deactivate(Long id);
}
