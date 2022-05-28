package br.com.guilhermenogueira.loja.loja.services.impl;

import br.com.guilhermenogueira.loja.loja.converters.inbound.CategoryConverterInbound;
import br.com.guilhermenogueira.loja.loja.converters.outbound.CategoryConverterOutbound;
import br.com.guilhermenogueira.loja.loja.models.Category;
import br.com.guilhermenogueira.loja.loja.parameters.CategoryRequest;
import br.com.guilhermenogueira.loja.loja.presenters.CategoryPresenter;
import br.com.guilhermenogueira.loja.loja.presenters.ImageCloudPresenter;
import br.com.guilhermenogueira.loja.loja.repositories.CategoryRepository;
import br.com.guilhermenogueira.loja.loja.services.CategoryService;
import br.com.guilhermenogueira.loja.loja.services.CloudinaryService;
import br.com.guilhermenogueira.loja.loja.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ImageService imageService;

    @Autowired
    private CloudinaryService cloudinaryService;

    public List<CategoryPresenter> findAll() {
        final var categoryList = categoryRepository.findAll();
        return categoryList.stream().map(response ->
                new CategoryPresenter(response, new ImageCloudPresenter(imageService.findByUrl(response.getImageUrl()))
                )).collect(Collectors.toList());
    }

    public List<CategoryPresenter> findAllActives() {
        final var categoryList = categoryRepository.findAllByActives();
        return categoryList.stream().map(CategoryConverterOutbound::converter).collect(Collectors.toList());
    }

    public CategoryPresenter findById(Long categoryId) {
        return CategoryConverterOutbound.converter(categoryRepository.getById(categoryId));
    }

    public CategoryPresenter createCategory(CategoryRequest request) {
        if (categoryRepository.existsByCode(request.getCode())) {
            throw new RuntimeException("code is already exists");
        }
        if (request.getCode().equals("") || isNull(request.getCode())) {
            throw new RuntimeException("code is null or empty");
        }
        final var saveResult = categoryRepository.save(CategoryConverterInbound.converter(request));
        return CategoryConverterOutbound.converter(saveResult);
    }

    public void delete(Long id) throws IOException {
        final var category = categoryRepository.findById(id);
        if (category.isPresent()) {
            final var image = imageService.findByUrl(category.get().getImageUrl());
            cloudinaryService.delete(image.getImageId(), "lucesan/category");
            imageService.delete(image.getId());
            categoryRepository.deleteById(id);
        }
    }

    public Boolean exists(Long id) {
        return categoryRepository.existsById(id);
    }

    public CategoryPresenter deactivate(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        Category request = new Category();
        category.ifPresent(value -> {
            request.setDescription(value.getDescription());
            request.setCode(value.getCode());
            request.setImageUrl(value.getImageUrl());
            request.setActive(false);
            request.setId(value.getId());
        });
        if (request.getCode() == null || request.getDescription() == null) {
            return null;
        }
        return CategoryConverterOutbound.converter(categoryRepository.save(request));
    }
}
