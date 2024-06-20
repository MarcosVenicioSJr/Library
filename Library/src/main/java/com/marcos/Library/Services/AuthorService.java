package com.marcos.Library.Services;

import com.marcos.Library.Models.Author;
import com.marcos.Library.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    
    public Author GetByName(String name){
        Optional<Author> author = this.authorRepository.findAuthorByNameAnd(name);
        return author.orElseThrow(() -> new RuntimeException("Author not found"));
    }
    
    public Author GetById(Integer id){
        Optional<Author> author = this.authorRepository.findById(id);
        return author.orElseThrow(() -> new RuntimeException("Author not found"));
    }
    
    @Transactional
    public Author Create(Author entity){
        entity.setId(null);
        entity = this.authorRepository.save(entity);
        return entity;
    }
    
    @Transactional
    public Author Update(Author entity){
        Author entityUpdatable = GetById(entity.getId());
        entityUpdatable.setName(entity.getName());
        return this.authorRepository.save(entityUpdatable);
    }
    
    public void Delete(Integer id) {
        GetById(id);
        try{
            this.authorRepository.deleteById(id);
        }
        catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }
    }
}
