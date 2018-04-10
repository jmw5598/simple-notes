package xyz.jasonwhite.notes.repositories.exceptions;

public class SectionNotFoundException extends RuntimeException {
    
    private Long id;
    
    public SectionNotFoundException(final Long id) {
        super("Section could not be found with id: " + id);
        this.id = id;
    }
    
    public Long getId() {
        return this.id;
    }

}
