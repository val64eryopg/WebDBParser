package com.example.bebroid.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CollectionDataModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String imagePath;
    private String collectionName;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String pathToImage) {
        this.imagePath = pathToImage;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String login) {
        this.collectionName = login;
    }
}