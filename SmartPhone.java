package com.tai06.dothetai.appmarketphone;

public class SmartPhone {
    private int id_maloaihang;
    private int id_phone;
    private String name;
    private int price;
    private String images;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }



    public SmartPhone(int id_maloaihang, int id_phone, String name, int price, String images) {
        this.id_maloaihang = id_maloaihang;
        this.id_phone = id_phone;
        this.name = name;
        this.price = price;
        this.images = images;
    }

    public int getId_maloaihang() {
        return id_maloaihang;
    }

    public void setId_maloaihang(int id_maloaihang) {
        this.id_maloaihang = id_maloaihang;
    }

    public int getId_phone() {
        return id_phone;
    }

    public void setId_phone(int id_phone) {
        this.id_phone = id_phone;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}
