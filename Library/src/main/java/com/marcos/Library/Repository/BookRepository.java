package com.marcos.Library.Repository;

import com.marcos.Library.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> getBookByTitle(String name);
    Optional<List<Book>> getBooksByAuthor_Name(String name);
}
