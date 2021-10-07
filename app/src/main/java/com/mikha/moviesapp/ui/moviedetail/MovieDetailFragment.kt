package com.mikha.moviesapp.ui.moviedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.mikha.moviesapp.R
import com.mikha.moviesapp.domain.model.Movie
import com.mikha.moviesapp.databinding.MovieDetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    companion object {
        const val MOVIE_KEY = "movie"
    }

    private val viewModel: MovieDetailViewModel by viewModels()
    private var _binding: MovieDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movie = (arguments?.get(MOVIE_KEY) as? Movie) ?: throw Exception(getString(R.string.wrong_argument_received))
        viewModel.setMovie(movie)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MovieDetailFragmentBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.webView.webViewClient = WebViewClient()
//        binding.webView.settings.javaScriptEnabled = true
//        viewModel.movie.observe(viewLifecycleOwner, Observer {
//            binding.webView.loadUrl(it)
//        })
    }
    


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



}