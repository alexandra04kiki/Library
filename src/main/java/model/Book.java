package model;

import java.time.LocalDate;

public class Book {
    private Long id; //folosim Long pt ca val implicita sa fie null si nu 0 cum ar fi fost in cazul lui long
    private String title;
    private String author;
    private LocalDate publishedDate;
    private Integer stock;
    private Float price;

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    @Override
    public String toString() {
        return "Book: Id: " + id + ", Title: " + title + ", Author: " + author + ", Published Date: " + publishedDate;
    }
}
