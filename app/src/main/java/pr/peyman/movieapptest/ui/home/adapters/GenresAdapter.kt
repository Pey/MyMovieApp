package pr.peyman.movieapptest.ui.home.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import coil3.request.crossfade
import dagger.hilt.android.qualifiers.ApplicationContext
import pr.peyman.movieapptest.databinding.ItemGenreHomeListBinding
import pr.peyman.movieapptest.models.home.ResponseGenres
import pr.peyman.movieapptest.models.home.ResponseMovies
import javax.inject.Inject

class GenresAdapter @Inject constructor(@ApplicationContext context: Context) :
    RecyclerView.Adapter<GenresAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            ItemGenreHomeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.setData(differ.currentList[position])
        holder.setIsRecyclable(false)// prevent duplicate
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ViewHolder(val binding: ItemGenreHomeListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun setData(item: ResponseGenres.ResponseGenreListItem) {
            binding.apply {

                nameTxt.text = item.name.toString()


            }
        }

    }


    val differCallBack = object : DiffUtil.ItemCallback<ResponseGenres.ResponseGenreListItem>() {

        override fun areItemsTheSame(
            oldItem: ResponseGenres.ResponseGenreListItem,
            newItem: ResponseGenres.ResponseGenreListItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ResponseGenres.ResponseGenreListItem,
            newItem: ResponseGenres.ResponseGenreListItem
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)
}