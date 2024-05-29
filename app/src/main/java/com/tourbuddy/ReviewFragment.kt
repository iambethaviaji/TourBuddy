package com.tourbuddy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tourbuddy.data.Review
import com.tourbuddy.databinding.FragmentDetailBinding
import com.tourbuddy.databinding.FragmentReviewBinding

class ReviewFragment : Fragment() {
    private lateinit var binding : FragmentReviewBinding
    private lateinit var rvReview: RecyclerView
    private val list = ArrayList<Review>()
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

        rvReview = binding.rvDestination
        rvReview.setHasFixedSize(true)

        list.addAll(getListReview())
        showRecyclerList()
    }

    private fun getListReview(): ArrayList<Review> {
        val dataName = resources.getStringArray(R.array.review_name)
        val dataReview = resources.getStringArray(R.array.review_review)
        val dataPhoto = resources.obtainTypedArray(R.array.review_photo)
        val dataRating = resources.getIntArray(R.array.review_rating)
        val listReview = ArrayList<Review>()
        for (i in dataName.indices) {
            val review = Review(dataName[i], dataReview[i], dataRating[i], dataPhoto.getResourceId(i, -1))
            listReview.add(review)
        }
        dataPhoto.recycle()
        return listReview
    }

    private fun showRecyclerList() {
        rvReview.layoutManager = LinearLayoutManager(requireContext())
        val listReviewAdapter = ListReviewAdapter(list)
        rvReview.adapter = listReviewAdapter
    }
}