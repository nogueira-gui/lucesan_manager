package br.com.guilhermenogueira.loja.loja.converters.outbound;

import br.com.guilhermenogueira.loja.loja.models.Category;
import br.com.guilhermenogueira.loja.loja.models.ImageCloud;
import br.com.guilhermenogueira.loja.loja.presenters.CategoryPresenter;
import br.com.guilhermenogueira.loja.loja.presenters.ImageCloudPresenter;
import com.sun.istack.NotNull;

public class CategoryConverterOutbound {
    public static CategoryPresenter converter(@NotNull Category category) {
        return new CategoryPresenter(category, new ImageCloudPresenter(new ImageCloud("",category.getImageUrl(),"")));
    }
}
