package cn.edu.hlju.tour.entity;

import java.io.Serializable;

public class Spot implements Serializable {
    private Long id;

    private String spotName;

    private String spotIntroduce;

    private String spendTime;

    private String traffic;

    private String ticket;

    private String openTime;

    private String location;

    private String coordinate;

    private String indexImg;

    private static final long serialVersionUID = 1L;

    public Spot(Long id, String spotName, String spotIntroduce, String spendTime, String traffic, String ticket, String openTime, String location, String coordinate, String indexImg) {
        this.id = id;
        this.spotName = spotName;
        this.spotIntroduce = spotIntroduce;
        this.spendTime = spendTime;
        this.traffic = traffic;
        this.ticket = ticket;
        this.openTime = openTime;
        this.location = location;
        this.coordinate = coordinate;
        this.indexImg = indexImg;
    }

    public Spot() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpotName() {
        return spotName;
    }

    public void setSpotName(String spotName) {
        this.spotName = spotName == null ? null : spotName.trim();
    }

    public String getSpotIntroduce() {
        return spotIntroduce;
    }

    public void setSpotIntroduce(String spotIntroduce) {
        this.spotIntroduce = spotIntroduce == null ? null : spotIntroduce.trim();
    }

    public String getSpendTime() {
        return spendTime;
    }

    public void setSpendTime(String spendTime) {
        this.spendTime = spendTime == null ? null : spendTime.trim();
    }

    public String getTraffic() {
        return traffic;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic == null ? null : traffic.trim();
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket == null ? null : ticket.trim();
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime == null ? null : openTime.trim();
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

    public String getIndexImg() {
        return indexImg;
    }

    public void setIndexImg(String indexImg) {
        this.indexImg = indexImg == null ? null : indexImg.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", spotName=").append(spotName);
        sb.append(", spotIntroduce=").append(spotIntroduce);
        sb.append(", spendTime=").append(spendTime);
        sb.append(", traffic=").append(traffic);
        sb.append(", ticket=").append(ticket);
        sb.append(", openTime=").append(openTime);
        sb.append(", location=").append(location);
        sb.append(", coordinate=").append(coordinate);
        sb.append(", indexImg=").append(indexImg);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}