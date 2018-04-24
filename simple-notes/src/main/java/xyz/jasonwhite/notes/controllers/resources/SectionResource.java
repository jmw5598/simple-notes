package xyz.jasonwhite.notes.controllers.resources;

import org.springframework.hateoas.ResourceSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import xyz.jasonwhite.notes.controllers.TopicController;
import xyz.jasonwhite.notes.model.Section;
import xyz.jasonwhite.notes.model.Topic;

public class SectionResource extends ResourceSupport {
    
    private final Section section;
    
    public SectionResource(Topic topic, Section section) {
        this.section = section;
        add(linkTo(TopicController.class).slash(topic.getId()).withRel("topic"));
        add(linkTo(TopicController.class).slash(topic.getId()).slash("sections").withRel("sections"));
        add(linkTo(TopicController.class).slash(topic.getId()).slash("sections").slash(section.getId()).withRel("self"));
    }
    
    public Section getSection() {
        return this.section;
    }

}
