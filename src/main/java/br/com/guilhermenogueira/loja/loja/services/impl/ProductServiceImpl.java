package br.com.guilhermenogueira.loja.loja.services.impl;

import br.com.guilhermenogueira.loja.loja.converters.inbound.ProductConverterInbound;
import br.com.guilhermenogueira.loja.loja.converters.outbound.ProductConverterOutbound;
import br.com.guilhermenogueira.loja.loja.models.Product;
import br.com.guilhermenogueira.loja.loja.parameters.ProductRequest;
import br.com.guilhermenogueira.loja.loja.parameters.ProductUpdateRequest;
import br.com.guilhermenogueira.loja.loja.presenters.ImageCloudPresenter;
import br.com.guilhermenogueira.loja.loja.presenters.ProductPresenter;
import br.com.guilhermenogueira.loja.loja.repositories.ProductRepository;
import br.com.guilhermenogueira.loja.loja.services.CloudinaryService;
import br.com.guilhermenogueira.loja.loja.services.ImageService;
import br.com.guilhermenogueira.loja.loja.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageService imageService;

    @Autowired
    private CloudinaryService cloudinaryService;

    public List<ProductPresenter> findAll() {
        final var productList = productRepository.findAll();
        return productList.stream().map(response ->
                new ProductPresenter(
                        response, new ImageCloudPresenter(imageService.findByUrl(response.getImageUrl()))
                )).collect(Collectors.toList());
    }

    public List<ProductPresenter> findAllActives() {
        final var productList = productRepository.findAllByActive();
        return productList.stream().map(ProductPresenter::new).collect(Collectors.toList());
    }

    public ProductPresenter findById(Long productId) {
        return ProductConverterOutbound.converter(productRepository.getById(productId));
    }

    public Product createProduct(ProductRequest request) {
        return productRepository.save(ProductConverterInbound.converter(request));
    }

    public ProductPresenter updateProduct(ProductUpdateRequest request) {
        Optional<Product> product = productRepository.findById(request.getId());
        if (product.isPresent()) {
            return ProductConverterOutbound.converter(productRepository.save(ProductConverterInbound.converterUpdateProduct(request)));
        }
        throw new RuntimeException("Product id " + request.getId() + " doesn't exist");
    }

    public Boolean exists(Long id) {
        return productRepository.existsById(id);
    }

    public void delete(Long id) throws IOException {
        final var product = productRepository.findById(id);
        if (product.isPresent()) {
            final var image = imageService.findByUrl(product.get().getImageUrl());
            cloudinaryService.delete(image.getImageId(), "lucesan/product");
            imageService.delete(image.getId());
            productRepository.deleteById(id);
        }
    }

    public ProductPresenter deactivate(Long id) {
        Optional<Product> product = productRepository.findById(id);
        Product request = new Product();
        product.ifPresent(value -> {
            request.setId(value.getId());
            request.setName(value.getName());
            request.setDescription(value.getDescription());
            request.setImageUrl(value.getImageUrl());
            request.setActive(false); // logical exclusion
            request.setHighlighted(value.isHighlighted());
            request.setAvailable(value.isAvailable());
            request.setPrice(value.getPrice());
            request.setQuantity(value.getQuantity());
            request.setCategory(value.getCategory());
            request.setSubCategory(value.getSubCategory());
        });
        if (request.getName() == null || request.getDescription() == null) {
            throw new RuntimeException("Product id not found");
        }
        return ProductConverterOutbound.converter(productRepository.save(request));
    }

}
