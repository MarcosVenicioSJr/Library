package com.marcos.Library.Controller;

import com.marcos.Library.Models.Book;
import com.marcos.Library.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/books")
@Validated
public class BookController {
    
    @Autowired
    private BookService bookService;
    
    @GetMapping("/{id}")
    public ResponseEntity<Book> GetById(@PathVariable Integer id){
        Book book = this.bookService.GetById(id);
        return ResponseEntity.ok().body(book);
    }
    
    @PostMapping
 //   @Validated(Book.class)
    public ResponseEntity<Void> Create(@RequestBody Book entity){
        this.bookService.Create(entity);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(entity.getId()).toUri();
        
        return ResponseEntity.created(uri).build();
    }
    
    @PutMapping("/{id}")
    @Validated(Book.class)
    public ResponseEntity<Void> Update(@RequestBody Book entity, @PathVariable Integer id){
        entity.setId(id);
        this.bookService.Update(entity);
        
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> Delete(@PathVariable Integer id){
        this.bookService.Delete(id);
        return ResponseEntity.noContent().build();
    }
}
