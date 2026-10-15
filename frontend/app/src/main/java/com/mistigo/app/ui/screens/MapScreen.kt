package com.mistigo.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mistigo.app.data.SampleData
import com.mistigo.app.data.Stop
import com.mistigo.app.ui.components.*
import com.mistigo.app.ui.theme.*

@Composable
fun MapScreen(onBack: () -> Unit, onOpenTrip: () -> Unit) {
    var filter by remember { mutableStateOf("Todas") }
    val filters = listOf("Todas", "Línea R12", "Línea C4", "Línea R7")

    Box(Modifier.fillMaxSize()) {
        FauxMap(Modifier.fillMaxSize())

        Column(Modifier.fillMaxSize().padding(16.dp)) {
            // Barra superior
            Row(verticalAlignment = Alignment.CenterVertically) {
                CircleIconButton(onClick = onBack) {
                    Icon(Icons.Rounded.ArrowBackIosNew, null, tint = Ink, modifier = Modifier.size(18.dp))
                }
                Spacer(Modifier.width(10.dp))
                Row(
                    Modifier.weight(1f).clip(RoundedCornerShape(16.dp)).background(CardWhite)
                        .padding(horizontal = 16.dp, vertical = 14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(Icons.Rounded.Search, null, tint = Muted, modifier = Modifier.size(20.dp))
                    Spacer(Modifier.width(10.dp))
                    Text("Av. Ejército", color = Ink, fontSize = 15.sp)
                }
                Spacer(Modifier.width(10.dp))
                CircleIconButton(onClick = {}) {
                    Icon(Icons.Rounded.MyLocation, null, tint = Ink, modifier = Modifier.size(20.dp))
                }
            }

            Spacer(Modifier.height(12.dp))
            // Chips de filtro
            Row(
                Modifier.horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                filters.forEach { f ->
                    val dot = when (f) {
                        "Línea R12" -> LineR12
                        "Línea C4" -> LineC4
                        "Línea R7" -> LineR7
                        else -> null
                    }
                    MapChip(f, dot, f == filter) { filter = f }
                }
            }

            Spacer(Modifier.height(14.dp))
            // Etiquetas flotantes de ejemplo
            MapTag("Catedral · C4 4 min")
            Spacer(Modifier.weight(1f))

            // Hoja inferior: paradas cerca
            RumboCard(Modifier.fillMaxWidth(), padding = 18.dp) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Paradas cerca de ti", color = Ink, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Spacer(Modifier.weight(1f))
                    Text("Toca una etiqueta del mapa", color = Muted, fontSize = 11.sp)
                }
                Spacer(Modifier.height(10.dp))
                SampleData.nearbyStops.forEach { stop ->
                    NearbyStopRow(stop, onClick = onOpenTrip)
                }
            }
        }
    }
}

@Composable
private fun MapChip(text: String, dot: Color?, selected: Boolean, onClick: () -> Unit) {
    Row(
        Modifier.clip(CircleShape)
            .background(if (selected) Ink else CardWhite)
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 9.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (dot != null) {
            Box(Modifier.size(8.dp).clip(CircleShape).background(dot))
            Spacer(Modifier.width(6.dp))
        }
        Text(text, color = if (selected) Color(0xFFFFFFFF) else InkSoft,
            fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
    }
}

@Composable
private fun MapTag(text: String) {
    Box(
        Modifier.clip(CircleShape).background(CardWhite)
            .padding(horizontal = 14.dp, vertical = 8.dp),
    ) {
        Text(text, color = Ink, fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
    }
}

@Composable
private fun NearbyStopRow(stop: Stop, onClick: () -> Unit) {
    Row(
        Modifier.fillMaxWidth().clickable(onClick = onClick).padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            Modifier.size(42.dp).clip(RoundedCornerShape(12.dp)).background(stop.soft),
            contentAlignment = Alignment.Center,
        ) { Icon(Icons.Rounded.Flag, null, tint = stop.color, modifier = Modifier.size(20.dp)) }
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(stop.name, color = Ink, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
            Text("${stop.distance} · ${stop.walk}", color = Muted, fontSize = 12.sp)
        }
        Text("${stop.line} · ${stop.eta}", color = Ink, fontWeight = FontWeight.Bold, fontSize = 14.sp)
    }
}
