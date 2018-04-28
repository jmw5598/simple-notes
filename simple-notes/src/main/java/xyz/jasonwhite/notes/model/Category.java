package xyz.jasonwhite.notes.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Category {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;
    
    @NotNull
    private String description;
    
    @ManyToMany(mappedBy = "categories")
    private Set<Topic> topics = new HashSet<>();
    
    public Category() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description.toLowerCase();
    }
    
    public Set<Topic> getTopics() {
        return topics;
    }

}
