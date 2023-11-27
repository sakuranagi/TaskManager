package mbk.io.taskmanager.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import mbk.io.taskmanager.model.Task

@Dao

interface TaskDao {

    @Insert
    fun insert(task: Task)

    @Delete
    fun delete(task: Task)

    @Update
    fun update(task: Task)

    @Query("SELECT * FROM task ORDER BY uid DESC")
    fun getAll(): List<Task>
}