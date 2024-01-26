package com.example.arduino_led_app.ui.composables

import android.graphics.Shader
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RainbowText(
    modifier: Modifier = Modifier,
    text: String,
    textSize: TextUnit = 20.sp,

) {
    Canvas(modifier = modifier.fillMaxWidth().height(50.dp)) {
        val paint = android.graphics.Paint().apply {
            isAntiAlias = true
            this.textSize = textSize.toPx()
            style = android.graphics.Paint.Style.FILL
        }

        val width = paint.measureText(text)
        val rainbowShader = android.graphics.LinearGradient(
            0f, 0f, width, 0f,
            intArrayOf(
                Color(0xFFFF685D).toArgb(),
                Color(0xFFFF64F0).toArgb(),
                Color(0xFF5155FF).toArgb(),
                Color(0xFF54EDFF).toArgb(),
                Color(0xFF5BFF7B).toArgb(),
                Color(0xFFFDFF59).toArgb(),
                Color(0xFFFFCA55).toArgb(),
            ),
            floatArrayOf(0f, 0.16f, 0.32f, 0.48f, 0.64f , 0.80f, 1f),
            Shader.TileMode.CLAMP
        )
        paint.shader = rainbowShader

        drawIntoCanvas { canvas ->
            val textHeight = paint.descent() - paint.ascent()
            val textOffset = (textHeight / 2) - paint.descent()
            canvas.nativeCanvas.drawText(
                text,
                0f,
                size.height / 2 + textOffset,
                paint
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRainbowText() {
    RainbowText(
        text = "RAINBOW",
        textSize = 24.sp,
        modifier = Modifier.fillMaxWidth()
    )
}
