package com.app.fastnews.feature.home.ui.activity

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.fastnews.databinding.ActivityCategoryFilterViewBinding
import com.app.fastnews.feature.home.ui.adapter.CategoryAdapter
import com.app.fastnews.feature.home.ui.bean.CategoryResponse

class CategoryFilterView : LinearLayoutCompat {
  private var categoryList: ArrayList<CategoryResponse> = ArrayList()
  
  private val mBinding: ActivityCategoryFilterViewBinding by lazy {
    ActivityCategoryFilterViewBinding.inflate(LayoutInflater.from(context), null, false)
  }
  
  constructor(context: Context)
      : super(context) {
    initView(context)
  }
  
  constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
    initView(context)
  }
  
  constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
    initView(context)
  }
  
  private fun initView(context: Context?) {
    orientation = VERTICAL
    addView(mBinding.root)
  }
  
  fun initRecyclerView(mListener: CategoryAdapter.CategoryClickListener?) {
    updateData()
    mBinding.rvCategoryList.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    mBinding.rvCategoryList.adapter = mListener?.let { CategoryAdapter(it, categoryList) }
  }
  
  private fun updateData() {
    categoryList.add(CategoryResponse(1,"Top Headlines", true))
    categoryList.add(CategoryResponse(2, "Top Business"))
    categoryList.add(CategoryResponse(3, "Apple"))
    categoryList.add(CategoryResponse(4, "Tesla"))
    categoryList.add(CategoryResponse(5, "Technology"))
  }
}

