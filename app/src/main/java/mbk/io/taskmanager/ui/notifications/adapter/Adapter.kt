package mbk.io.taskmanager.ui.notifications.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import mbk.io.taskmanager.databinding.ItemTaskBinding
import mbk.io.taskmanager.model.Book

class BookAdapter : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    private val bookList = arrayListOf<Book>()

    fun addBooks(newList: List<Book>) {
        bookList.clear()
        bookList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = bookList.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(bookList[position])
    }

    inner class BookViewHolder(private val binding: ItemTaskBinding) : ViewHolder(binding.root) {
        fun bind(book: Book) {
            binding.tvTitle.text = book.name
            binding.tvDescription.text = book.author
        }
    }
}