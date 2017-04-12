package cn.edu.hlju.tour.entity;

import java.io.Serializable;
import java.util.Date;

public class SpotComment implements Serializable {
    private Long id;

    private Long spotId;

    private String content;

    private Long userId;

    private Date time;

    private static final long serialVersionUID = 1L;

    public SpotComment(Long id, Long spotId, String content, Long userId, Date time) {
        this.id = id;
        this.spotId = spotId;
        this.content = content;
        this.userId = userId;
        this.time = time;
    }

    public SpotComment() {
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
        sb.append(", spotId=").append(spotId);
        sb.append(", content=").append(content);
        sb.append(", userId=").append(userId);
        sb.append(", time=").append(time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}