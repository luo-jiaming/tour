package cn.edu.hlju.tour.entity;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private Long id;

    private String content;

    private Long fromUid;

    private Long toUid;

    private Date time;

    private Long status;

    private Long type;

    private static final long serialVersionUID = 1L;

    public Message(Long id, String content, Long fromUid, Long toUid, Date time, Long status, Long type) {
        this.id = id;
        this.content = content;
        this.fromUid = fromUid;
        this.toUid = toUid;
        this.time = time;
        this.status = status;
        this.type = type;
    }

    public Message() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", content=").append(content);
        sb.append(", fromUid=").append(fromUid);
        sb.append(", toUid=").append(toUid);
        sb.append(", time=").append(time);
        sb.append(", status=").append(status);
        sb.append(", type=").append(type);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}