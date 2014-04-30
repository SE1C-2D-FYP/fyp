/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.jsonmodel;

import com.crm.service.CustomJsonDateDeserializer;
import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

/**
 *
 * @author Cliff
 */
public class ActivityNoteJson {

    private String content;
    private String type;
    //fullcalendar attributes
    private Long id;
    private String title;
    private Date start;
    private Date end;

    public ActivityNoteJson() {
    }

    public ActivityNoteJson(Long id, String title, Date start, Date end, String content, String type) {
        this.content = content;
        this.type = type;
        this.id = id;
        this.title = title;
        this.start = start;
        this.end = end;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        this.title = title;
    }

    public Date getStart() {
        return start;
    }

    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    public void setEnd(Date end) {
        this.end = end;
    }
}
