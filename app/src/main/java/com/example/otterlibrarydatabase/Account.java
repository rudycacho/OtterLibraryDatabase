// Name: Rudolph Cacho Hernandez
// Date: December 16th, 2024
package com.example.otterlibrarydatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "accountBank")
public class Account {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String username;
    @ColumnInfo
    private String password;

    public Account(String username, String password){
        this.username = username;
        this.password = password;
    }
    // get and set id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // get and set username
    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    // get and set password
    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.username = password;
    }
}
