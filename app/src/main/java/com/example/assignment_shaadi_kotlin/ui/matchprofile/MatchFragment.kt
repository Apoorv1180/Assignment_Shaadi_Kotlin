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
import com.example.assignment_shaadi_kotlin.utils.Utils
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
        if(Utils.isInternetAvailable(requireContext())) {
            setupObservers()
        }
        else {
            viewModel.profilesOffline.observe(viewLifecycleOwner, Observer {
                it.data?.let { it1 -> handleMyObservable(it, it1) }
            })
        }

    }

    private fun setupRecyclerView() {
        adapter = MatchProfileAdapter(this,this)
        binding.charactersRv.layoutManager = LinearLayoutManager(requireContext())
        binding.charactersRv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.profiles.observe(viewLifecycleOwner, Observer {
            it.data?.let { it1 -> handleMyObservable(it, it1) }
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

    private fun handleMyObservable(
        it: Resource<List<Result>>,
        data: List<Result>
    ) {
        when (it.status) {
            Resource.Status.SUCCESS -> {
                binding.progressBar.visibility = View.GONE
                if (!it.data.isNullOrEmpty()) {
                    adapter.setItems(ArrayList(data))
                    no_data.visibility = View.GONE
                    characters_rv.visibility = View.VISIBLE
                } else {
                    no_data.visibility = View.VISIBLE
                    characters_rv.visibility = View.GONE
                }
            }
            Resource.Status.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            Resource.Status.ERROR -> {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                if (listValue.isNullOrEmpty()) {
                    no_data.visibility = View.VISIBLE
                    characters_rv.visibility = View.GONE
                } else {
                    characters_rv.visibility = View.VISIBLE
                }
            }
        }
    }


}