package ua.cn.cpnu.pmp_lab_4.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

// abstract class for local database
@Database(entities = {LocalUniversity.class}, version = 1)
public abstract class UniversitiesDatabase extends RoomDatabase {
    public abstract UniversitiesDao getUniversitiesDao();
}
