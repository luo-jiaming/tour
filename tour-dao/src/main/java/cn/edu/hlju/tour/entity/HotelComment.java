package cn.edu.hlju.tour.entity;

import java.io.Serializable;
import java.util.Date;

public class HotelComment implements Serializable {
    private Long id;

    private Long hotelId;

    private String content;

    private Long userId;

    private Date time;

    private static final long serialVersionUID = 1L;

    public HotelComment(Long id, Long hotelId, String content, Long userId, Date time) {
        this.id = id;
        this.hotelId = hotelId;
        this.content = content;
        this.userId = userId;
        this.time = time;
    }

    public HotelComment() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", hotelId=").append(hotelId);
        sb.append(", content=").append(content);
        sb.append(", userId=").append(userId);
        sb.append(", time=").append(time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}