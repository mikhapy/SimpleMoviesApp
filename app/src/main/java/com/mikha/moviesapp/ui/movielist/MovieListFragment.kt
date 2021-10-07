package com.mikha.moviesapp.ui.movielist

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikha.moviesapp.databinding.MovieListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private val homeViewModel: MovieListViewModel by viewModels()
    private var _binding: MovieListFragmentBinding? = null
    private val binding get() = _binding!!

    private var movieAdapter: MovieAdapter? = null



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MovieListFragmentBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = homeViewModel

        movieAdapter = MovieAdapter(MovieAdapter.MovieClick {
            Timber.d("Movie clicked = ${it.title}")
            findNavController().navigate(
                MovieListFragmentDirections.actionNavHomeToMovieDetailFragment(it)
            )
        })


        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = movieAdapter
        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        homeViewModel.movies.observe(viewLifecycleOwner, Observer {
            movieAdapter?.submitList(it)
        })

        binding.swipeContainer.setOnRefreshListener {
            homeViewModel.getMoviesFromRepository(true)
        }

        binding.btnRetry.setOnClickListener {
            homeViewModel.getMoviesFromRepository(true)
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}