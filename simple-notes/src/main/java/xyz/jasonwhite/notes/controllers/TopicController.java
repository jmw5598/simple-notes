package xyz.jasonwhite.notes.controllers;

import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.jasonwhite.notes.controllers.resources.SectionResource;
import xyz.jasonwhite.notes.controllers.resources.TopicResource;
import xyz.jasonwhite.notes.model.Section;
import xyz.jasonwhite.notes.model.Topic;
import xyz.jasonwhite.notes.repositories.SectionRepository;
import xyz.jasonwhite.notes.repositories.TopicRepository;
import xyz.jasonwhite.notes.repositories.exceptions.SectionNotFoundException;
import xyz.jasonwhite.notes.repositories.exceptions.TopicNotFoundException;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/topics")
public class TopicController {
    
    private TopicRepository topicRepository;
    private SectionRepository sectionRepository;
    
    @Autowired
    public TopicController(TopicRepository topicRepository, SectionRepository sectionRepository) {
        this.topicRepository = topicRepository;
        this.sectionRepository = sectionRepository;
    }
    
    @GetMapping
    public ResponseEntity<Iterable<Topic>> getTopics() {
        return ResponseEntity.ok(this.topicRepository.findAll());
    }
    
    @PostMapping
    public ResponseEntity<TopicResource> createTopic(@RequestBody Topic topic) {
        Topic newTopic = this.topicRepository.save(topic);
        return ResponseEntity.ok(new TopicResource(newTopic));
    }
    
    @GetMapping(path="/{topicId}")
    public ResponseEntity<TopicResource> getBook(@PathVariable("topicId") Long topicId) {
        return this.topicRepository.findById(topicId)
            .map(b -> ResponseEntity.ok(new TopicResource(b)))
            .orElseThrow(() -> new TopicNotFoundException(topicId));
    }
    
    @DeleteMapping(path="/{topicId}")
    public ResponseEntity<?> deleteBook(@PathVariable("topicId") Long topicId) {
        return this.topicRepository.findById(topicId)
            .map( p -> {
                this.topicRepository.deleteById(topicId);
                return ResponseEntity.noContent().build();
            })
            .orElseThrow(() -> new TopicNotFoundException(topicId));
    }
    
    @GetMapping(path="/{topicId}/sections")
    public ResponseEntity<Set<Section>> getChapters(@PathVariable("topicId") Long topicId) {
        return this.topicRepository.findById(topicId)
            .map(b -> ResponseEntity.ok(b.getSections()))
            .orElseThrow(() -> new TopicNotFoundException(topicId));
    }
    
    @PostMapping(path="/{topicId}/sections")
    public  ResponseEntity<?> createSection(
            @PathVariable("topicId") Long topicId, @RequestBody Section section) {
        
        return this.topicRepository.findById(topicId)
            .map(b -> {
                b.addSection(section);
                this.topicRepository.save(b);
                return ResponseEntity.noContent().build();
            })
            .orElseThrow(() -> new TopicNotFoundException(topicId));
        
    }
    
    @GetMapping(path="/{topicId}/sections/{sectionId}")
    public ResponseEntity<Section> getSection(
            @PathVariable("topicId") Long topicId, @PathVariable("sectionId") Long sectionId) throws Exception {
        
        return this.sectionRepository.findById(sectionId)
            .map(s -> ResponseEntity.ok(s))
            .orElseThrow(() -> new SectionNotFoundException(sectionId));
    }
    
    @PutMapping(path="/{topicId}/sections/{sectionId}")
    public ResponseEntity<Section> updateSection(
            @PathVariable("topicId") Long topicId, @PathVariable("sectionId") Long sectionId, @RequestBody Section section) {

        return this.sectionRepository.findById(sectionId)
            .map(s -> {
                BeanUtils.copyProperties(section, s);
                this.sectionRepository.save(s);
                return ResponseEntity.ok(s);
            })
            .orElseThrow(() -> new SectionNotFoundException(sectionId));
    }
    
}
