package pr.peyman.movieapptest.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import pr.peyman.movieapptest.databinding.FragmentFavoriteBinding
import pr.peyman.movieapptest.ui.favorite.adapter.FavoriteAdapter
import pr.peyman.movieapptest.viewmodel.FavoriteViewModel
import javax.inject.Inject

@AndroidEntryPoint

class FavoriteFragment : Fragment() {

    lateinit var binding: FragmentFavoriteBinding

    val viewModel: FavoriteViewModel by viewModels()

    @Inject
    lateinit var adapter: FavoriteAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            // show all favorite
            viewModel.loadAllMoviesFromDb()

            //loading
            viewModel.loading.observe(viewLifecycleOwner) {
                if (it) {
                    ProgressBar.visibility = View.VISIBLE
                } else {
                    ProgressBar.visibility = View.INVISIBLE

                }
            }

            // empty
            viewModel.empty.observe(viewLifecycleOwner) {
                if (it) {
                    emptyLay.visibility = View.INVISIBLE
                    favoriteRecycler.visibility = View.VISIBLE
                } else {
                    emptyLay.visibility = View.VISIBLE
                    favoriteRecycler.visibility = View.INVISIBLE
                }
            }


            //get data from db

            viewModel.moviesList.observe(viewLifecycleOwner) {

                favoriteRecycler.layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                adapter.setDataItem(it)
                favoriteRecycler.adapter = adapter
            }


            //click item
            adapter.setOnClickFavoriteItem {

                val direction = FavoriteFragmentDirections.actionToDetails()
                findNavController().navigate(direction)

            }
        }
    }


}