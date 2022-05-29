package br.com.guilhermenogueira.loja.loja.controller;

import br.com.guilhermenogueira.loja.loja.models.ImageCloud;
import br.com.guilhermenogueira.loja.loja.models.ResponseMessage;
import br.com.guilhermenogueira.loja.loja.parameters.CategoryRequest;
import br.com.guilhermenogueira.loja.loja.presenters.CategoryPresenter;
import br.com.guilhermenogueira.loja.loja.presenters.ImageCloudPresenter;
import br.com.guilhermenogueira.loja.loja.services.CategoryService;
import br.com.guilhermenogueira.loja.loja.services.CloudinaryService;
import br.com.guilhermenogueira.loja.loja.services.ImageService;
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
@RequestMapping(path="v1/category")
@CrossOrigin(origins = {"http://localhost:4200","https://lucesan-manager-angular-h6y75e80k-nogueira-gui.vercel.app","https://lucesan-manager-angular.vercel.app"})
public class CategoryController {
    private final CategoryService categoryService;
    private final ImageService imageService;
    private final CloudinaryService cloudinaryService;
    @Autowired
    public CategoryController(CategoryService categoryService,ImageService imageService, CloudinaryService cloudinaryService) {
        this.categoryService = categoryService;
        this.imageService = imageService;
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping("/all")
    public List<CategoryPresenter> getCategoryList(){
        return categoryService.findAll();
    }

    @GetMapping("/all/actives")
    public List<CategoryPresenter> getActiveCategoryList(){
        return categoryService.findAllActives();
    }

    @GetMapping("/{categoryId}")
    public CategoryPresenter findById(@PathVariable Long categoryId){
        return categoryService.findById(categoryId);
    }

    @PostMapping()
    public CategoryPresenter createCategory(@RequestBody CategoryRequest request){
        return categoryService.createCategory(request);
    }

    @PutMapping("/deactivate/{id}")
    public CategoryPresenter deactivate(@PathVariable("id") Long id){
        return categoryService.deactivate(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) throws IOException {
        if (!categoryService.exists(id))
            return new ResponseEntity(new ResponseMessage("Does not exist", null), HttpStatus.NOT_FOUND);

        categoryService.delete(id);
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
        Map result = cloudinaryService.upload(multipartFile, "lucesan/category");
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
        Map result = cloudinaryService.delete(image.getImageId(), "lucesan/category");
        imageService.delete(id);
        return new ResponseEntity(new ResponseMessage("Image has been deleted", null), HttpStatus.OK);
    }


}

