package cn.edu.hlju.tour.entity;

import java.io.Serializable;
import java.util.Date;

public class TravelComment implements Serializable {
    private Long id;

    private Long travelId;

    private String content;

    private Long userId;

    private Long applyCid;

    private Date time;

    private static final long serialVersionUID = 1L;

    public TravelComment(Long id, Long travelId, String content, Long userId, Long applyCid, Date time) {
        this.id = id;
        this.travelId = travelId;
        this.content = content;
        this.userId = userId;
        this.applyCid = applyCid;
        this.time = time;
    }

    public TravelComment() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTravelId() {
        return travelId;
    }

    public void setTravelId(Long travelId) {
        this.travelId = travelId;
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

    public Long getApplyCid() {
        return applyCid;
    }

    public void setApplyCid(Long applyCid) {
        this.applyCid = applyCid;
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
        sb.append(", travelId=").append(travelId);
        sb.append(", content=").append(content);
        sb.append(", userId=").append(userId);
        sb.append(", applyCid=").append(applyCid);
        sb.append(", time=").append(time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}