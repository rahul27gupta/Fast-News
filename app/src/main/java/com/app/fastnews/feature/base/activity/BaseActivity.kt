package com.app.fastnews.feature.base.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.fastnews.R

class BaseActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_base)
  }
}