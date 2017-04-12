package cn.edu.hlju.tour.entity;

import java.io.Serializable;

public class SpotImg implements Serializable {
    private Long id;

    private Long spotId;

    private String img;

    private static final long serialVersionUID = 1L;

    public SpotImg(Long id, Long spotId, String img) {
        this.id = id;
        this.spotId = spotId;
        this.img = img;
    }

    public SpotImg() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSpotId() {
        return spotId;
    }

    public void setSpotId(Long spotId) {
        this.spotId = spotId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", spotId=").append(spotId);
        sb.append(", img=").append(img);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}