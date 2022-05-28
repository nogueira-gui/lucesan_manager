package br.com.guilhermenogueira.loja.loja.converters.inbound;

import br.com.guilhermenogueira.loja.loja.models.Category;
import br.com.guilhermenogueira.loja.loja.parameters.CategoryRequest;

public class CategoryConverterInbound {
    public static Category converter(CategoryRequest request) {
        Category category = new Category();
        category.setCode(request.getCode());
        category.setDescription(request.getDescription());
        category.setImageUrl(request.getImage());
        category.setActive(true);
        return category;
    }
}
