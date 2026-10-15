package com.mistigo.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DirectionsBus
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mistigo.app.ui.components.InkButton
import com.mistigo.app.ui.theme.*

@Composable
fun WelcomeScreen(onStart: () -> Unit, onSkip: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(listOf(OrangeLight, Orange, OrangeDark))
            )
            .padding(28.dp),
    ) {
        Column(Modifier.fillMaxSize().systemBarsPadding()) {
            Spacer(Modifier.height(24.dp))
            Text("Misti Go", color = Color(0xFFFFFFFF), fontSize = 34.sp, fontWeight = FontWeight.SemiBold)

            Spacer(Modifier.weight(0.4f))

            // Ilustración del bus dentro de un círculo tenue
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Box(
                    Modifier
                        .size(260.dp)
                        .clip(CircleShape)
                        .background(Color(0x1FFFFFFF)),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        Icons.Rounded.DirectionsBus,
                        contentDescription = null,
                        tint = Color(0xFFFFFFFF),
                        modifier = Modifier.size(150.dp),
                    )
                }
            }

            Spacer(Modifier.weight(0.5f))

            Text("— — — —", color = Color(0xCCFFFFFF), fontSize = 16.sp)
            Spacer(Modifier.height(14.dp))
            Text(
                buildAnnotatedString {
                    withStyle(SpanStyle(fontWeight = FontWeight.Normal)) { append("Muévete por tu ciudad,\n") }
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append("sin perderte") }
                },
                color = Color(0xFFFFFFFF),
                fontSize = 30.sp,
                lineHeight = 36.sp,
            )
            Spacer(Modifier.height(14.dp))
            Text(
                "Misti Go encuentra tu parada, te dice qué bus tomar y te avisa justo antes de bajar.",
                color = Color(0xE6FFFFFF),
                fontSize = 15.sp,
                lineHeight = 21.sp,
            )

            Spacer(Modifier.height(28.dp))
            InkButton("Activar mi ubicación", onClick = onStart, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(12.dp))
            Box(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0x33FFFFFF))
                    .clickable(onClick = onSkip)
                    .padding(vertical = 16.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text("Omitir", color = Color(0xFFFFFFFF),
                    fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
            }
            Spacer(Modifier.height(12.dp))
        }
    }
}
