package ua.cn.cpnu.pmp_lab_4.model;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public abstract class UniversitiesDao {

    @Query("SELECT * FROM universities")
    public abstract List<LocalUniversity> getUniversities();

    @Query("SELECT * FROM universities WHERE identifier LIKE :pattern")
    public abstract LocalUniversity getUniversityById(String pattern);

    @Query("DELETE FROM universities")
    public abstract void deleteAll();

    @Insert
    public abstract void insertUniversities(List<LocalUniversity> universities);

    @Transaction
    public void updateImages(List<LocalUniversity> newUniversities) {
        deleteAll();
        insertUniversities(newUniversities);
    }

    @Insert
    public abstract long insertOneUniversity(LocalUniversity university);

    @Insert
    public abstract long[] insertUniversitiesAndReturnIds(LocalUniversity[] universities);

    @Update
    public abstract void updateUniversity(LocalUniversity university);

    @Delete
    public abstract void deleteUniversity(LocalUniversity university);

}
