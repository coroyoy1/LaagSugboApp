package com.google.group.laagsugboapp;

public class UserData {
    String imageUriData;
    String placeData;
    String addressData;
    String descriptionData;

    public UserData()
    {

    }

    public UserData(String imageUriData, String placeData, String addressData, String descriptionData)
    {
        this.imageUriData = imageUriData;
        this.placeData = placeData;
        this.addressData = addressData;
        this.descriptionData = descriptionData;
    }

    public String getImageUriData() {
        return imageUriData;
    }

    public void setImageUriData(String imageUriData) {
        this.imageUriData = imageUriData;
    }

    public String getPlaceData() {
        return placeData;
    }

    public void setPlaceData(String placeData) {
        this.placeData = placeData;
    }

    public String getAddressData() {
        return addressData;
    }

    public void setAddressData(String addressData) {
        this.addressData = addressData;
    }

    public String getDescriptionData() {
        return descriptionData;
    }

    public void setDescriptionData(String descriptionData) {
        this.descriptionData = descriptionData;
    }
}
