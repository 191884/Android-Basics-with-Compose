package com.snappy.tiptime

import android.icu.text.NumberFormat
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.snappy.tiptime.ui.theme.TipTimeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipTimeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize()) {
                    TipTimeScreen()
                }
            }
        }
    }
}

@Composable
fun EditNumberField(
    value: String,
    @StringRes label: Int,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions
){
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = {Text( stringResource(label))},
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
    )

}

@Composable
fun TipTimeScreen(){
    Column(modifier = Modifier.padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        var amountInput by remember { mutableStateOf("0")}
        var tipInput by remember { mutableStateOf("15.0")}
        val amount = amountInput.toDoubleOrNull()?:0.0
        val tipPercent  = tipInput.toDoubleOrNull()?:0.0
        val tip = calculateTip(amount, tipPercent)
        val focusManager = LocalFocusManager.current

        Text(
            text = stringResource(id = R.string.calculate_tip),
            fontSize = 24.sp,
            modifier = Modifier.align(
                CenterHorizontally
            )
        )
        Spacer(Modifier.height(16.dp))

        EditNumberField(
            amountInput,
            R.string.bill_amount,
            onValueChange =  { amountInput = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {focusManager.moveFocus(FocusDirection.Down)}
            )
        )

        Spacer(Modifier.height(16.dp))

        EditNumberField(
            value = tipInput,
            label = R.string.how_was_the_service,
            onValueChange =  {tipInput = it},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            )
        )

        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(id = R.string.tip_amount, tip),
            modifier = Modifier.align(CenterHorizontally),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyPreview(){
    TipTimeTheme {
        TipTimeScreen()
    }
}

private fun calculateTip(
    amount: Double,
    tipPercent: Double =15.0
): String {
    val tip = tipPercent / 100* amount
    return NumberFormat.getCurrencyInstance().format(tip)
}