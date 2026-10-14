package com.mistigo.app.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import com.mistigo.app.ui.theme.*

/** Fondo de mapa estilizado (calles, manzanas, parque) para las pantallas con mapa. */
@Composable
fun FauxMap(modifier: Modifier = Modifier) {
    Box(modifier) {
        Canvas(Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height
            drawRect(Color(0xFFDDE3EA))

            // Parque (zona verde)
            drawCircle(Color(0xFFCADEC4), radius = w * 0.26f, center = Offset(w * 0.82f, h * 0.52f))

            val road = Color(0xFFF2F4F7)
            val thick = w * 0.055f
            // Avenidas horizontales
            listOf(0.22f, 0.5f, 0.78f).forEach { fy ->
                drawLine(road, Offset(0f, h * fy), Offset(w, h * fy),
                    strokeWidth = thick, cap = StrokeCap.Round)
            }
            // Avenidas verticales
            listOf(0.25f, 0.55f, 0.85f).forEach { fx ->
                drawLine(road, Offset(w * fx, 0f), Offset(w * fx, h),
                    strokeWidth = thick, cap = StrokeCap.Round)
            }

            // Línea R12 (naranja) y C4 (verde) como rutas destacadas
            drawLine(LineR12, Offset(w * 0.25f, h * 0.78f), Offset(w * 0.25f, h * 0.22f),
                strokeWidth = w * 0.02f, cap = StrokeCap.Round)
            drawLine(LineR12, Offset(w * 0.25f, h * 0.22f), Offset(w * 0.85f, h * 0.22f),
                strokeWidth = w * 0.02f, cap = StrokeCap.Round)
            drawLine(LineC4, Offset(0f, h * 0.5f), Offset(w * 0.55f, h * 0.5f),
                strokeWidth = w * 0.02f, cap = StrokeCap.Round)
            drawLine(LineR7, Offset(w * 0.85f, 0f), Offset(w * 0.85f, h),
                strokeWidth = w * 0.02f, cap = StrokeCap.Round)
        }
    }
}
