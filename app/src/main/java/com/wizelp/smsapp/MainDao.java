package com.wizelp.smsapp;


import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MainDao  {
    //Insert query
    @Insert(onConflict = REPLACE)
    void insert(MainData mainData);

    //Delete query
    @Delete
    void delete(MainData mainData);

    //Delete all query
   // void reset(List<MainData> mainData);
    @Query("SELECT * FROM table_name WHERE  text=:userId")
    List<MainData> is_exist(String userId);
    // update query
//    @Query("UPDATE table_name SET text=:sText   WHERE ID=:sID")
//    void update(int sID,String sText);

    //Get all data query
    @Query("SELECT * FROM table_name")
    List<MainData> getAll();

}
