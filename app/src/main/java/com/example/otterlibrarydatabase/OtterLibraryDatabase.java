// Name: Rudolph Cacho Hernandez
// Date: December 16th, 2024
package com.example.otterlibrarydatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Book.class, Account.class,  Log.class}, version = 3, exportSchema = false)

public abstract class OtterLibraryDatabase extends RoomDatabase {

    public abstract BookDao book();
    public abstract AccountDao account();
    public abstract LogDao log();
    private static OtterLibraryDatabase sInstance;

    public static synchronized OtterLibraryDatabase getInstance(Context context){
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            OtterLibraryDatabase.class, "library.db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return sInstance;
    }
    public void populateInitialData(){
        if (account().count() == 0){
            runInTransaction(() ->{
                Account a1 = new Account("!admin2","!admin2");
                Account a2 = new Account("sShlaer","oop");
                Account a3 = new Account("bMeyer","eiffel");
                Account a4 = new Account("shirleyBee","carmel2Chicago");

                account().addAccount(a1);
                account().addAccount(a2);
                account().addAccount(a3);
                account().addAccount(a4);
            });
        }
        if (book().count() == 0){
            runInTransaction(()->{
                Book b1 = new Book("Meditations","Marcus Aurelius","Self-Help");
                Book b2 = new Book("Letters to a Young Poet","Rainer Maria Rilke","Self-Help");
                Book b3 = new Book("Circe","Madeline Miller","Historical Fantasy");
                Book b4 = new Book("Reusable Software","Bertrand Meyer","Computer Science");
                Book b5 = new Book("Intro to Machine Learning","Anita Faul","Computer Science");

                book().addBook(b1);
                book().addBook(b2);
                book().addBook(b3);
                book().addBook(b4);
                book().addBook(b5);
            });
        }
    }
}
