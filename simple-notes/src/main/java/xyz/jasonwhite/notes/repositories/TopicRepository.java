package xyz.jasonwhite.notes.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import xyz.jasonwhite.notes.model.Topic;

@Repository
public interface TopicRepository extends CrudRepository<Topic, Long> {

    @Override
    @Query("SELECT t FROM Topic t WHERE t.owner = ?#{ authentication.name }")
    Iterable<Topic> findAll();

    @Override
    @Query("SELECT t FROM Topic t WHERE t.id = :id AND t.owner = ?#{ authentication.name }")
    Optional<Topic> findById(@Param("id") Long id);
    
    
 
}
