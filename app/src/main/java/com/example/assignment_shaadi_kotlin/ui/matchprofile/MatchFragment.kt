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
import com.example.assignment_shaadi_kotlin.data.entities.Result
import com.example.assignment_shaadi_kotlin.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import com.example.assignment_shaadi_kotlin.databinding.FragmentMatchBinding
import com.example.assignment_shaadi_kotlin.utils.autoCleared
import kotlinx.android.synthetic.main.fragment_match.*

@AndroidEntryPoint
class MatchFragment : Fragment(), MatchProfileAdapter.MatchProfileAcceptedItemListener,MatchProfileAdapter.MatchProfileDeclinedItemListener {
    private val viewModel: ProfilesViewModel by viewModels()
    var binding : FragmentMatchBinding by autoCleared()
    var listValue : MutableList<Result> = ArrayList()
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
        adapter = MatchProfileAdapter(this,this)
        binding.charactersRv.layoutManager = LinearLayoutManager(requireContext())
        binding.charactersRv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.profiles.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) {
                        Log.e("MAINDATA", it.data.toString())
                        listValue.addAll(it.data)
                        adapter.setItems(ArrayList(it.data))
                        no_data.visibility = View.GONE
                        characters_rv.visibility = View.VISIBLE
                    }else{
                        Log.e("MAINDATA111", it.data.toString())
                        no_data.visibility = View.VISIBLE
                        characters_rv.visibility = View.GONE
                    }

                }
                Resource.Status.ERROR -> {

                    binding.progressBar.visibility = View.GONE
                    Log.e("MAINDATA1222", it.data.toString())
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    if(listValue.isNullOrEmpty()){
                        Log.e("MAINDATA1233332", it.data.toString())
                        no_data.visibility = View.VISIBLE
                        characters_rv.visibility = View.GONE
                    }else {
                        Log.e("MAINDATA134342423222", it.data.toString())
                        characters_rv.visibility = View.VISIBLE
                    }
                }
                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE


                }
            }
        })
    }

    override fun onClickedAccepted(viewId: Int, matchResult: Result) {
        matchResult.isAccepted = "Accepted"
        viewModel.updateProfile(matchResult).observe(this, Observer {
            if(it>0) Log.e("VALUE",it.toString())
        })

    }

    override fun onClickedDeclined(viewId: Int, matchResult: Result) {
        matchResult.isAccepted = "Declined"
        viewModel.updateProfile(matchResult).observe(this, Observer {
            if(it>0) Log.e("VALUE",it.toString())
        })
    }


}