package xyz.jasonwhite.notes.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import xyz.jasonwhite.notes.model.Section;

@Repository
public interface SectionRepository extends CrudRepository<Section, Long> {

}
