package br.com.guilhermenogueira.loja.loja.converters.outbound;

import br.com.guilhermenogueira.loja.loja.models.ImageCloud;
import br.com.guilhermenogueira.loja.loja.presenters.ImageCloudPresenter;

public class ProductImageConverterOutbound {

    public static ImageCloudPresenter converter(ImageCloud imageResponse){
        return new ImageCloudPresenter(imageResponse);
    }
}
