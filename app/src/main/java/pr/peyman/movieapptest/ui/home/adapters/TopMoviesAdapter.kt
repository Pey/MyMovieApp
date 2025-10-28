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
import pr.peyman.movieapptest.databinding.ItemHomwMoviesTopBinding
import pr.peyman.movieapptest.models.home.ResponseMovies
import javax.inject.Inject

class TopMoviesAdapter @Inject constructor(@ApplicationContext context: Context) :
    RecyclerView.Adapter<TopMoviesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            ItemHomwMoviesTopBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.setData(differ.currentList[position])
        holder.setIsRecyclable(false)// prevent duplicate
    }

    override fun getItemCount(): Int {
        return if (differ.currentList.size > 5) 5 else differ.currentList.size
    }

    inner class ViewHolder(val binding: ItemHomwMoviesTopBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun setData(item: ResponseMovies.Data) {
            binding.apply {

                movieNameText.text = item.title.toString()
                movieInfoText.text =
                    "${item.imdbRating.toString()} | ${item.country.toString()} | ${item.year.toString()}"

                moviePosterImg.load(item.poster){
                    crossfade(true)
                    crossfade(800)
                }

            }
        }

    }


    val differCallBack = object : DiffUtil.ItemCallback<ResponseMovies.Data>() {
        override fun areItemsTheSame(
            oldItem: ResponseMovies.Data,
            newItem: ResponseMovies.Data
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ResponseMovies.Data,
            newItem: ResponseMovies.Data
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)
}