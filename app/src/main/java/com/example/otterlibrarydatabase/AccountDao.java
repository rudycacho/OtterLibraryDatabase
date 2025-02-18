// Name: Rudolph Cacho Hernandez
// Date: December 16th, 2024
package com.example.otterlibrarydatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface AccountDao {
    @Insert
    void addAccount(Account account);

    @Query("SELECT COUNT(*) FROM accountBank")
    int count();

    @Query("SELECT * FROM accountBank")
    List<Account> getAll();

    @Delete
    void delete(Account account);
}
