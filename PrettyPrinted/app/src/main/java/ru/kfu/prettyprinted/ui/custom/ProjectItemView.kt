package ru.kfu.prettyprinted.ui.custom

import android.content.Context
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.project_item.view.*
import ru.kfu.prettyprinted.R

class ProjectItemView constructor(
        context: Context
) : ViewGroup(context, null, 0) {
    private val tv_name: TextView


    init {
        tv_name = TextView(context).apply {
            id = R.id.pl_item_name
            textSize = 12f
        }

        addView(pl_item_name)
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        var usedHeight = paddingTop
//        val width = getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)
//
//        measureChild(tv_date, widthMeasureSpec, heightMeasureSpec)
//        tv_author.maxWidth = width - (tv_date.measuredWidth + 3 * defaultPadding)
//        measureChild(tv_author, widthMeasureSpec, heightMeasureSpec)
//        usedHeight += tv_author.measuredHeight
//
//        //title row
//        val rh = posterSize + categorySize / 2
//        tv_title.maxWidth = width - (rh + 2 * paddingLeft + defaultSpace)
//        measureChild(tv_title, widthMeasureSpec, heightMeasureSpec)
//        usedHeight += max(tv_title.measuredHeight, rh) + 2 * defaultSpace
//
//        //description row
//        measureChild(tv_description, widthMeasureSpec, heightMeasureSpec)
//        usedHeight += tv_description.measuredHeight + defaultSpace
//
//        //icon row
//        measureChild(tv_likes_count, widthMeasureSpec, heightMeasureSpec)
//        measureChild(tv_comments_count, widthMeasureSpec, heightMeasureSpec)
//        measureChild(tv_read_duration, widthMeasureSpec, heightMeasureSpec)
//
//        usedHeight += iconSize + paddingBottom
//        setMeasuredDimension(width, usedHeight)
    }


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var usedHeight = paddingTop
        val bodyWidth = right - left - paddingLeft - paddingRight
        var left = paddingLeft

//        tv_date.layout(
//                left,
//                usedHeight,
//                left + tv_date.measuredWidth,
//                usedHeight + tv_date.measuredHeight
//        )
//        left = tv_date.right + defaultPadding
//        tv_author.layout(
//                left,
//                usedHeight,
//                left + tv_author.measuredWidth,
//                usedHeight + tv_author.measuredHeight
//        )
//        usedHeight += tv_author.measuredHeight + defaultSpace
//        left = paddingLeft
//
//        val rh = posterSize + categorySize / 2
//        val leftTop = if (rh > tv_title.measuredHeight) (rh - tv_title.measuredHeight) / 2 else 0
//        val rightTop = if (rh < tv_title.measuredHeight) (tv_title.measuredHeight - rh) / 2 else 0
//
//        tv_title.layout(
//                left,
//                usedHeight + leftTop,
//                left + tv_title.measuredWidth,
//                usedHeight + leftTop + tv_title.measuredHeight
//        )
//        iv_poster.layout(
//                left + bodyWidth - posterSize,
//                usedHeight + rightTop,
//                left + bodyWidth,
//                usedHeight + rightTop + posterSize
//        )
//        iv_category.layout(
//                iv_poster.left - categorySize / 2,
//                iv_poster.bottom - categorySize / 2,
//                iv_poster.left + categorySize / 2,
//                iv_poster.bottom + categorySize / 2
//        )
//        usedHeight += if (rh > tv_title.measuredHeight) rh else tv_title.measuredHeight
//        usedHeight += defaultSpace
//
//        tv_description.layout(
//                left,
//                usedHeight,
//                left + bodyWidth,
//                usedHeight + tv_description.measuredHeight
//        )
//        usedHeight += tv_description.measuredHeight + defaultSpace
//
//        val fontDiff = iconSize - tv_likes_count.measuredHeight
//        iv_likes.layout(
//                left,
//                usedHeight - fontDiff,
//                left + iconSize,
//                usedHeight + iconSize - fontDiff
//        )
//
//        left = iv_likes.right + defaultSpace
//        tv_likes_count.layout(
//                left,
//                usedHeight,
//                left + tv_likes_count.measuredWidth,
//                usedHeight + tv_likes_count.measuredHeight
//        )
//        left = tv_likes_count.right + defaultPadding
//
//        iv_comments.layout(
//                left,
//                usedHeight - fontDiff,
//                left + iconSize,
//                usedHeight + iconSize - fontDiff
//        )
//        left = iv_comments.right + defaultSpace
//        tv_comments_count.layout(
//                left,
//                usedHeight,
//                left + tv_comments_count.measuredWidth,
//                usedHeight + tv_comments_count.measuredHeight
//        )
//        left = tv_comments_count.right + defaultPadding
//        tv_read_duration.layout(
//                left,
//                usedHeight,
//                left + tv_read_duration.measuredWidth,
//                usedHeight + tv_read_duration.measuredHeight
//        )
//
//        left = defaultPadding
//        iv_bookmark.layout(
//                left + bodyWidth - iconSize,
//                usedHeight - fontDiff,
//                left + bodyWidth,
//                usedHeight + iconSize - fontDiff
//        )
    }

//    fun bind(item: ArticleItem, listener: (ArticleItem, Boolean) -> Unit) {
//
//        tv_date.text = item.date.shortFormat()
//        tv_author.text = item.author
//        tv_title.text = item.title
//
//        Glide.with(context)
//                .load(item.poster)
//                .transform(CenterCrop(), RoundedCorners(cornerRadius))
//                .override(posterSize)
//                .into(iv_poster)
//
//        Glide.with(context)
//                .load(item.categoryIcon)
//                .transform(CenterCrop(), RoundedCorners(cornerRadius))
//                .override(categorySize)
//                .into(iv_category)
//
//        tv_description.text = item.description
//        tv_likes_count.text = "${item.likeCount}"
//        tv_comments_count.text = "${item.commentCount}"
//        tv_read_duration.text = "${item.readDuration} min read"
//        iv_bookmark.isChecked = item.isBookmark
//        iv_bookmark.setOnClickListener { listener.invoke(item, true) }
//        this.setOnClickListener { listener.invoke(item, false) }
//    }
}