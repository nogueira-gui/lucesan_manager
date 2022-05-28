package br.com.guilhermenogueira.loja.loja.services.impl;

import br.com.guilhermenogueira.loja.loja.services.CloudinaryService;
import com.cloudinary.Cloudinary;
import org.springframework.stereotype.Service;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {
    Cloudinary cloudinary;

    private Map<String, String> valuesMap = new HashMap<>();

    public CloudinaryServiceImpl() {
        valuesMap.put("cloud_name", "nogueiras");
        valuesMap.put("api_key", "283778722396273");
        valuesMap.put("api_secret", "jSZz-tUGIJ2tzeiuvRszWbP7NCI");
        cloudinary = new Cloudinary(valuesMap);
    }

    public Map upload(MultipartFile multipartFile, String folder) throws IOException {
        File file = convert(multipartFile);
        Map result = cloudinary.uploader().upload(file, ObjectUtils.asMap(
                "folder", folder
        ));
        file.delete();
        return result;
    }

    public Map delete(String id, String public_id) throws IOException {
        Map result = cloudinary.uploader().destroy(id, ObjectUtils.asMap(
                "public_id", public_id
        ));
        return result;
    }

    private File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
    }
}
