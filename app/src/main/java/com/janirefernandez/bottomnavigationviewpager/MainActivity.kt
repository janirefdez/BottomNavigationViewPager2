package com.janirefernandez.bottomnavigationviewpager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.janirefernandez.bottomnavigationviewpager.R
import com.janirefernandez.bottomnavigationviewpager.databinding.ActivityMainBinding
import com.janirefernandez.bottomnavigationviewpager.ui.adapters.ViewPagerAdapter
import com.janirefernandez.bottomnavigationviewpager.ui.dashboard.DashboardFragment
import com.janirefernandez.bottomnavigationviewpager.ui.home.HomeFragment
import com.janirefernandez.bottomnavigationviewpager.ui.notifications.NotificationsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Define fragments to display in viewPager2
        val listOfFragments = listOf(HomeFragment(), DashboardFragment(), NotificationsFragment())
        binding.viewPager.adapter = ViewPagerAdapter(this, listOfFragments)
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> binding.navView.menu.findItem(R.id.navigation_home).isChecked = true
                    1 -> binding.navView.menu.findItem(R.id.navigation_dashboard).isChecked = true
                    2 -> binding.navView.menu.findItem(R.id.navigation_notifications).isChecked = true

                }
            }
        })

        // Listen bottom navigation tabs change
        binding.navView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    binding.viewPager.setCurrentItem(0, true)
                    return@setOnItemSelectedListener true

                }
                R.id.navigation_dashboard -> {
                    binding.viewPager.setCurrentItem(1, true)
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_notifications -> {
                    binding.viewPager.setCurrentItem(2, true)
                    return@setOnItemSelectedListener true
                }
            }
            return@setOnItemSelectedListener false
        }
    }
}
