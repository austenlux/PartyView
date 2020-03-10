package com.lux.partyview

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatTextView

class PartyTextView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    AppCompatTextView(context, attrs), PartyView {

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
            val a = context.obtainStyledAttributes(attrs, R.styleable.PartyTextView)
            if (a.hasValue(R.styleable.PartyTextView_partyTextView_mode)) {
                mode = PartyView.PartyMode.values()[a.getInt(
                    R.styleable.PartyTextView_partyTextView_mode,
                    1
                )]
            }
            if (a.hasValue(R.styleable.PartyTextView_partyTextView_colors)) {
                colors = resources.getIntArray(
                    a.getResourceId(
                        R.styleable.PartyTextView_partyTextView_colors,
                        R.array.party
                    )
                )
            }
            if (a.hasValue(R.styleable.PartyTextView_partyTextView_colorStep)) {
                colorStep = a.getFloat(R.styleable.PartyTextView_partyTextView_colorStep, 0.2f)
            }
            if (a.hasValue(R.styleable.PartyTextView_partyTextView_radius)) {
                radius = a.getInt(R.styleable.PartyTextView_partyTextView_radius, 30)
            }
            if (a.hasValue(R.styleable.PartyTextView_partyTextView_radianStep)) {
                radianStep = a.getFloat(R.styleable.PartyTextView_partyTextView_radianStep, 0.2f)
            }
            if (a.hasValue(R.styleable.PartyTextView_partyTextView_reverse)) {
                reverse = a.getBoolean(R.styleable.PartyTextView_partyTextView_reverse, false)
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