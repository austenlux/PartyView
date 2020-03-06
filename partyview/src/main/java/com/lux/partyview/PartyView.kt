package com.lux.partyview

import android.graphics.Canvas
import android.graphics.PorterDuff
import android.view.View
import android.view.View.OnClickListener
import kotlin.math.cos
import kotlin.math.sin

interface PartyView {

    var colors: IntArray?
    var colorIndex: Float?
    var colorStep: Float?
    var radius: Int?
    var radians: Double?
    var radianStep: Float?
    var shouldSpin: Boolean?
    var shouldStrobe: Boolean?
    var mode: PartyMode?
    var reverse: Boolean?

    enum class PartyMode(value: Int) {
        OFF(0),
        ON(1),
        SPIN(2),
        STROBE(3),
        TAP_SPIN(4),
        TAP_STROBE(5),
        TAP_ON_OFF(6),
        TAP_THRU(7);
    }

    val clickListener: OnClickListener
        get() = OnClickListener { onClick() }

    fun onDraw(view: View, canvas: Canvas?) {
        refreshMode(view)
        if (shouldSpin!!) {
            translate(view)
        }
        if (shouldStrobe!!) {
            strobe(canvas)
        }
        centerView(view)
        view.invalidate()
    }

    fun refreshMode(view: View) {
        when (mode) {
            PartyMode.ON -> {
                shouldSpin = true
                shouldStrobe = true
                if (view.hasOnClickListeners()) {
                    view.setOnClickListener(null)
                }
            }
            PartyMode.OFF -> {
                shouldSpin = false
                shouldStrobe = false
                if (view.hasOnClickListeners()) {
                    view.setOnClickListener(null)
                }
            }
            PartyMode.SPIN -> {
                shouldSpin = true
                shouldStrobe = false
                if (view.hasOnClickListeners()) {
                    view.setOnClickListener(null)
                }
            }
            PartyMode.STROBE -> {
                shouldSpin = false
                shouldStrobe = true
                if (view.hasOnClickListeners()) {
                    view.setOnClickListener(null)
                }
            }
            PartyMode.TAP_SPIN,
            PartyMode.TAP_STROBE,
            PartyMode.TAP_ON_OFF,
            PartyMode.TAP_THRU -> {
                if (!view.hasOnClickListeners()) {
                    view.setOnClickListener(clickListener)
                }
            }
        }
    }

    fun translate(view: View) {
        view.translationX = radius?.times(cos(radians!!))!!.toFloat()
        view.translationY = radius?.times(sin(radians!!))!!.toFloat()
        radians = radianStep?.let {
            radians?.plus(
                if (reverse!!) {
                    it.times(-1)
                } else {
                    it
                }
            )?.rem(Math.PI.times(2))
        }
    }

    fun strobe(canvas: Canvas?) {
        colorIndex?.let { colors?.get(it.toInt()) }
            ?.let { canvas?.drawColor(it, PorterDuff.Mode.SRC_IN) }
        colorIndex = colors?.size?.let { colorStep?.let { it1 -> colorIndex?.plus(it1)?.rem(it) } }
    }

    fun centerView(view: View) {
        if (!shouldSpin!! && !shouldStrobe!! && view.translationX != 0f && view.translationY != 0f) {
            view.translationX = 0f
            view.translationY = 0f
        }
    }

    fun onClick() {
        when (mode) {
            PartyMode.TAP_SPIN -> {
                shouldSpin = !shouldSpin!!
            }
            PartyMode.TAP_STROBE -> {
                shouldStrobe = !shouldStrobe!!
            }
            PartyMode.TAP_ON_OFF -> {
                shouldSpin = !shouldSpin!!
                shouldStrobe = !shouldStrobe!!
            }
            PartyMode.TAP_THRU -> {
                if (!shouldStrobe!!) {
                    shouldStrobe = true
                } else if (shouldStrobe!! && !shouldSpin!!) {
                    shouldSpin = true
                } else {
                    shouldStrobe = false
                    shouldSpin = false
                }
            }
        }
    }
}