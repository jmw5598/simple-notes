package xyz.jasonwhite.notes.repositories.exceptions;

public class TopicNotFoundException extends RuntimeException {
        
    private Long id;
    
    public TopicNotFoundException(final Long id) {
        super("Topic could not be found with id: " + id);
        this.id = id;
    }
    
    public Long getId() {
        return this.id;
    }
    
}
