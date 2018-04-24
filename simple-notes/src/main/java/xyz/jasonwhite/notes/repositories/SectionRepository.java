package xyz.jasonwhite.notes.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import xyz.jasonwhite.notes.model.Section;
import xyz.jasonwhite.notes.model.Topic;

@Repository
public interface SectionRepository extends CrudRepository<Section, Long> {
    
    @Query("SELECT s FROM Section s WHERE s.topic = :topic AND s.topic.owner = ?#{ authentication.name } ORDER BY s.title ASC, s.createdOn ASC")
    Iterable<Section> findAllByTopic(@Param("topic") Topic topic);
    
    @Query("SELECT s FROM Section s WHERE s.id = :id AND s.topic = :topic AND s.topic.owner = ?#{ authentication.name }")
    Optional<Section> findByIdAndTopic(@Param("id") Long id, @Param("topic") Topic topic);

}
