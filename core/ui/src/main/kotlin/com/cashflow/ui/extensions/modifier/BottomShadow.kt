package com.cashflow.ui.extensions.modifier

import android.graphics.BlurMaskFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.bottomShadow(
    color: Color = Color.Black,
    cornersRadius: Dp = 0.dp,
    height: Dp = 0.dp,
    blur: Dp = 0.dp,
) = drawBehind {
    val paint = Paint()
    val rect = Rect(Offset(0f, size.height / 2), size.copy(height = size.height / 2 + height.toPx()))
    drawIntoCanvas {
        paint.color = color
        paint.isAntiAlias = true
        val frameworkPaint = paint.asFrameworkPaint()
        frameworkPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
        if (blur.toPx() > 0) {
            frameworkPaint.maskFilter = BlurMaskFilter(blur.toPx(), BlurMaskFilter.Blur.NORMAL)
        }
        it.drawRoundRect(
            left = rect.left,
            top = rect.top,
            right = rect.right,
            bottom = rect.bottom,
            cornersRadius.toPx(),
            cornersRadius.toPx(),
            paint
        )
        frameworkPaint.xfermode = null
        frameworkPaint.maskFilter = null
    }
}

@Preview
@Composable
private fun BottomShadowPreview() {
    Box(
        modifier = Modifier
            .size(200.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Box(modifier = Modifier
            .bottomShadow(
                height = 4.dp,
                cornersRadius = 12.dp,
                blur = 4.dp
            )
            .size(100.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.Blue)
        )
    }
}
