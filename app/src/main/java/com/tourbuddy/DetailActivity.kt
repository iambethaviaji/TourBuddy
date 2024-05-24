package com.tourbuddy

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tourbuddy.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentManager = supportFragmentManager
        val detailFragment = DetailFragment()
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