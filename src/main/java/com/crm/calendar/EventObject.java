
package com.crm.calendar;


/**
 *
 * @author comon
 */
public class EventObject {
    private String id;
    private String title;
    private String start;
    private String end;
    private String description;
    private boolean editable;

    public EventObject() {
    }

    public EventObject(String id, String title, String start, String end, String description, boolean editable) {
        this.id = id;
        this.title = title;
        this.start = start;
        this.end = end;
        this.description = description;
        this.editable = editable;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String getDescription() {
        return description;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

   

    
}
