package cn.edu.hlju.tour.entity;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private Long id;

    private String content;

    private Long fromUid;

    private Long toUid;

    private Date time;

    private String status;

    private static final long serialVersionUID = 1L;

    public Message(Long id, String content, Long fromUid, Long toUid, Date time, String status) {
        this.id = id;
        this.content = content;
        this.fromUid = fromUid;
        this.toUid = toUid;
        this.time = time;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}