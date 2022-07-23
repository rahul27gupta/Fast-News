package com.app.fastnews.feature.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.app.fastnews.R
import com.app.fastnews.databinding.CategoryListBinding
import com.app.fastnews.feature.home.ui.bean.CategoryResponse

class CategoryAdapter(private val mListener: CategoryClickListener, var categoryList: ArrayList<CategoryResponse>?) : RecyclerView.Adapter<CategoryViewHolder>() {
  
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
    return CategoryViewHolder(CategoryListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
  }
  
  override fun onBindViewHolder(categoryHolder: CategoryViewHolder, position: Int) {
    categoryList?.get(categoryHolder.adapterPosition)?.apply {
      categoryHolder.mBinding.apply {
        tvCategoryTitle.text = categoryTitle
        
        // Change Button Theme According to category Selection
        if (isSelected == true) {
          cvCategory.setCardBackgroundColor(ContextCompat.getColor(cvCategory.context, R.color.color_000000))
          tvCategoryTitle.setTextColor(ContextCompat.getColor(tvCategoryTitle.context, R.color.color_ffffff))
        } else {
          cvCategory.setCardBackgroundColor(ContextCompat.getColor(cvCategory.context, R.color.color_ebedf1))
          tvCategoryTitle.setTextColor(ContextCompat.getColor(tvCategoryTitle.context, R.color.color_000000))
        }
  
        //Category Click Operation
        cvCategory.setOnClickListener {
          val lastSelectedItem = getLastSelectedItem()
          if (lastSelectedItem != -1) {
            categoryList?.get(lastSelectedItem)?.isSelected = false
          }
          categoryList?.get(categoryHolder.adapterPosition)?.isSelected = true
          setCategoryView(lastSelectedItem, categoryHolder.adapterPosition)
  
          mListener.let {
            mListener.onTabChange(id)
          }
        }
      }
    }
  }
  
  private fun setCategoryView(lastSelectedItem: Int, adapterPosition: Int) {
    if (lastSelectedItem != -1) {
      notifyItemChanged(lastSelectedItem)
    }
    notifyItemChanged(adapterPosition)
  }
  
  private fun getLastSelectedItem(): Int {
    for (i in 0 until (categoryList?.count() ?: 0)) {
      if (categoryList?.get(i)?.isSelected == true) {
        return i
      }
    }
    return -1
  }
  
  override fun getItemCount(): Int {
    return categoryList?.count() ?: 0
  }
  
  interface CategoryClickListener {
    fun onTabChange(tabId: Int?)
  }
}

class CategoryViewHolder(var mBinding: CategoryListBinding) : RecyclerView.ViewHolder(mBinding.root) {}
