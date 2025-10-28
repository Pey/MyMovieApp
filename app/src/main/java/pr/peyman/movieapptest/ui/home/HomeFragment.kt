package pr.peyman.movieapptest.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import pr.peyman.movieapptest.databinding.FragmentHomeBinding
import pr.peyman.movieapptest.ui.home.adapters.GenresAdapter
import pr.peyman.movieapptest.ui.home.adapters.LastMoviesAdapter
import pr.peyman.movieapptest.ui.home.adapters.TopMoviesAdapter
import pr.peyman.movieapptest.viewmodel.HomeViewModel
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var topMoviesAdapter: TopMoviesAdapter

    @Inject
    lateinit var lastMoviesAdapter: LastMoviesAdapter

    @Inject
    lateinit var genresAdapter: GenresAdapter


    val pagerIndicator: PagerSnapHelper by lazy { PagerSnapHelper() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // call api

        viewModel.getTopMovies(3)
        viewModel.getGenresMovies()
        viewModel.getLastMovies()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            //get top movies

            viewModel.topMovies.observe(viewLifecycleOwner) {

                topMoviesAdapter.differ.submitList(it.data)

                topMoviesRecycler.layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

                topMoviesRecycler.adapter = topMoviesAdapter


                pagerIndicator.attachToRecyclerView(topMoviesRecycler) // baraye tak tak jabeja shodan
                topMoviesIndicator.attachToRecyclerView(topMoviesRecycler, pagerIndicator)

            }


            // get Genre

            viewModel.genresList.observe(viewLifecycleOwner) {

                genresAdapter.differ.submitList(it)

                genresRecycler.layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

                genresRecycler.adapter = genresAdapter
            }

            // get last Movies


            viewModel.lastMovies.observe(viewLifecycleOwner) {

                lastMoviesAdapter.differ.submitList(it.data)

                lastMoviesRecycler.layoutManager = LinearLayoutManager(
                    requireContext(),
                    RecyclerView.VERTICAL, false
                )

                lastMoviesRecycler.adapter = lastMoviesAdapter
            }

            viewModel.homeLoading.observe(viewLifecycleOwner) {

                if (it) {
                    progressBar.visibility = View.VISIBLE
                    moviesScrollLay.visibility = View.INVISIBLE
                } else {
                    progressBar.visibility = View.INVISIBLE
                    moviesScrollLay.visibility = View.VISIBLE
                }
            }
        }

    }


}