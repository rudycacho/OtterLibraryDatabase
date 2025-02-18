// Name: Rudolph Cacho Hernandez
// Date: December 16th, 2024
package com.example.otterlibrarydatabase;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface BookDao {
    @Insert
    void addBook(Book book);

    @Query("SELECT COUNT(*) FROM bookBank")
    int count();

    @Query("SELECT * FROM bookBank")
    List<Book> getAll();

    @Delete
    void delete(Book book);
}
