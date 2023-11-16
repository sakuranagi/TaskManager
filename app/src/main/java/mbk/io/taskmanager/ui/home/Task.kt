package mbk.io.taskmanager.ui.home

import java.io.Serializable

data class Task(
    val title: String,
    val description: String
): Serializable
