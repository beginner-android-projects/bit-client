package com.example.bitclient.ui.bottomnavigation

import android.animation.ValueAnimator
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.annotation.RequiresApi
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import java.lang.Float.max
import java.lang.Float.min

class BottomNavigationBehavior<V : View>(context: Context, attrs: AttributeSet) :
    CoordinatorLayout.Behavior<V>(context, attrs) {

    @ViewCompat.NestedScrollType
    private var lastStartedType: Int = 0
    private var offsetAnimator: ValueAnimator? = null
    var isSnappingEnabled = false

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout, child: V, directTargetChild: View, target: View, axes: Int, type: Int
    ): Boolean {
        if (axes != ViewCompat.SCROLL_AXIS_VERTICAL)
            return false

        lastStartedType = type
        offsetAnimator?.cancel()

        return true
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout, child: V, target: View, dx: Int, dy: Int, consumed: IntArray, type: Int
    ) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
        child.translationY = max(0f, min(child.height.toFloat(), child.translationY + dy))
    }

    override fun onStopNestedScroll(coordinatorLayout: CoordinatorLayout, child: V, target: View, type: Int) {
        if (!isSnappingEnabled) {

            // add snap behaviour
            // Logic here borrowed from AppBarLayout onStopNestedScroll code
            if (lastStartedType == ViewCompat.TYPE_TOUCH || type == ViewCompat.TYPE_NON_TOUCH) {
                // find nearest seam
                val currTranslation = child.translationY
                val childHalfHeight = child.height * 0.5f

                // translate down
                if (currTranslation >= childHalfHeight) {
                    animateBarVisibility(child, isVisible = false)
                }
                // translate up
                else {
                    animateBarVisibility(child, isVisible = true)
                }
            }
        }
    }

    private fun animateBarVisibility(child: View, isVisible: Boolean) {
        if (offsetAnimator == null) {
            offsetAnimator = ValueAnimator().apply {
                interpolator = DecelerateInterpolator()
                duration = 150L
            }

            offsetAnimator?.addUpdateListener {
                child.translationY = it.animatedValue as Float
            }
        } else {
            offsetAnimator?.cancel()
        }

        val targetTranslation = if (isVisible) 0f else child.height.toFloat()
        offsetAnimator?.setFloatValues(child.translationY, targetTranslation)
        offsetAnimator?.start()
    }
}