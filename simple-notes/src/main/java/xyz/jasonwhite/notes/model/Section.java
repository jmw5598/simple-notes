package xyz.jasonwhite.notes.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Section {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;
    
    @NotNull
    private String title;
    
    @NotNull
    private String synopsis;
    
    private String notes;
    
    @Column(updatable=false, insertable=true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    
    @Column(updatable=true, insertable=true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;
    
    @ManyToOne
    @JoinColumn(name="topic_id")
    @JsonIgnore
    private Topic topic;
    
    @PrePersist
    protected void onCreate() {
        this.createdOn = new Date();
        this.lastModified = new Date();
    }
    
    @PreUpdate
    protected void onUpdate() {
        this.lastModified = new Date();
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

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
    
}
