package cn.edu.hlju.tour.entity;

import java.io.Serializable;
import java.util.Date;

public class Travel implements Serializable {
    private Long id;

    private String title;

    private Long spotId;

    private Date time;

    private String img;

    private Long userId;

    private Long status;

    private String content;

    private static final long serialVersionUID = 1L;

    public Travel(Long id, String title, Long spotId, Date time, String img, Long userId, Long status, String content) {
        this.id = id;
        this.title = title;
        this.spotId = spotId;
        this.time = time;
        this.img = img;
        this.userId = userId;
        this.status = status;
        this.content = content;
    }

    public Travel() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Long getSpotId() {
        return spotId;
    }

    public void setSpotId(Long spotId) {
        this.spotId = spotId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", spotId=").append(spotId);
        sb.append(", time=").append(time);
        sb.append(", img=").append(img);
        sb.append(", userId=").append(userId);
        sb.append(", status=").append(status);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}