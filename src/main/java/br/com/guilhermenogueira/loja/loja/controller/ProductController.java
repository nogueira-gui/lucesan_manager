package br.com.guilhermenogueira.loja.loja.controller;

import br.com.guilhermenogueira.loja.loja.models.ImageCloud;
import br.com.guilhermenogueira.loja.loja.models.Product;
import br.com.guilhermenogueira.loja.loja.models.ResponseMessage;
import br.com.guilhermenogueira.loja.loja.parameters.ProductRequest;
import br.com.guilhermenogueira.loja.loja.parameters.ProductUpdateRequest;
import br.com.guilhermenogueira.loja.loja.presenters.ImageCloudPresenter;
import br.com.guilhermenogueira.loja.loja.presenters.ProductPresenter;
import br.com.guilhermenogueira.loja.loja.services.CloudinaryService;
import br.com.guilhermenogueira.loja.loja.services.ImageService;
import br.com.guilhermenogueira.loja.loja.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "v1/product")
@CrossOrigin(origins = {"http://localhost:4200","https://lucesan-manager-angular-4q7v4rccm-nogueira-gui.vercel.app"})
public class ProductController {

    private final ProductService productService;

    private final CloudinaryService cloudinaryService;

    private final ImageService imageService;

    @Autowired
    public ProductController(ProductService productService, CloudinaryService cloudinaryService, ImageService imageService) {
        this.productService = productService;
        this.cloudinaryService = cloudinaryService;
        this.imageService = imageService;
    }

    @GetMapping("/all")
    public List<ProductPresenter> findAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/all/actives")
    public List<ProductPresenter> findAllActives() {
        return productService.findAllActives();
    }

    @GetMapping("/{productId}")
    public ProductPresenter findById(@PathVariable Long productId) {
        return productService.findById(productId);
    }

    @PostMapping()
    public Product createProduct(@RequestBody ProductRequest request) {
        return productService.createProduct(request);
    }

    @PutMapping()
    public ProductPresenter updateProduct(@RequestBody ProductUpdateRequest request) {
        return productService.updateProduct(request);
    }

    @PutMapping("/deactivate/{id}")
    public ProductPresenter deactivate(@PathVariable("id") Long id) {
        return productService.deactivate(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) throws IOException {
        if (!productService.exists(id))
            return new ResponseEntity(new ResponseMessage("Does not exist", null), HttpStatus.NOT_FOUND);

        productService.delete(id);
        return new ResponseEntity(new ResponseMessage("Product has been deleted", null), HttpStatus.OK);
    }

    @GetMapping("/image/list")
    public ResponseEntity<List<ImageCloudPresenter>> list() {
        List<ImageCloudPresenter> list = imageService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/image/upload")
    public ResponseEntity<?> uploadImage(@RequestParam MultipartFile multipartFile) throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if (bi == null) {
            return new ResponseEntity(new ResponseMessage("Image is not compatible",null), HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(multipartFile, "lucesan/product");
        ImageCloud image =
                new ImageCloud((String) result.get("original_filename"),
                        (String) result.get("url"),
                        (String) result.get("public_id"));
        imageService.save(image);
        return new ResponseEntity(new ResponseMessage("Image has uploaded", (String) result.get("url")), HttpStatus.OK);
    }

    @DeleteMapping("/image/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) throws IOException {
        if (!imageService.exists(id))
            return new ResponseEntity(new ResponseMessage("Does not exist", null), HttpStatus.NOT_FOUND);
        ImageCloud image = imageService.getOne(id).get();
        Map result = cloudinaryService.delete(image.getImageId(), "lucesan/product");
        imageService.delete(id);
        return new ResponseEntity(new ResponseMessage("Image has been deleted", null), HttpStatus.OK);
    }

}
