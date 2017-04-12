package cn.edu.hlju.tour.entity;

import java.io.Serializable;
import java.util.Date;

public class TravelComment implements Serializable {
    private Long id;

    private Long travelId;

    private String content;

    private Long fromUid;

    private Long toUid;

    private Date time;

    private static final long serialVersionUID = 1L;

    public TravelComment(Long id, Long travelId, String content, Long fromUid, Long toUid, Date time) {
        this.id = id;
        this.travelId = travelId;
        this.content = content;
        this.fromUid = fromUid;
        this.toUid = toUid;
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

    public Long getFromUid() {
        return fromUid;
    }

    public void setFromUid(Long fromUid) {
        this.fromUid = fromUid;
    }

    public Long getToUid() {
        return toUid;
    }

    public void setToUid(Long toUid) {
        this.toUid = toUid;
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
        sb.append(", fromUid=").append(fromUid);
        sb.append(", toUid=").append(toUid);
        sb.append(", time=").append(time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}