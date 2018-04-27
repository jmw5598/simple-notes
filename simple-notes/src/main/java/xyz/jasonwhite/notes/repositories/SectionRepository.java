package xyz.jasonwhite.notes.repositories;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import xyz.jasonwhite.notes.model.Permission;
import xyz.jasonwhite.notes.model.Section;
import xyz.jasonwhite.notes.model.Topic;

@Repository
public interface SectionRepository extends CrudRepository<Section, Long> {
    
    @Query(
        "SELECT s FROM Section s " +
        "WHERE s.topic = :topic AND " +
            "(s.topic.owner = ?#{ authentication.name } OR s.topic.permission = 'PUBLIC') " +
        "ORDER BY s.title ASC, s.createdOn ASC")
    Iterable<Section> findAllByTopic(@Param("topic") Topic topic);
    
    @Query(
        "SELECT s FROM Section s " +
        "WHERE s.id = :id AND s.topic = :topic AND " +
            "(s.topic.owner = ?#{ authentication.name } OR s.topic.permission = 'PUBLIC')")
    Optional<Section> findByIdAndTopic(@Param("id") Long id, @Param("topic") Topic topic);
    
    @Modifying
    @Transactional
    @PreAuthorize("#topic.owner == authentication.name")
    @Query("DELETE FROM Section s WHERE s.topic = :topic")
    void deleteAllByTopic(@Param("topic") Topic topic);

    @Override
    @PreAuthorize("#topic.owner == authentication.name")
    void deleteById(Long id);
    
}
