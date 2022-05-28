package br.com.guilhermenogueira.loja.loja.services.impl;

import br.com.guilhermenogueira.loja.loja.converters.outbound.ProductImageConverterOutbound;
import br.com.guilhermenogueira.loja.loja.models.ImageCloud;
import br.com.guilhermenogueira.loja.loja.presenters.ImageCloudPresenter;
import br.com.guilhermenogueira.loja.loja.repositories.ImageRepository;
import br.com.guilhermenogueira.loja.loja.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageRepository imageRepository;

    public List<ImageCloudPresenter> list() {
        final var list = imageRepository.findByOrderById();
        return list.stream().map(ProductImageConverterOutbound::converter).collect(Collectors.toList());
    }

    public Optional<ImageCloud> getOne(int id) {
        return imageRepository.findById(id);
    }

    public ImageCloud findByUrl(String url) {
        return imageRepository.findByUrl(url);
    }

    public void save(ImageCloud image) {
        imageRepository.save(image);
    }

    public void delete(int id) {
        imageRepository.deleteById(id);
    }

    public boolean exists(int id) {
        return imageRepository.existsById(id);
    }
}
