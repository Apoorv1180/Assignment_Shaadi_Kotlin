package com.example.assignment_shaadi_kotlin.ui.matchprofile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment_shaadi_kotlin.R
import com.example.assignment_shaadi_kotlin.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import com.example.assignment_shaadi_kotlin.databinding.FragmentMatchBinding
import com.example.assignment_shaadi_kotlin.utils.autoCleared

@AndroidEntryPoint
class MatchFragment : Fragment(), MatchProfileAdapter.MatchProfileItemListener {
    private val viewModel: ProfilesViewModel by viewModels()
    var binding : FragmentMatchBinding by autoCleared()
    private lateinit var adapter: MatchProfileAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMatchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = MatchProfileAdapter(this)
        binding.charactersRv.layoutManager = LinearLayoutManager(requireContext())
        binding.charactersRv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.profiles.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) Log.e("item",it.data.toString())
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    override fun onClickedCharacter(characterId: String) {
        TODO("Not yet implemented")
    }

}