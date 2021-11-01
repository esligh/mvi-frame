package com.me.mvi.demo.views

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.core.content.withStyledAttributes
import androidx.core.view.isVisible
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.me.mvi.demo.R
import com.me.mvi.demo.databinding.MarqueeBinding
import com.yunzhu.frame.extensions.viewBinding

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class Marquee @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private val binding: MarqueeBinding by viewBinding()

    init {
        orientation = VERTICAL
        context.withStyledAttributes(attrs, R.styleable.Marquee) {
            if (hasValue(R.styleable.Marquee_android_title)) setTitle(getText(R.styleable.Marquee_android_title))
        }
    }

    @TextProp
    fun setTitle(title: CharSequence) {
        binding.title.text = title
    }

    @TextProp
    fun setSubtitle(subtitle: CharSequence?) {
        binding.subtitle.isVisible = subtitle.isNullOrBlank().not()
        binding.subtitle.text = subtitle
    }
}
