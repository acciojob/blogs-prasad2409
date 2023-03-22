package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Blog blog = blogRepository2.findById(blogId).get();
        Image image = new Image();
        image.setBlog(blog);
        image.setDescription(description);
        image.setDimensions(dimensions);

        blog.getImageList().add(image);

        blogRepository2.save(blog);
        return image;
    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Image image = imageRepository2.findById(id).get();

        String dimensions = image.getDimensions();
        String[] di = dimensions.split("X");

        int imageH = Integer.parseInt(di[0]);
        int imageW = Integer.parseInt(di[1]);

        String[] newImage = screenDimensions.split("X");
        int screenLen = Integer.parseInt(newImage[0]);
        int screenWid = Integer.parseInt(newImage[1]);


        return (screenLen/imageH) * (screenWid/imageW);
    }
}
