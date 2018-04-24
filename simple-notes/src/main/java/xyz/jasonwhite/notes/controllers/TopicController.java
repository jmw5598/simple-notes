package xyz.jasonwhite.notes.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import club.caliope.udc.InputFormat;
import club.caliope.udc.OutputFormat;
import xyz.jasonwhite.notes.controllers.resources.TopicResource;
import xyz.jasonwhite.notes.model.Topic;
import xyz.jasonwhite.notes.repositories.TopicRepository;
import xyz.jasonwhite.notes.repositories.exceptions.TopicNotFoundException;
import xyz.jasonwhite.notes.services.DocumentService;
import xyz.jasonwhite.notes.utilities.TopicExportUtility;

@RestController
@RequestMapping("/v1/topics")
public class TopicController {
    
    private TopicRepository topicRepository;
    private DocumentService documentService;
    
    @Autowired
    public TopicController(TopicRepository topicRepository, DocumentService documentService) {
        this.topicRepository = topicRepository;
        this.documentService = documentService;
    }
    
    @GetMapping
    public ResponseEntity<Iterable<Topic>> getTopics() {
        return ResponseEntity.ok(this.topicRepository.findAll());
    }
    
    @PostMapping
    public ResponseEntity<TopicResource> createTopic(@RequestBody Topic topic, Authentication authentication) {
        String user = authentication.getName();
        topic.setOwner(user);
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
    public ResponseEntity<?> deleteTopic(@PathVariable("topicId") Long topicId) {
        return this.topicRepository.findById(topicId)
            .map( p -> {
                this.topicRepository.deleteById(topicId);
                return ResponseEntity.noContent().build();
            })
            .orElseThrow(() -> new TopicNotFoundException(topicId));
    }
    
    @GetMapping(path="/{topicId}/export", produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<?> exportTopic(
            @PathVariable("topicId") Long topicId,
            @RequestParam(value="fileType", defaultValue="txt") OutputFormat fileType) throws IOException {
        
        return null;
        
    }
    
}
