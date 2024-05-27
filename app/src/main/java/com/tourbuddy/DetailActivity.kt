package com.tourbuddy

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tourbuddy.data.Destination
import com.tourbuddy.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val destination = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Destination>("key_destination", Destination::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Destination>("key_destination")
        }

        binding.ivPhoto.setImageResource(destination?.photo as Int)

        val bundle = Bundle()
        bundle.putParcelable("key_destination", destination)

        val fragmentManager = supportFragmentManager
        val detailFragment = DetailFragment()
        detailFragment.arguments = bundle
        val fragment = fragmentManager.findFragmentByTag(DetailFragment::class.java.simpleName)
        if (fragment !is DetailFragment) {
            fragmentManager
                .beginTransaction()
                .add(R.id.frame_container, detailFragment, DetailFragment::class.java.simpleName)
                .commit()
        }

        binding.buttonBack.setOnClickListener{
            startActivity(Intent(this@DetailActivity, MainActivity::class.java))
            finish()
        }

        //todo bookmark story
        binding.buttonBookmark.setOnClickListener{

        }
    }
}