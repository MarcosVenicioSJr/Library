package com.marcos.Library.Repository;

import com.marcos.Library.Models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {    
    Optional<Author> findAuthorByName(String name);
}
