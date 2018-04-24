package xyz.jasonwhite.notes.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.jasonwhite.notes.controllers.resources.SectionResource;
import xyz.jasonwhite.notes.model.Section;
import xyz.jasonwhite.notes.model.Topic;
import xyz.jasonwhite.notes.repositories.SectionRepository;
import xyz.jasonwhite.notes.repositories.TopicRepository;
import xyz.jasonwhite.notes.repositories.exceptions.SectionNotFoundException;
import xyz.jasonwhite.notes.repositories.exceptions.TopicNotFoundException;

@RestController
@RequestMapping("/v1/topics/")
public class SectionController {
    
    private TopicRepository topicRepository;
    private SectionRepository sectionRepository;
    
    @Autowired
    public SectionController(
            TopicRepository topicRepository, SectionRepository sectionRepository) {
        this.topicRepository = topicRepository;
        this.sectionRepository = sectionRepository;
    }
    
    @GetMapping(path="/{topicId}/sections")
    public ResponseEntity<Iterable<Section>> getSections(@PathVariable("topicId") Long topicId) {
        Topic topic = this.validateTopic(topicId);
        return ResponseEntity.ok(this.sectionRepository.findAllByTopic(topic));
    }
    
    @PostMapping(path="/{topicId}/sections")
    public  ResponseEntity<?> createSection(
            @PathVariable("topicId") Long topicId, @RequestBody Section section) {
        
        Topic topic = this.validateTopic(topicId);
        section.setTopic(topic);
        Section result = this.sectionRepository.save(section);
        return ResponseEntity.ok(new SectionResource(topic, result));
    }
    
    @GetMapping(path="/{topicId}/sections/{sectionId}")
    public ResponseEntity<SectionResource> getSection(
            @PathVariable("topicId") Long topicId, @PathVariable("sectionId") Long sectionId) {
        
        Topic topic = this.validateTopic(topicId);
        return this.sectionRepository.findByIdAndTopic(sectionId, topic)
            .map(s -> ResponseEntity.ok(new SectionResource(topic, s)))
            .orElseThrow(() -> new SectionNotFoundException(sectionId));
    }
    
    @PutMapping(path="/{topicId}/sections/{sectionId}")
    public ResponseEntity<SectionResource> updateSection(
            @PathVariable("topicId") Long topicId, @PathVariable("sectionId") Long sectionId, @RequestBody Section section) {
        
        Topic topic = this.validateTopic(topicId);
        return this.sectionRepository.findByIdAndTopic(sectionId, topic)
            .map(s -> {
                BeanUtils.copyProperties(section, s);
                this.sectionRepository.save(s);
                return ResponseEntity.ok(new SectionResource(topic, s));
            })
            .orElseThrow(() -> new SectionNotFoundException(sectionId));
    }
    
    @DeleteMapping(path="/{topicId}/sections/{sectionId}")
    public ResponseEntity<?> deleteSection(
            @PathVariable("topicId") Long topicId, @PathVariable("sectionId") Long sectionId) {
        
        Topic topic = this.validateTopic(topicId);
        return this.sectionRepository.findByIdAndTopic(sectionId, topic)
            .map( p -> {
                this.sectionRepository.deleteById(sectionId);
                return ResponseEntity.noContent().build();
            })
            .orElseThrow(() -> new SectionNotFoundException(sectionId));
    }
    
    private Topic validateTopic(Long topicId) {
        return this.topicRepository.findById(topicId)
            .map(b -> b)
            .orElseThrow(() -> new TopicNotFoundException(topicId));
    }

}
