package br.com.guilhermenogueira.loja.loja.converters.inbound;

import br.com.guilhermenogueira.loja.loja.models.Product;
import br.com.guilhermenogueira.loja.loja.parameters.ProductRequest;
import br.com.guilhermenogueira.loja.loja.parameters.ProductUpdateRequest;

public class ProductConverterInbound {

    public static Product converter(ProductRequest request) {
        Product product = new Product();
        product.setActive(true);
        product.setAvailable(request.isAvailable());
        product.setCategory(request.getCategory());
        product.setDescription(request.getDescription());
        product.setHighlighted(request.getHighlighted());
        product.setImageUrl(request.getImages());
        product.setName(request.getName());
        product.setSubCategory(request.getSubCategory());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        return product;
    }

    public static Product converterUpdateProduct(ProductUpdateRequest request) {
        Product product = new Product();
        product.setId(request.getId());
        product.setActive(true);
        product.setAvailable(request.isAvailable());
        product.setCategory(request.getCategory());
        product.setDescription(request.getDescription());
        product.setHighlighted(request.getHighlighted());
        product.setImageUrl(request.getImages());
        product.setName(request.getName());
        product.setSubCategory(request.getSubCategory());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        return product;
    }
}
