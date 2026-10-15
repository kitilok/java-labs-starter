package edu.course.lab04;

import java.util.Objects;

public class Book {

private final String isbn;
private final String title;

public Book(String isbn, String title){
    this.isbn = isbn;
    this.title = title;
}

public String getIsbn(){
    return isbn;
    }

public String getTitle(){
    return title;
    }

@Override
public boolean equals(Object obj){
    if (this == obj){
        return true;
    }

    if (!(obj instanceof Book)){
        return false;
    }

    Book other = (Book) obj;
    return Objects.equals(isbn, other.isbn);
}

@Override
public int hashCode(){
    return Objects.hash(isbn);
   }
}

