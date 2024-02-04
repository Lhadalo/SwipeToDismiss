package com.example.dismisstodoapp.ui.screens

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dismisstodoapp.ui.components.ListItem
import com.example.dismisstodoapp.ui.swipetodismiss.DefaultConfig
import com.example.dismisstodoapp.ui.swipetodismiss.DismissConfig
import com.example.dismisstodoapp.ui.swipetodismiss.SwipeToDismissItem

@Composable
fun EmailsScreen() {
    val viewModel = viewModel<EmailsScreenViewModel>()
    val listState = rememberLazyListState()

    Column(Modifier.fillMaxSize()) {
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(viewModel.emails, key = { it.subject }) { email ->
                SwipeToDismissItem(
                    defaultConfig = DefaultConfig(
                        backgroundColor = MaterialTheme.colorScheme.background,
                        animationSpec = spring(Spring.DampingRatioHighBouncy)
                    ),
                    dismissToStartConfig = DismissConfig(
                        iconVector = Icons.Default.Delete,
                        backgroundColor = Color.Red,
                        iconTint = Color.White.copy(alpha = .9f),
                        onDismiss = { viewModel.removeEmail(email) }
                    ),
                    dismissToEndConfig = DismissConfig(
                        iconVector = Icons.Default.Done,
                        backgroundColor = Color.Green,
                        iconTint = Color.White,
                        onDismiss = { false }
                    ),
                    modifier = Modifier.animateItemPlacement()
                ) {
                    Card(
                        shape = RectangleShape,
                        elevation = CardDefaults.cardElevation(1.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        ListItem(title = email.subject, subtitle = email.text, onClick = {})
                    }
                }
            }
        }
    }
}
