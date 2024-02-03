package com.example.dismisstodoapp.ui.swipetodismiss

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissState
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier

@Composable
fun SwipeToDismissItem(
    modifier: Modifier = Modifier,
    defaultConfig: DefaultConfig = DefaultConfig(),
    dismissToEndConfig: DismissConfig? = null,
    dismissToStartConfig: DismissConfig? = null,
    content: @Composable RowScope.(DismissState) -> Unit,
) {
    val dismissState = rememberDismissState()
    if (dismissToStartConfig != null) {
        val isDismissedEndToStart = dismissState.isDismissed(DismissDirection.EndToStart)
        LaunchedEffect(isDismissedEndToStart) {
            if (isDismissedEndToStart) {
                if (dismissToStartConfig.onDismiss()) {
                    return@LaunchedEffect
                }
                dismissState.reset()
            }
        }
    }

    if (dismissToEndConfig != null) {
        val isDismissedStartToEnd = dismissState.isDismissed(DismissDirection.StartToEnd)
        LaunchedEffect(isDismissedStartToEnd) {
            if (isDismissedStartToEnd) {
                if (dismissToEndConfig.onDismiss()) {
                    return@LaunchedEffect
                }
                dismissState.reset()
            }
        }
    }

    val enabledDirections =
        mutableSetOf<DismissDirection>().apply {
            if (dismissToStartConfig != null) add(DismissDirection.EndToStart)
            if (dismissToEndConfig != null) add(DismissDirection.StartToEnd)
        }

    SwipeToDismiss(
        state = dismissState,
        background = {
            DismissBackground(
                dismissState,
                defaultConfig = defaultConfig,
                dismissToEndConfig = dismissToEndConfig,
                dismissToStartConfig = dismissToStartConfig
            )
        },
        dismissContent = { content(dismissState) },
        directions = enabledDirections,
        modifier = modifier
    )
}