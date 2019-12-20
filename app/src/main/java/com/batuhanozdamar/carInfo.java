package com.batuhanozdamar;

public class carInfo {

    int imageId;
    String brand;
    String seat;
    String km;
    String engine;
    String price;

    public carInfo(int imageId, String brand, String seat, String km, String engine, String price) {
        this.imageId = imageId;
        this.brand = brand;
        this.seat = seat;
        this.km = km;
        this.engine = engine;
        this.price = price;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "carInfo{" +
                "imageId=" + imageId +
                ", brand='" + brand + '\'' +
                ", seat='" + seat + '\'' +
                ", km=" + km +
                ", engine='" + engine + '\'' +
                ", price=" + price +
                '}';
    }
}
