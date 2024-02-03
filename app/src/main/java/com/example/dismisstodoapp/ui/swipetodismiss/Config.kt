package com.example.dismisstodoapp.ui.swipetodismiss

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

class DefaultConfig(
    val backgroundColor: Color = Color.LightGray,
    val iconTint: Color = Color.Black,
    val animateIcons: Boolean = true,
    val animationSpec: AnimationSpec<Float> = tween()
)

class DismissConfig private constructor(
    val iconVector: ImageVector?,
    @DrawableRes val iconRes: Int?,
    val backgroundColor: Color?,
    val iconTint: Color?,
    val onDismiss: () -> Boolean
) {
    constructor(
        iconVector: ImageVector,
        backgroundColor: Color? = null,
        iconTint: Color? = null,
        onDismiss: () -> Boolean = { false }
    ) : this(iconVector, null, backgroundColor, iconTint, onDismiss)

    constructor(
        @DrawableRes iconRes: Int,
        backgroundColor: Color? = null,
        iconTint: Color? = null,
        onDismiss: () -> Boolean = { false }
    ) : this(null, iconRes, backgroundColor, iconTint, onDismiss)
}