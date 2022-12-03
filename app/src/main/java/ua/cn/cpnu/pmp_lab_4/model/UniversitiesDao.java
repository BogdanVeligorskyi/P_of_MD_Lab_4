package ua.cn.cpnu.pmp_lab_4.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public abstract class UniversitiesDao {

    @Query("SELECT * FROM universities WHERE country LIKE :pattern")
    public abstract List<LocalUniversity> getUniversities(String pattern);

    @Insert
    public abstract long[] insert(List<LocalUniversity> universities);

    @Query("DELETE FROM universities")
    public abstract void deleteAll();

    @Transaction
    public void updateImages(List<LocalUniversity> newUniversities) {
        deleteAll();
        insert(newUniversities);
    }

    @Insert
    public abstract void insertUniversities(List<LocalUniversity> universities);

    @Insert
    public abstract long insertOneUniversity(LocalUniversity university);

    @Insert
    public abstract long[] insertUniversitiesAndReturnIds(LocalUniversity[] universities);

    @Update
    public abstract void updateUniversity(LocalUniversity university);

    @Delete
    public abstract void deleteUniversity(LocalUniversity university);

    @Delete
    public abstract void deleteUniversities(List<LocalUniversity> universities);

}
