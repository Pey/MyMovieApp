package pr.peyman.movieapptest.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import pr.peyman.movieapptest.databinding.FragmentSearchBinding
import pr.peyman.movieapptest.ui.home.adapters.LastMoviesAdapter_type2
import pr.peyman.movieapptest.ui.search.adapter.SearchAdapter
import pr.peyman.movieapptest.viewmodel.SearchViewModel
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {

    lateinit var binding: FragmentSearchBinding

    val viewModel: SearchViewModel by viewModels()

    @Inject
    lateinit var adapter: LastMoviesAdapter_type2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            editText.addTextChangedListener {

                val text = it.toString()

                if (text.isNotEmpty()) {
                    viewModel.loadSearchList(text)
                }
            }

//get search List
            viewModel.searchList.observe(viewLifecycleOwner) {

//                adapter.differ.submitList(it.data)
                adapter.setDataItem(it.data)
                searchRecycler.layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                searchRecycler.adapter = adapter

            }

            //loading

            viewModel.loading.observe(viewLifecycleOwner) {

                if (it) {
                    ProgressBar.visibility = View.VISIBLE
                } else {
                    ProgressBar.visibility = View.INVISIBLE
                }
            }
//empty layout
            viewModel.empty.observe(viewLifecycleOwner) {

                if (it) {
                    emptyLay.visibility = View.VISIBLE
                    searchRecycler.visibility = View.INVISIBLE
                } else {
                    emptyLay.visibility = View.INVISIBLE
                    searchRecycler.visibility = View.VISIBLE
                }
            }

        }


    }
}

