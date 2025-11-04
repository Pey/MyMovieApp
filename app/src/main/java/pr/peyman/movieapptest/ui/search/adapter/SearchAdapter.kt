package pr.peyman.movieapptest.ui.search.adapter

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
import pr.peyman.movieapptest.databinding.ItemLastMoviesHomeBinding
import pr.peyman.movieapptest.models.home.ResponseMovies
import javax.inject.Inject

class SearchAdapter @Inject constructor(@ApplicationContext context: Context) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            ItemLastMoviesHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.setData(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return  differ.currentList.size
    }

    inner class ViewHolder(val binding: ItemLastMoviesHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun setData(item: ResponseMovies.Data) {
            binding.apply {

                movieNameText.text = item.title.toString()
                movieRateText.text = item.imdbRating.toString()
                movieCountryText.text = item.country.toString()
                movieYearText.text = item.year.toString()

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