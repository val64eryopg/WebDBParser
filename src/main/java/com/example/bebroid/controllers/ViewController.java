package com.example.bebroid.controllers;

import com.example.bebroid.repositories.CollectionDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Objects;

@Controller
public class ViewController {
    @Autowired
    CollectionDataRepository collectionDataRepository;

    @GetMapping("/view")
    public ModelAndView getViewPage(String collectionName) {
        var allCollectionsData = collectionDataRepository.findAll();
        var imageData = new ArrayList<String>();

        for (var collectionData: allCollectionsData) {
            if (Objects.equals(collectionName, collectionData.getCollectionName()))
                imageData.add(collectionData.getImagePath());
        }

        var mav = new ModelAndView("view");

        mav.addObject("imageData", imageData);
        mav.addObject("collectionName", collectionName);

        return mav;
    }
}
