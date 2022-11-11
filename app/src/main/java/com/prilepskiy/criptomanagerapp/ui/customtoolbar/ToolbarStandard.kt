package com.prilepskiy.criptomanagerapp.ui.customtoolbar

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.prilepskiy.criptomanagerapp.R
import com.prilepskiy.criptomanagerapp.databinding.ToolbarStandardBinding

@SuppressLint("CustomViewStyleable")
open class ToolbarStandard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
): CustomToolbar(context, attrs){

    private lateinit var binding: ToolbarStandardBinding

    private var customFirstItem: Drawable? = null

    private var customSecondItem: Drawable? = null

    private var backgroundColor: Int? = null

    private var firstItemClick: () -> Unit = {}

    private var secondItemClick: () -> Unit = {}

    init {
        val attr: TypedArray =
            context.obtainStyledAttributes(attrs, R.styleable.CustomToolbar, 0, 0)

        customFirstItem = attr.getDrawable(R.styleable.CustomToolbar_firstItem)

        customSecondItem = attr.getDrawable(R.styleable.CustomToolbar_secondItem)

        backgroundColor = attr.getColor(R.styleable.CustomToolbar_backgroundColor,0)

        attr.recycle()
        this.createToolbar()
    }

    override fun createToolbar() {
        binding = ToolbarStandardBinding.inflate(LayoutInflater.from(context), this, false)


        with(binding) {

            //*** title ***
            title.text = customTitle


            //*** firstItem element ***
            customFirstItem?.let {
                firstItem.visibility= View.VISIBLE
                firstItem.setImageDrawable(it)
            }

            firstItem.setOnClickListener {
                firstItemClick()
            }

            //*** secondItem element ***
            customSecondItem?.let {
                secondItem.visibility= View.VISIBLE
                secondItem.setImageDrawable(it)
            }

            secondItem.setOnClickListener {
                secondItemClick()
            }

            backgroundColor?.let { toolbarStandardMain.setBackgroundColor(it) }

            addView(root)
        }
    }

    private fun setDrawableDrawableInt(imageView: ImageView, @DrawableRes drawableId: Int) {
        imageView.setImageDrawable(ContextCompat.getDrawable(context, drawableId))
    }


    override fun setTitleText(text: String) {
        binding.title.text = text
    }

    fun setOnFirstItemClickListener(firstItemClick: () -> Unit) {
        this.firstItemClick = firstItemClick
    }

    fun setOnSecondItemClickListener(secondItemClick: () -> Unit) {
        this.secondItemClick = secondItemClick
    }

    fun setFirstItem(@DrawableRes drawableId: Int) {
        setDrawableDrawableInt(binding.firstItem, drawableId)
    }

    fun setSecondItem(@DrawableRes drawableId: Int) {
        setDrawableDrawableInt(binding.secondItem, drawableId)
    }





    fun setTitleVisibility(isVisible: Boolean) {
        if (isVisible)
        binding.title.visibility= View.VISIBLE
        else
            binding.title.visibility= View.GONE
    }

}