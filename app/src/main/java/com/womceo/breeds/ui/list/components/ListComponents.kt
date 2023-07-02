package com.womceo.breeds.ui.list.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ListButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    breedName: String
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        onClick = onClick,
        elevation = ButtonDefaults.elevatedButtonElevation(focusedElevation = 10.dp)
    ) {
        Text(text = breedName)
    }
}
