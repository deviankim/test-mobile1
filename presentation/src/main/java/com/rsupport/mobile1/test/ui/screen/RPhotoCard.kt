package com.rsupport.mobile1.test.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.rsupport.mobile1.test.ui.theme.RColor

@Composable
fun RPhotoCard(
    photoURL: String,
    title: String,
    artist: String,
    uploadDate: String,
    favorite: Boolean,
    onFavorite: () -> Unit
) {

    var isExpanded by rememberSaveable { mutableStateOf(false) }
    Surface(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Card(
            onClick = { isExpanded = !isExpanded },
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                model = photoURL,
                contentScale = ContentScale.FillBounds,
                contentDescription = null
            )
            AnimatedVisibility(
                visible = isExpanded,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically(),
                modifier = Modifier.background(RColor.Gray)
            ) {
                Column(
                    modifier = Modifier.padding(vertical = 10.dp, horizontal = 14.dp),
                ) {
                    ExplainRow("title ", title)
                    ExplainRow("Artist", artist)
                    ExplainRow("Upload Date", uploadDate)
                }
            }
        }
        Box(
            contentAlignment = Alignment.TopEnd,
            modifier = Modifier.padding(6.dp)
        ) {
            IconButton(
                onClick = { onFavorite() },
            ) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = Icons.Default.Favorite,
                    tint = if (favorite) RColor.Primary else RColor.BackGround,
                    contentDescription = ""
                )
            }
        }
    }
}

@Composable
fun ExplainRow(
    rowTitle: String,
    explain: String
) {
    Row {
        Text(
            text = rowTitle,
            color = RColor.Primary,
            fontSize = 14.sp,
        )
        Text(
            text = ": $explain",
            fontSize = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
fun PhotoCardPreview() {
    RPhotoCard("", "", "", "", true, {})
}