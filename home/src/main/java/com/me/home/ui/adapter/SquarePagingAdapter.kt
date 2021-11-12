package com.me.home.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.me.home.R
import com.me.repo.entity.home.SquareData
import com.yunzhu.frame.base.BasePagingAdapter

class SquarePagingAdapter : BasePagingAdapter<SquareData>(differCallback) {

    companion object {
        val differCallback = object : DiffUtil.ItemCallback<SquareData>() {
            override fun areItemsTheSame(oldItem: SquareData, newItem: SquareData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: SquareData, newItem: SquareData): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun getItemLayout(position: Int): Int = R.layout.item_rv_article

    override fun onItemClick(data: SquareData?) {

    }

    override fun bindData(helper: ItemHelper, data: SquareData?) {
        helper.setText(R.id.tv_article_title, data?.title)
        helper.setText(R.id.bt_health_info_type, data?.superChapterName)
        helper.setText(R.id.tv_home_info_time, data?.niceDate)
        helper.setText(R.id.tv_article_author, data?.shareUser)

    }
}

