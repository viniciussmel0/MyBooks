package com.viniciusmelo.mybooks.ui
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.viniciusmelo.mybooks.R
import com.viniciusmelo.mybooks.databinding.FragmentHomeBinding
import com.viniciusmelo.mybooks.ui.adapter.BookAdapter
import com.viniciusmelo.mybooks.ui.listener.BookListener
import com.viniciusmelo.mybooks.viewmodels.HomeViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()
    private val adapter: BookAdapter = BookAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.recyclerviewBooks.layoutManager = LinearLayoutManager(context)

        // adapter
        binding.recyclerviewBooks.adapter = adapter

        attachListener()

        viewModel.getAllBooks()
        setObservers()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun attachListener() {
        adapter.attachListener(object : BookListener {
            override fun onClick(id: Int) {
                findNavController().navigate(R.id.navigation_details)
            }
        })
    }

    private fun setObservers() {
        viewModel.books.observe(viewLifecycleOwner) {
            adapter.updateBooks(it)
        }
    }
}