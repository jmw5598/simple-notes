package xyz.jasonwhite.notes.controllers.resources;

import org.springframework.hateoas.ResourceSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import xyz.jasonwhite.notes.controllers.TopicController;
import xyz.jasonwhite.notes.model.Topic;

public class TopicResource extends ResourceSupport {
    
    private final Topic topic;
    
    public TopicResource(Topic topic) {
        this.topic = topic;
        add(linkTo(TopicController.class).slash(topic.getId()).withRel("self"));
        add(linkTo(TopicController.class).slash(topic.getId()).slash("sections").withRel("sections"));
    }
    
    public Topic getTopic() {
        return this.topic;
    }

}
