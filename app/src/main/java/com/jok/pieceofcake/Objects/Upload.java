package com.jok.pieceofcake.Objects;
/**
 * Represents the url and name of an image of a pastry,
 *
 */

import java.io.Serializable;

public class Upload implements Serializable {
    private  String name;
    private String imageURL;

    public Upload(String name, String imageURL) {
        if(name.trim().equals("")){
            name = "No Name";
        }
        this.name = name;
        this.imageURL = imageURL;
    }

    public Upload() {
        // needed for fireBase
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
