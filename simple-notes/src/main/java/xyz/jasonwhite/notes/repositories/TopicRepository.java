package xyz.jasonwhite.notes.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Repository;

import xyz.jasonwhite.notes.model.Topic;

@Repository
public interface TopicRepository extends CrudRepository<Topic, Long> {

    @Override
    @PostFilter(value = "filterObject.owner == authentication.name") // inefficient on large lists
    Iterable<Topic> findAll();
 
}
