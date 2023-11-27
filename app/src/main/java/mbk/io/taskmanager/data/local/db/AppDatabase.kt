package mbk.io.taskmanager.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import mbk.io.taskmanager.model.Task

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}