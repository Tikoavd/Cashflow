package com.cashflow.ui.extensions.modifier

import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.drawLeftZigZag(zigZagEdgeWidth: Dp = 8.dp) = composed {
    val zigZagEdgeWidthPixels = with(LocalDensity.current) { zigZagEdgeWidth.toPx() }
    this.then(
        Modifier.drawWithCache {
            val textRectangleHeight = size.height
            val zigZagEdgeTriangleBaseLength = textRectangleHeight / TRIANGLES_COUNT

            val textRectangle = Path().apply {
                reset()
                //start drawing the background with a zigzag offset
                moveTo(x = zigZagEdgeWidthPixels, y = 0f)
                lineTo(size.width + zigZagEdgeWidthPixels, 0f)
                lineTo(size.width + zigZagEdgeWidthPixels, size.height)
                //stop drawing before zig zag. Bottom left corner minus zigzag widht
                lineTo(zigZagEdgeWidthPixels, size.height)

                //draw a zig-zag edge
                var drawingDistance = size.height
                val triangleHalfSize = zigZagEdgeTriangleBaseLength / 2
                repeat(TRIANGLES_COUNT) {
                    drawingDistance -= triangleHalfSize
                    lineTo(0f, drawingDistance)
                    drawingDistance -= triangleHalfSize
                    lineTo(zigZagEdgeWidthPixels, drawingDistance)
                }
                close()
            }
            onDrawWithContent {
                drawPath(
                    path = textRectangle,
                    color = Color.White
                )
                //offset content by the zigzag width to center it within the rectangle
                translate(left = zigZagEdgeWidthPixels) {
                    this@onDrawWithContent.drawContent()
                }
            }
        }
    )
}

fun Modifier.drawRightZigZag(zigZagEdgeWidth: Dp = 8.dp, color: Color) = composed {
    val zigZagEdgeWidthPixels = with(LocalDensity.current) { zigZagEdgeWidth.toPx() }
    this.then(
        Modifier.drawWithCache {
            val textRectangleHeight = size.height
            val zigZagEdgeTriangleBaseLength = textRectangleHeight / TRIANGLES_COUNT

            val textRectangle = Path().apply {
                reset()
                // Start from the top-left corner
                moveTo(0f, 0f)
                // Draw to the top-right corner minus zigzag width
                lineTo(size.width - zigZagEdgeWidthPixels, 0f)

                // Draw the first triangle at the top
                lineTo(size.width, 0f)  // Pointing outward
                var drawingDistance = 0f

                val triangleHalfSize = zigZagEdgeTriangleBaseLength / 2

                // Draw the zig-zag edge
                repeat(TRIANGLES_COUNT) {
                    drawingDistance += triangleHalfSize
                    lineTo(size.width - zigZagEdgeWidthPixels, drawingDistance)
                    drawingDistance += triangleHalfSize
                    lineTo(size.width, drawingDistance)  // Outward again
                }

                // Draw to the bottom-right corner minus zigzag width
                lineTo(size.width - zigZagEdgeWidthPixels, size.height)
                // Draw to bottom-left corner
                lineTo(0f, size.height)
                close()
            }

            onDrawWithContent {
                drawPath(
                    path = textRectangle,
                    color = color
                )
                // Offset content to the left to center it within the rectangle
                translate(left = -zigZagEdgeWidthPixels) {
                    this@onDrawWithContent.drawContent()
                }
            }
        }
    )
}

private const val TRIANGLES_COUNT = 12
