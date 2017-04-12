package cn.edu.hlju.tour.entity;

import java.io.Serializable;

public class Flight implements Serializable {
    private Long id;

    private String name;

    private String startTime;

    private String endTime;

    private String fromLoc;

    private String toLoc;

    private String startTerminal;

    private String endTerminal;

    private String price;

    private static final long serialVersionUID = 1L;

    public Flight(Long id, String name, String startTime, String endTime, String fromLoc, String toLoc, String startTerminal, String endTerminal, String price) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.fromLoc = fromLoc;
        this.toLoc = toLoc;
        this.startTerminal = startTerminal;
        this.endTerminal = endTerminal;
        this.price = price;
    }

    public Flight() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    public String getFromLoc() {
        return fromLoc;
    }

    public void setFromLoc(String fromLoc) {
        this.fromLoc = fromLoc == null ? null : fromLoc.trim();
    }

    public String getToLoc() {
        return toLoc;
    }

    public void setToLoc(String toLoc) {
        this.toLoc = toLoc == null ? null : toLoc.trim();
    }

    public String getStartTerminal() {
        return startTerminal;
    }

    public void setStartTerminal(String startTerminal) {
        this.startTerminal = startTerminal == null ? null : startTerminal.trim();
    }

    public String getEndTerminal() {
        return endTerminal;
    }

    public void setEndTerminal(String endTerminal) {
        this.endTerminal = endTerminal == null ? null : endTerminal.trim();
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", fromLoc=").append(fromLoc);
        sb.append(", toLoc=").append(toLoc);
        sb.append(", startTerminal=").append(startTerminal);
        sb.append(", endTerminal=").append(endTerminal);
        sb.append(", price=").append(price);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}