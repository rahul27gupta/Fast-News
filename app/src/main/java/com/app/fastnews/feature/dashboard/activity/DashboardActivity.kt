package com.app.fastnews.feature.dashboard.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.app.fastnews.R
import com.app.fastnews.databinding.ActivityDashboardBinding
import com.app.fastnews.feature.bookmark.ui.fragment.BookmarkFragment
import com.app.fastnews.feature.home.ui.fragment.HomeFragment
import com.app.fastnews.feature.profile.ui.fragment.ProfileFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {
  private var mBinding: ActivityDashboardBinding? = null
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    mBinding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
    initToolbar()
    initBottomNavigation()
    onChangeScreen(HomeFragment())
  }
  
  private fun initToolbar() {
    mBinding?.toolbar?.title?.text = "Fast News"
  }
  
  private fun initBottomNavigation() {
    mBinding?.bottomNavigation?.setOnItemSelectedListener { item ->
      when (item.itemId) {
        R.id.actionHome -> {
          onChangeScreen(HomeFragment())
        }
        R.id.actionBookmark -> {
          onChangeScreen(BookmarkFragment())
        }
        R.id.actionUser -> {
          onChangeScreen(ProfileFragment())
        }
      }
      true
    }
  }
  
  private fun onChangeScreen(fragment: Fragment) {
    val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
    fragmentTransaction.replace(R.id.flScreen, fragment)
    fragmentTransaction.addToBackStack(null)
    fragmentTransaction.commit()
  }
}

