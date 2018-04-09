package xyz.jasonwhite.notes.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import xyz.jasonwhite.notes.model.Topic;

@Repository
public interface TopicRepository extends CrudRepository<Topic, Long> {

}
