package com.summer.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun Sbc(){
    Column(modifier = Modifier.verticalScroll(ScrollState(0))){
        for(name in 0..100){
            Text(text = "${name}", color = Color.Red)
        }
        Image(
            painter = painterResource(id = com.summer.view.R.drawable.src),
            contentDescription = "abc"
        )
    }


}