package com.example.bebroid.sevices;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ImageDownloadService {
    private final static String IMAGE_DESTINATION_FOLDER = "src/main/resources/static/img";

    public static String getImageFolderPath() {
        return IMAGE_DESTINATION_FOLDER;
    }

    public static ArrayList<String> getImg(String imageUrl) {
        ArrayList<String> images = new ArrayList<>();

        Document document = null;
        try {
            document = Jsoup
                .connect(imageUrl)
                .userAgent("Mozilla/5.0")
                .timeout(10 * 1000)
                .get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert document != null;
        Elements imageElements = document.select("img");
        int count = 1;

        for (Element imageElement: imageElements) {
            String strImageURL = imageElement.attr("abs:src");

            downloadImage(strImageURL, Integer.toString(count));
            images.add(IMAGE_DESTINATION_FOLDER + "/" + "img_" + count + ".jpg");
            count += 1;
        }

        return images;
    }

    public static void downloadImage(String strImageURL, String imageName) {
        String strImageName =
            strImageURL.substring(strImageURL.lastIndexOf("/") + 1);

        System.out.println("Saving: " + strImageName + ", from: " + strImageURL);

        try {
            URL urlImage = new URL(strImageURL);
            InputStream in = urlImage.openStream();

            byte[] buffer = new byte[4096];
            int n;

            OutputStream os = new FileOutputStream(IMAGE_DESTINATION_FOLDER + "/" + imageName);

            while ((n = in.read(buffer)) != -1) {
                os.write(buffer, 0, n);
            }

            os.close();

            System.out.println("Image saved.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}