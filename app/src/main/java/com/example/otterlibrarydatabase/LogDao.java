// Name: Rudolph Cacho Hernandez
// Date: December 16th, 2024
package com.example.otterlibrarydatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface LogDao {
    @Insert
    void addLog(Log log);

    @Query("SELECT COUNT(*) FROM logBank")
    int count();

    @Query("SELECT * FROM logBank")
    List<Log> getAll();

    @Delete
    void delete(Log log);
}