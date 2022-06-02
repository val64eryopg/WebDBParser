package com.example.bebroid.controllers;

import com.example.bebroid.domain.CollectionDataModel;
import com.example.bebroid.repositories.CollectionDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

import static com.example.bebroid.sevices.ImageDownloadService.downloadImage;

@Controller
@RequestMapping(path="/extend")
public class ExtendController {
    @Autowired
    CollectionDataRepository collectionDataRepository;

    @GetMapping("")
    public ModelAndView getExtendPage(String collectionName) {
        var mav = new ModelAndView("extend");

        mav.addObject("collectionName", collectionName);

        return mav;
    }

    @PostMapping("")
    public String extendCollection(@RequestParam String collectionName, @RequestParam String photoUrl) {
        var imgName = "img_" + (int) (new Date().getTime() / 1000) + ".jpg";
        var imgPath = "img/" + imgName;

        downloadImage(photoUrl, imgName);

        var collectionDataPiece = new CollectionDataModel();

        collectionDataPiece.setCollectionName(collectionName);
        collectionDataPiece.setImagePath(imgPath);

        collectionDataRepository.save(collectionDataPiece);

        return "redirect:/";
    }
}
