package xyz.jasonwhite.notes.repositories;

import org.springframework.data.repository.CrudRepository;

import xyz.jasonwhite.notes.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    
    Iterable<Category> findByDescriptionIn(Iterable<String> descriptions);

}
