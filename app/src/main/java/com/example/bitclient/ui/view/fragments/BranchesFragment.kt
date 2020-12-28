package com.example.bitclient.ui.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bitclient.BitClientApp
import com.example.bitclient.R
import com.example.bitclient.data.network.NetworkLiveData
import com.example.bitclient.databinding.FragmentBranchesBinding
import com.example.bitclient.ui.view.fragments.viewbinding.viewBinding
import com.example.bitclient.ui.viewmodels.BranchesViewModel
import com.example.bitclient.ui.viewmodels.ViewModelFactory
import javax.inject.Inject

class BranchesFragment : Fragment(R.layout.fragment_branches) {

    private val binding by viewBinding(FragmentBranchesBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var branchesViewModel: BranchesViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as BitClientApp).appComponent.userComponentManager().userComponent!!.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        branchesViewModel = ViewModelProvider(this, viewModelFactory).get(BranchesViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()

        startConnectionChecking()
    }

    private fun startConnectionChecking() {
        NetworkLiveData.observe(this, {
            if (it) {
                if (binding.textViewBranchesNoInternet.isVisible)
                    binding.textViewBranchesNoInternet.visibility = View.GONE
            } else {
                binding.textViewBranchesNoInternet.visibility = View.VISIBLE
            }
        })
    }
}