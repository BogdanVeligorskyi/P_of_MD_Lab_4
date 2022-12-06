package ua.cn.cpnu.pmp_lab_4.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import java.util.List;

// class with SQLite-queries to a local database
@Dao
public abstract class UniversitiesDao {

    @Query("SELECT * FROM universities")
    public abstract List<LocalUniversity> getUniversities();

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

}
