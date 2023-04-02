package com.rsupport.mobile1.test.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.databinding.FragmentGettyImageBinding

class GettyImageFragment : Fragment() {

    private lateinit var binding: FragmentGettyImageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentGettyImageBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = GettyImageFragment()
    }
}