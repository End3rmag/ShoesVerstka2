package com.example.myapplication.ui.screen.Otp


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun testOtpTextFiels() {
    val text = remember { mutableStateOf("") }
    OtpTextField(text.value, 4){
        text.value = it
    }
}

@Composable
fun OtpTextField(
    value: String,
    length: Int,
    modifier: Modifier = Modifier,
    boxWidth: Dp = 50.dp,
    boxHeight: Dp= 100.dp,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    keyboardActions: KeyboardActions = KeyboardActions(),
    onValueChange: (String) -> Unit) {
    BasicTextField(
        value = value,
        onValueChange = {
            if(it.length <= length){
                onValueChange(it)
            }
        },
        modifier = modifier,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        decorationBox = {
            val spaceBoxBetween = 12.dp
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .size((boxWidth + spaceBoxBetween) * length, height = boxHeight),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                repeat(length){
                    Box(
                        modifier = Modifier
                            .width(46.dp)
                            .fillMaxHeight()
                            .border(width = 1.dp, color = Color.Blue),
                        contentAlignment = Alignment.Center
                    ){
                        Text(text = value.getOrNull(it)?.toString() ?: "")
                    }
                }
            }
        }
    )
}