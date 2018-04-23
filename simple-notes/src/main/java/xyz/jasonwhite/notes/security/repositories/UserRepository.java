package xyz.jasonwhite.notes.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.jasonwhite.notes.model.security.User;


/**
 * Created by stephan on 20.03.16.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
