package com.marcos.Library.Models.Enums;

public enum Gender {
    Novel("Novel"),
    Fiction("Fiction"),
    NonFiction("NonFiction"),
    ScienceFiction("Science Fiction"),
    Mystery("Mystery"),
    Thriller("Thriller"),
    Horror("Horror"),
    Poetry("Poetry");

    private String description;
    Gender(String description){
        this.description = description;
    }
    public String getDescription(){
        return description;
    }
}
