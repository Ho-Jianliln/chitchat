package entity;

import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Message {
    private Integer id;
    private String context;
    private String fromId;
    private String toId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp date;
    private Integer status;

    public Message() {}

    public Message(Integer id, String context, String fromId,
                   String toId, Timestamp date, Integer status) {
        super();
        this.id = id;
        this.context = context;
        this.fromId = fromId;
        this.toId = toId;
        this.date = date;
        this.status = status;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getContext() {
        return context;
    }
    public void setContext(String context) {
        this.context = context;
    }
    public String getFromId() {
        return fromId;
    }
    public void setFromId(String fromId) {
        this.fromId = fromId;
    }
    public String getToId() {
        return toId;
    }
    public void setToId(String toId) {
        this.toId = toId;
    }
    public Timestamp getDate() {
        return date;
    }
    public void setDate(Timestamp date) {
        this.date = date;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
}
