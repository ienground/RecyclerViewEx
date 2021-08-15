package net.ienlab.recyclerviewex.utils

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation

class ToggleAnimation {
    companion object {
        fun toggleArrow(view: View, isExpanded: Boolean): Boolean {
            view.animate().setDuration(200).rotation(if (isExpanded) 180f else 0f)
            return isExpanded
        }

        fun expand(view: View): Long {
//            view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
            view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))
            val actualHeight = view.measuredHeight

            view.layoutParams.height = 0
            view.visibility = View.VISIBLE

            val animation = object: Animation() {
                override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                    view.layoutParams.height = if (interpolatedTime == 1f) ViewGroup.LayoutParams.WRAP_CONTENT else (actualHeight * interpolatedTime).toInt()
//                    Log.d(TAG, "size: ${(actualHeight * interpolatedTime).toInt()}")
                    view.requestLayout()
                }
            }

            val duration = (actualHeight / view.context.resources.displayMetrics.density).toLong() * 4
            animation.duration = duration
            view.startAnimation(animation)

            return duration
        }

        fun collapse(view: View): Long {
            val actualHeight = view.measuredHeight

            val animation = object: Animation() {
                override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                    if (interpolatedTime == 1f) {
                        view.visibility = View.GONE
                    } else {
                        view.layoutParams.height = (actualHeight - (actualHeight * interpolatedTime)).toInt()
//                        Log.d(TAG, "size: ${(actualHeight - (actualHeight * interpolatedTime)).toInt()}")
                        view.requestLayout()
                    }
                }
            }

            val duration = (actualHeight / view.context.resources.displayMetrics.density).toLong() * 4
            animation.duration = duration
            view.startAnimation(animation)

            return duration
        }
    }
}