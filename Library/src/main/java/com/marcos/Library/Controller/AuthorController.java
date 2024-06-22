package com.marcos.Library.Controller;

import com.marcos.Library.Models.Author;
import com.marcos.Library.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriBuilder;

import java.net.URI;

@RestController
@Validated
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    
    @GetMapping("/{id}")
    public ResponseEntity<Author> GetById(@PathVariable Integer id){
        Author author = this.authorService.GetById(id);
        return ResponseEntity.ok().body(author);
    }
    
    @PostMapping
    @Validated(Author.class)
    public ResponseEntity<Void> Create( @Validated @RequestBody Author entity){
        this.authorService.Create(entity);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(entity.getId()).toUri();
        
        return ResponseEntity.created(uri).build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Void> Update(@RequestBody Author entity, @PathVariable Integer id){
        entity.setId(id);
        this.authorService.Update(entity);
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> Delete(@PathVariable Integer id){
        this.authorService.Delete(id);
        return ResponseEntity.noContent().build();
    }
}
