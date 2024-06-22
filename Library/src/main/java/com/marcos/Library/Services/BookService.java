package com.marcos.Library.Services;

import com.marcos.Library.Models.Author;
import com.marcos.Library.Models.Book;
import com.marcos.Library.Repository.AuthorRepository;
import com.marcos.Library.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    
    public Book GetByTitle(String title){
        Optional<Book> book = this.bookRepository.getBookByTitle(title);
        return book.orElseThrow(() -> new RuntimeException("Book not found"));
    }
    
    public Book GetById(Integer id){
        Optional<Book> book = this.bookRepository.findById(id);
        return book.orElseThrow(() -> new RuntimeException("Book not found"));
    }
    
    public List<Book> GetByAuthor(String authorName){
        Optional<Author> author = this.authorRepository.findAuthorByName(authorName);
        author.orElseThrow(() -> {
            return new RuntimeException("Author not found!");
        });
        
        Optional<List<Book>> booksList = this.bookRepository.getBooksByAuthor_Name(authorName);
        return booksList.orElseThrow(() -> new RuntimeException("Books not found"));
    }
    
    @Transactional
    public Book Create(Book entity) {
        Optional<Author> author = authorRepository.findAuthorByName(entity.getAuthor().getName());
        
        if (author.isEmpty()) {
            Author savedAuthor = authorRepository.save(entity.getAuthor());
            entity.setAuthor(savedAuthor);
        } else {
            entity.setAuthor(author.get());
        }
        
        entity.setId(null);
        return bookRepository.save(entity);
    }

    
    @Transactional
    public Book Update(Book entity){
        Book book = this.bookRepository.getById(entity.getId());
        
        if(book != null) {
            book.setDescription(entity.getDescription());
            book.setGender(entity.getGender());
            book.setTitle(entity.getTitle());
            
            return this.bookRepository.save(book);
        }else {
            return this.bookRepository.save(entity);
        }
    }
    @Transactional
    public void Delete(Integer id) {
        GetById(id);
        try{
            this.bookRepository.deleteById(id);
        }
        catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }
    }
}
