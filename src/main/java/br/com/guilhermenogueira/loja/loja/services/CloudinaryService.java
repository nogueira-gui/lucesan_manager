package br.com.guilhermenogueira.loja.loja.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface CloudinaryService {

    Map upload(MultipartFile multipartFile, String folder) throws IOException;

    Map delete(String id, String public_id) throws IOException;

}
