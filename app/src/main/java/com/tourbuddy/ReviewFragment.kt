package com.tourbuddy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tourbuddy.databinding.FragmentDetailBinding
import com.tourbuddy.databinding.FragmentReviewBinding

class ReviewFragment : Fragment() {
    private lateinit var binding : FragmentReviewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnWriteReview.setOnClickListener{
            val writeReviewFragment = WriteReviewFragment()
            val fragmentManager = parentFragmentManager
            fragmentManager.beginTransaction().apply {
                replace(R.id.frame_container, writeReviewFragment, WriteReviewFragment::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }
    }
}