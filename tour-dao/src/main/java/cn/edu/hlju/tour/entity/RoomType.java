package cn.edu.hlju.tour.entity;

import java.io.Serializable;

public class RoomType implements Serializable {
    private Long id;

    private String typeName;

    private String remark;

    private String indexImg;

    private String price;

    private Long hotelId;

    private static final long serialVersionUID = 1L;

    public RoomType(Long id, String typeName, String remark, String indexImg, String price, Long hotelId) {
        this.id = id;
        this.typeName = typeName;
        this.remark = remark;
        this.indexImg = indexImg;
        this.price = price;
        this.hotelId = hotelId;
    }

    public RoomType() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getIndexImg() {
        return indexImg;
    }

    public void setIndexImg(String indexImg) {
        this.indexImg = indexImg == null ? null : indexImg.trim();
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", typeName=").append(typeName);
        sb.append(", remark=").append(remark);
        sb.append(", indexImg=").append(indexImg);
        sb.append(", price=").append(price);
        sb.append(", hotelId=").append(hotelId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}