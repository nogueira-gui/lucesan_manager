package br.com.guilhermenogueira.loja.loja.controller;

import br.com.guilhermenogueira.loja.loja.models.ImageCloud;
import br.com.guilhermenogueira.loja.loja.models.ResponseMessage;
import br.com.guilhermenogueira.loja.loja.parameters.CampaignRequest;
import br.com.guilhermenogueira.loja.loja.presenters.CampaignPresenter;
import br.com.guilhermenogueira.loja.loja.presenters.ImageCloudPresenter;
import br.com.guilhermenogueira.loja.loja.services.CampaignService;
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
@RequestMapping(path = "v1/campaign")
@CrossOrigin(origins = {"http://localhost:4200","https://lucesan-manager-angular.vercel.app", "http://d2os8tisjwjzio.cloudfront.net", "https://d2os8tisjwjzio.cloudfront.net"})
public class CampaignController {
    private final CampaignService campaignService;
    private final ImageService imageService;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public CampaignController(CampaignService campaignService, ImageService imageService, CloudinaryService cloudinaryService) {
        this.campaignService = campaignService;
        this.imageService = imageService;
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping("/all")
    public List<CampaignPresenter> getCategoryList() {
        return campaignService.findAll();
    }

    @GetMapping("/all/actives")
    public List<CampaignPresenter> getActiveCategoryList() {
        return campaignService.findAllActives();
    }

    @GetMapping("/{campaignId}")
    public CampaignPresenter findById(@PathVariable Long campaignId) {
        return campaignService.findById(campaignId);
    }

    @PostMapping()
    public CampaignPresenter createCategory(@RequestBody CampaignRequest request) {
        return campaignService.createCampaign(request);
    }

    @PutMapping("/deactivate/{id}")
    public CampaignPresenter deactivate(@PathVariable("id") Long id) {
        return campaignService.deactivate(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) throws IOException {
        if (!campaignService.exists(id))
            return new ResponseEntity(new ResponseMessage("Does not exist", null), HttpStatus.NOT_FOUND);

        campaignService.delete(id);
        return new ResponseEntity(new ResponseMessage("campaign has been deleted", null), HttpStatus.OK);
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
            return new ResponseEntity(new ResponseMessage("Image is not compatible", null), HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(multipartFile, "lucesan/banner");
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
        Map result = cloudinaryService.delete(image.getImageId(), "lucesan/banner");
        imageService.delete(id);
        return new ResponseEntity(new ResponseMessage("Image has been deleted", null), HttpStatus.OK);
    }

}
