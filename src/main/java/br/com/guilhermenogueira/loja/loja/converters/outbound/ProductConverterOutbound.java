package br.com.guilhermenogueira.loja.loja.converters.outbound;

import br.com.guilhermenogueira.loja.loja.models.Product;
import br.com.guilhermenogueira.loja.loja.presenters.ProductPresenter;
import com.sun.istack.NotNull;

public class ProductConverterOutbound {

    public static ProductPresenter converter(@NotNull Product product) {
        return new ProductPresenter(product);
    }
}
