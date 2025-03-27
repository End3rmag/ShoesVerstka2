package com.example.myapplication.ui.screen.Otp

import android.health.connect.datatypes.units.Length
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun OtpTextFiled(
    length: Int,
    onValueChanged:(String)->Unit,
    value: String,
    modifier: Modifier =Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    keyboardActions: KeyboardActions = KeyboardActions()
){
    BasicTextField(
        value = value
    )
}