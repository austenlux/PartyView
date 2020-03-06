package com.lux.partyview

import android.graphics.Canvas
import android.graphics.PorterDuff
import android.view.View
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

    fun onDraw(view: View, canvas: Canvas?) {
        if (shouldSpin!!) {
            translate(view)
        }
        if (shouldStrobe!!) {
            strobe(canvas)
        }
        view.invalidate()
    }

    fun translate(view: View) {
        view.translationX = radius?.times(cos(radians!!))!!.toFloat()
        view.translationY = radius?.times(sin(radians!!))!!.toFloat()
        radians = radianStep?.let { radians?.plus(it)?.rem(Math.PI.times(2)) }
    }

    fun strobe(canvas: Canvas?) {
        colorIndex?.let { colors?.get(it.toInt()) }?.let { canvas?.drawColor(it, PorterDuff.Mode.SRC_IN) }
        colorIndex = colors?.size?.let { colorStep?.let { it1 -> colorIndex?.plus(it1)?.rem(it) } }
    }

    fun onClick() {
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