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
import pr.peyman.movieapptest.databinding.ItemLastMoviesHomeBinding
import pr.peyman.movieapptest.models.home.ResponseMovies
import pr.peyman.movieapptest.models.home.ResponseMovies.Data
import javax.inject.Inject

class LastMoviesAdapter_type2 @Inject constructor(@ApplicationContext context: Context) :
    RecyclerView.Adapter<LastMoviesAdapter_type2.ViewHolder>() {

    var movieList = emptyList<Data>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            ItemLastMoviesHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.setData(movieList[position])
        holder.setIsRecyclable(false)// prevent duplicate
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    inner class ViewHolder(val binding: ItemLastMoviesHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun setData(item: Data) {
            binding.apply {

                movieNameText.text = item.title.toString()
                movieRateText.text = item.imdbRating.toString()
                movieCountryText.text = item.country.toString()
                movieYearText.text = item.year.toString()

                moviePosterImg.load(item.poster) {
                    crossfade(true)
                    crossfade(800)
                }

            }
        }

    }

    fun setDataItem(data: List<Data>) {
        val moviesDiffUtils = MoviesDiffUtils(movieList, data)
        val diffUtils = DiffUtil.calculateDiff(moviesDiffUtils)
        movieList = data
        diffUtils.dispatchUpdatesTo(this) // "this" baraye in ast ke roye in adapter taghirat emal konad

    }


    class MoviesDiffUtils(private val oldItem: List<Data>, private val newItem: List<Data>) :
        DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldItem.size
        }

        override fun getNewListSize(): Int {
            return newItem.size
        }

        override fun areItemsTheSame(
            oldItemPosition: Int,
            newItemPosition: Int
        ): Boolean {
            return oldItem[oldItemPosition] === newItem[newItemPosition]
        }

        override fun areContentsTheSame(
            oldItemPosition: Int,
            newItemPosition: Int
        ): Boolean {
            return oldItem[oldItemPosition] === newItem[newItemPosition]
        }

    }


//    val differCallBack = object : DiffUtil.ItemCallback<ResponseMovies.Data>() {
//        override fun areItemsTheSame(
//            oldItem: ResponseMovies.Data,
//            newItem: ResponseMovies.Data
//        ): Boolean {
//            return oldItem.id == newItem.id
//        }
//
//        override fun areContentsTheSame(
//            oldItem: ResponseMovies.Data,
//            newItem: ResponseMovies.Data
//        ): Boolean {
//            return oldItem == newItem
//        }
//
//    }
//
//    val differ = AsyncListDiffer(this, differCallBack)
}