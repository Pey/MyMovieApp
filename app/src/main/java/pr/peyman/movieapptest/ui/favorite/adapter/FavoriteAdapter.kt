package pr.peyman.movieapptest.ui.favorite.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import coil3.request.crossfade
import dagger.hilt.android.qualifiers.ApplicationContext
import pr.peyman.movieapptest.databinding.ItemLastMoviesHomeBinding
import pr.peyman.movieapptest.db.MovieEntity
import javax.inject.Inject

class FavoriteAdapter @Inject constructor(@ApplicationContext context: Context) :
    RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    var movieList = emptyList<MovieEntity>()

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
        fun setData(item: MovieEntity) {
            binding.apply {

                movieNameText.text = item.movieName.toString()
                movieRateText.text = item.rate.toString()
                movieCountryText.text = item.country.toString()
                movieYearText.text = item.year.toString()

                moviePosterImg.load(item.img) {
                    crossfade(true)
                    crossfade(800)
                }

                root.setOnClickListener {
                    setOnClickFavoriteItem {
                        setOnClick?.invoke(item)
                    }
                }

            }
        }

    }

    var setOnClick: ((MovieEntity) -> Unit)? = null

    fun setOnClickFavoriteItem(listener: (MovieEntity) -> Unit) {
        setOnClick = listener
    }

    fun setDataItem(data: List<MovieEntity>) {
        val moviesDiffUtils = MoviesDiffUtils(movieList, data)
        val diffUtils = DiffUtil.calculateDiff(moviesDiffUtils)
        movieList = data
        diffUtils.dispatchUpdatesTo(this) // "this" baraye in ast ke roye in adapter taghirat emal konad

    }


    class MoviesDiffUtils(
        private val oldItem: List<MovieEntity>,
        private val newItem: List<MovieEntity>
    ) :
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


}