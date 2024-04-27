package com.rsupport.mobile1.test.activity.core.ui

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.flow.collectLatest

// https://manavtamboli.medium.com/infinite-list-paged-list-in-jetpack-compose-b10fc7e74768
@Composable
fun LazyGridState.OnBottomReached(
  // tells how many items before we reach the bottom of the list
  // to call onLoadMore function
  buffer: Int = 0,
  onLoadMore: () -> Unit,
) {
  // Buffer must be positive.
  // Or our list will never reach the bottom.
  require(buffer >= 0) { "buffer cannot be negative, but was $buffer" }

  LaunchedEffect(this) {
    snapshotFlow { layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0 }
      .collectLatest { lastIndex ->
        if (lastIndex >= layoutInfo.totalItemsCount - 1 - buffer) onLoadMore()
      }
  }
}
