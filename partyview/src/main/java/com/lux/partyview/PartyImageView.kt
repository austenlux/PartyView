package com.lux.partyview

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatImageView

class PartyImageView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    AppCompatImageView(context, attrs), PartyView {

    override var colors: IntArray = resources.getIntArray(R.array.party)
    override var colorIndex: Float = 0f
    override var colorStep: Float = 0.2f
    override var radius: Int = 30
    override var radians: Double = 0.toDouble()
    override var radianStep: Float = 0.2f
    override var shouldSpin: Boolean = false
    override var shouldStrobe: Boolean = false
    override var reverse: Boolean = false
    override var mode: PartyView.PartyMode = PartyView.PartyMode.ON
    override var partyClickListener: OnClickListener? = null

    init {
        setLayerType(View.LAYER_TYPE_HARDWARE, null)
        setOnClickListener(partyClickListener)

        if (null != attrs) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.PartyImageView)
            if (a.hasValue(R.styleable.PartyImageView_partyImageView_mode)) {
                mode = PartyView.PartyMode.values()[a.getInt(
                    R.styleable.PartyImageView_partyImageView_mode,
                    1
                )]
            }
            if (a.hasValue(R.styleable.PartyImageView_partyImageView_colors)) {
                colors = resources.getIntArray(
                    a.getResourceId(
                        R.styleable.PartyImageView_partyImageView_colors,
                        R.array.party
                    )
                )
            }
            if (a.hasValue(R.styleable.PartyImageView_partyImageView_colorStep)) {
                colorStep = a.getFloat(R.styleable.PartyImageView_partyImageView_colorStep, 0.2f)
            }
            if (a.hasValue(R.styleable.PartyImageView_partyImageView_radius)) {
                radius = a.getInt(R.styleable.PartyImageView_partyImageView_radius, 30)
            }
            if (a.hasValue(R.styleable.PartyImageView_partyImageView_radianStep)) {
                radianStep = a.getFloat(R.styleable.PartyImageView_partyImageView_radianStep, 0.2f)
            }
            if (a.hasValue(R.styleable.PartyImageView_partyImageView_reverse)) {
                reverse = a.getBoolean(R.styleable.PartyImageView_partyImageView_reverse, false)
            }
            a.recycle()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        party(this, canvas)
    }

    override fun setOnClickListener(l: OnClickListener?) {
        partyClickListener = l
        super.setOnClickListener(tapModeListener)
    }
}