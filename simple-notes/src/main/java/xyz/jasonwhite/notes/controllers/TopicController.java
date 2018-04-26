package xyz.jasonwhite.notes.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

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
import xyz.jasonwhite.notes.model.Section;
import xyz.jasonwhite.notes.model.Topic;
import xyz.jasonwhite.notes.repositories.SectionRepository;
import xyz.jasonwhite.notes.repositories.TopicRepository;
import xyz.jasonwhite.notes.repositories.exceptions.TopicNotFoundException;
import xyz.jasonwhite.notes.services.DocumentService;
import xyz.jasonwhite.notes.utilities.TopicExportUtility;

@RestController
@RequestMapping("/v1/topics")
public class TopicController {
    
    private TopicRepository topicRepository;
    private SectionRepository sectionRepository;
    private DocumentService documentService;
    
    @Autowired
    public TopicController(TopicRepository topicRepository, SectionRepository sectionRepository, DocumentService documentService) {
        this.topicRepository = topicRepository;
        this.sectionRepository = sectionRepository;
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
        Topic topic = this.validateTopic(topicId);
        return ResponseEntity.ok(new TopicResource(topic));
    }
    
    @DeleteMapping(path="/{topicId}")
    public ResponseEntity<?> deleteTopic(@PathVariable("topicId") Long topicId) {
        Topic topic = this.validateTopic(topicId);
        this.sectionRepository.deleteAllByTopic(topic);
        this.topicRepository.deleteById(topicId);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping(path="/{topicId}/export", produces=MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> exportTopic(
            @PathVariable("topicId") Long topicId,
            @RequestParam(value="format", defaultValue="pdf") OutputFormat format) throws IOException {
        
        // TODO: Clean this up with a service
        Topic topic = this.validateTopic(topicId);
        Iterable<Section> sections = this.sectionRepository.findAllByTopic(topic);
        
        String markdown = TopicExportUtility.generateMarkdown(topic, sections);
        String filename = (topic.getId() + "-" + topic.getTitle()).replaceAll(" ","-").toLowerCase() + "." + format.name().toLowerCase();
        File file = this.documentService.convert(markdown, InputFormat.MARKDOWN, OutputFormat.LATEX);
            
        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        
        HttpHeaders headers = new HttpHeaders(); 
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Content-Disposition");
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
            
        return ResponseEntity.ok()
            .headers(headers)
            .contentLength(file.length())
            .contentType(MediaType.parseMediaType("application/pdf"))
            .body(resource);
        
    }
    
    private Topic validateTopic(Long topicId) {
        return this.topicRepository.findById(topicId)
            .map(b -> b)
            .orElseThrow(() -> new TopicNotFoundException(topicId));
    }
    
}
