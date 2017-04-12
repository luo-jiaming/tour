package cn.edu.hlju.tour.entity;

import java.io.Serializable;

public class Hotel implements Serializable {
    private Long id;

    private String hotelName;

    private String hotelIntroduce;

    private String hotelLevel;

    private String location;

    private String coordinate;

    private String phone;

    private String indexImg;

    private String wifi;

    private String bath;

    private String park;

    private String restaurant;

    private String price;

    private Long spotId;

    private static final long serialVersionUID = 1L;

    public Hotel(Long id, String hotelName, String hotelIntroduce, String hotelLevel, String location, String coordinate, String phone, String indexImg, String wifi, String bath, String park, String restaurant, String price, Long spotId) {
        this.id = id;
        this.hotelName = hotelName;
        this.hotelIntroduce = hotelIntroduce;
        this.hotelLevel = hotelLevel;
        this.location = location;
        this.coordinate = coordinate;
        this.phone = phone;
        this.indexImg = indexImg;
        this.wifi = wifi;
        this.bath = bath;
        this.park = park;
        this.restaurant = restaurant;
        this.price = price;
        this.spotId = spotId;
    }

    public Hotel() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName == null ? null : hotelName.trim();
    }

    public String getHotelIntroduce() {
        return hotelIntroduce;
    }

    public void setHotelIntroduce(String hotelIntroduce) {
        this.hotelIntroduce = hotelIntroduce == null ? null : hotelIntroduce.trim();
    }

    public String getHotelLevel() {
        return hotelLevel;
    }

    public void setHotelLevel(String hotelLevel) {
        this.hotelLevel = hotelLevel == null ? null : hotelLevel.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate == null ? null : coordinate.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getIndexImg() {
        return indexImg;
    }

    public void setIndexImg(String indexImg) {
        this.indexImg = indexImg == null ? null : indexImg.trim();
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi == null ? null : wifi.trim();
    }

    public String getBath() {
        return bath;
    }

    public void setBath(String bath) {
        this.bath = bath == null ? null : bath.trim();
    }

    public String getPark() {
        return park;
    }

    public void setPark(String park) {
        this.park = park == null ? null : park.trim();
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant == null ? null : restaurant.trim();
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }

    public Long getSpotId() {
        return spotId;
    }

    public void setSpotId(Long spotId) {
        this.spotId = spotId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", hotelName=").append(hotelName);
        sb.append(", hotelIntroduce=").append(hotelIntroduce);
        sb.append(", hotelLevel=").append(hotelLevel);
        sb.append(", location=").append(location);
        sb.append(", coordinate=").append(coordinate);
        sb.append(", phone=").append(phone);
        sb.append(", indexImg=").append(indexImg);
        sb.append(", wifi=").append(wifi);
        sb.append(", bath=").append(bath);
        sb.append(", park=").append(park);
        sb.append(", restaurant=").append(restaurant);
        sb.append(", price=").append(price);
        sb.append(", spotId=").append(spotId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}