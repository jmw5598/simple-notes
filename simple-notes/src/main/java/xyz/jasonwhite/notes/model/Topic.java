package xyz.jasonwhite.notes.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Topic {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;
    
    @NotNull
    private String title;
    
    @NotNull
    private String synopsis;
    
    @Column(updatable=false, insertable=true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    
    @Column(updatable=true, insertable=true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;
    
    @Enumerated(EnumType.STRING)
    public Permission permission = Permission.PRIVATE;
    
    @NotNull
    private String owner;
    
    @ManyToMany(cascade={ CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
        name = "TOPIC_CATEGORY",
        joinColumns = @JoinColumn(name = "post_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @JsonIgnoreProperties(value={"topics"})
    private Set<Category> categories = new HashSet<>();
    
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

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
    
    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public void addTag(Category category) {
        categories.add(category);
        category.getTopics().add(this);
    }
    
    public void removeTag(Category category) {
        categories.remove(category);
        category.getTopics().remove(this);
    }
    
}
