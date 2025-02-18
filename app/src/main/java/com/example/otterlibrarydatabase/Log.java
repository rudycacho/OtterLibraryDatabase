// Name: Rudolph Cacho Hernandez
// Date: December 16th, 2024
package com.example.otterlibrarydatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "logBank")
public class Log {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String transactionType;
    @ColumnInfo
    private String details;

    public Log(String transactionType, String details){
        this.transactionType = transactionType;
        this.details = details;
    }
    // get and set id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // get and set username
    public String getTransactionType(){
        return transactionType;
    }

    public void setTransactionType(String username){
        this.transactionType = username;
    }

    // get and set password
    public String getDetails(){
        return details;
    }

    public void setDetails(String password){
        this.details = password;
    }
}
