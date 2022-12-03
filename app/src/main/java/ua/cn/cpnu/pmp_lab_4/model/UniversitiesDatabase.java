package ua.cn.cpnu.pmp_lab_4.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {LocalUniversity.class}, version = 1)
public abstract class UniversitiesDatabase extends RoomDatabase {
    public abstract UniversitiesDao getUniversitiesDao();
}
