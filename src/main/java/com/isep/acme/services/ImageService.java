package com.isep.acme.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;

import com.isep.acme.dtos.ImageDTO;
import com.isep.acme.model.ProdImage;
import com.isep.acme.services.iRepositories.ImageRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {

    //@Autowired
    private Resource image;
    //@Autowired
     private ProdImage id;
     @Autowired
     private FileStorageService service;
     @Autowired
     private ImageRepository repository;
    private String filename;


    public Iterable<ImageDTO> getImageProduct(){
          Iterable<ProdImage> p = repository.findAll();
          List<ImageDTO> iDto= new ArrayList();
          for (ProdImage pd:p) {
               iDto.add(pd.toDto());
          }

          return iDto;
     };

    public <ProdImage> Resource addImage(Resource image){

        this.image =  service.loadFileAsResource(filename);
        return image;
     }







}
