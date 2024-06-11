package com.marcos.Library.Models;

import com.marcos.Library.Models.Enums.Gender;
import jakarta.persistence.*;


@Entity
@Table(name = "book")
public class Book {
   
    public Book() {
    }
    
    public Book(Integer id, String title, String description, Author author, Gender gender) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.gender = gender;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(length = 50, nullable = false)
    private String title;
    
    @Column(length = 255, nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
    
    @Column()
    private Gender gender;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Author getAuthor() {
        return author;
    }

    public Gender getGender() {
        return gender;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
