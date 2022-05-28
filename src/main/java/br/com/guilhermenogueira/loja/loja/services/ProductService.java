package br.com.guilhermenogueira.loja.loja.services;

import br.com.guilhermenogueira.loja.loja.models.Product;
import br.com.guilhermenogueira.loja.loja.parameters.ProductRequest;
import br.com.guilhermenogueira.loja.loja.parameters.ProductUpdateRequest;
import br.com.guilhermenogueira.loja.loja.presenters.ProductPresenter;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    List<ProductPresenter> findAll();

    List<ProductPresenter> findAllActives();

    ProductPresenter findById(Long productId);

    Product createProduct(ProductRequest request);

    ProductPresenter updateProduct(ProductUpdateRequest request);

    ProductPresenter deactivate(Long id);

    void delete(Long id) throws IOException;

    Boolean exists(Long id);
}
