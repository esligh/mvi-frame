package com.me.home.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.me.home.R
import com.me.repo.entity.home.DailyQuestionData
import com.yunzhu.frame.base.BasePagingAdapter

class DailyQuestionPagingAdapter : BasePagingAdapter<DailyQuestionData>(diffCallback){

    companion object{
        val diffCallback = object : DiffUtil.ItemCallback<DailyQuestionData>() {
            override fun areItemsTheSame(
                oldItem: DailyQuestionData,
                newItem: DailyQuestionData
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: DailyQuestionData,
                newItem: DailyQuestionData
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun getItemLayout(position: Int): Int =  R.layout.item_rv_article

    override fun bindData(helper: ItemHelper, data: DailyQuestionData?) {

        helper.setText(R.id.tv_article_title, data?.title)
        helper.setText(R.id.bt_health_info_type, data?.superChapterName)
        helper.setText(R.id.tv_article_author, data?.author)
        helper.setText(R.id.tv_home_info_time, data?.niceShareDate)

    }

    override fun onItemClick(data: DailyQuestionData?) {

    }
}