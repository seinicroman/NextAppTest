package com.example.nextapptest.common.shape

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.graphics.Matrix


class BottomWaveShape : Shape {

    private val rawPath = """
        M376 172.082
        C342.575 192.148 256.479 225.257 179.5 197.165
        C139.322 180.443 43.7732 152.016 -17 172.082
        V-267
        H376
        V172.082
        Z
    """.trimIndent()

    private val viewBoxWidth = 359f
    private val viewBoxHeight = 210f

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = PathParser().parsePathString(rawPath).toPath()

        val matrix = Matrix().apply {
            scale(
                x = size.width / viewBoxWidth,
                y = size.height / viewBoxHeight
            )
        }
        path.transform(matrix)

        return Outline.Generic(path)
    }
}
