package com.example.dismisstodoapp.ui.swipetodismiss

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissState
import androidx.compose.material3.DismissValue
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DismissBackground(
    dismissState: DismissState,
    defaultConfig: DefaultConfig,
    dismissToEndConfig: DismissConfig?,
    dismissToStartConfig: DismissConfig?
) {
    val direction = dismissState.dismissDirection ?: return
    val alignment = remember {
        when (direction) {
            DismissDirection.StartToEnd -> Alignment.CenterStart
            DismissDirection.EndToStart -> Alignment.CenterEnd
        }
    }

    val icon = remember {
        when (direction) {
            DismissDirection.StartToEnd -> dismissToEndConfig?.iconVector
            DismissDirection.EndToStart -> dismissToStartConfig?.iconVector
        }
    }

    val backgroundColor by animateColorAsState(
        when (dismissState.targetValue) {
            DismissValue.Default -> defaultConfig.backgroundColor
            DismissValue.DismissedToEnd -> dismissToEndConfig?.backgroundColor
                ?: defaultConfig.backgroundColor

            DismissValue.DismissedToStart -> dismissToStartConfig?.backgroundColor
                ?: defaultConfig.backgroundColor
        },
        label = "Color State"
    )

    val iconTint by animateColorAsState(
        when (dismissState.targetValue) {
            DismissValue.Default -> defaultConfig.iconTint
            DismissValue.DismissedToEnd -> dismissToEndConfig?.iconTint ?: Color.Black
            DismissValue.DismissedToStart -> dismissToStartConfig?.iconTint ?: Color.Black
        },
        label = "Color State"
    )

    val animatedScale by animateFloatAsState(
        if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f,
        animationSpec = defaultConfig.animationSpec,
        label = "Icon Scale"
    )

    Box(
        Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(horizontal = 18.dp),
        contentAlignment = alignment
    ) {
        if (icon != null) {
            Icon(
                icon,
                contentDescription = "Localized description",
                tint = iconTint,
                modifier = Modifier
                    .size(28.dp)
                    .scale(if (defaultConfig.animateIcons) animatedScale else 1f)
            )
        }
    }
}