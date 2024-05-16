package com.enset.books_android.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Book implements Serializable {
    @SerializedName("volumeInfo")
    private VolumeInfo volumeInfo;

    public Book() {}
    public Book(VolumeInfo volumeInfo) { this.volumeInfo = volumeInfo; }

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }
    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }
}
