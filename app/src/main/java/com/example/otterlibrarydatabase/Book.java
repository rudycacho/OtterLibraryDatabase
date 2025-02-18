// Name: Rudolph Cacho Hernandez
// Date: December 16th, 2024
package com.example.otterlibrarydatabase;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "bookBank")
public class Book {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String title;
    @ColumnInfo
    private String author;
    @ColumnInfo
    private String genre;

    public Book(String title, String author, String genre){
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    // get and set id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // get and set title
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    // get and set author
    public String getAuthor(){
        return author;
    }
    public void setAuthor(String author){
        this.author = author;
    }

    // get and set genre
    public String getGenre(){
        return genre;
    }
    public void setGenre(String genre){
        this.genre = genre;
    }

}
