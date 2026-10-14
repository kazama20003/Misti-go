package com.mistigo.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mistigo.app.data.SampleData
import com.mistigo.app.data.Trip
import com.mistigo.app.data.lineColor
import com.mistigo.app.data.lineSoft
import com.mistigo.app.ui.components.*
import com.mistigo.app.ui.theme.*

@Composable
fun HistoryScreen(onBack: () -> Unit, onPlans: () -> Unit) {
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)) {
        BackHeader(onBack, "Octubre", "Tus", "viajes")
        Spacer(Modifier.height(14.dp))
        RumboCard(Modifier.fillMaxWidth(), padding = 16.dp) {
            Row {
                HStat("Viajes", "24", Modifier.weight(1f))
                HStat("En bus", "9 h 40 m", Modifier.weight(1f))
                HStat("Distancia", "86 km", Modifier.weight(1f))
            }
        }
        Spacer(Modifier.height(12.dp))
        // Tarjeta bloqueada
        Box(
            Modifier.fillMaxWidth().clip(RoundedCornerShape(18.dp)).background(CardGrey).padding(24.dp),
            contentAlignment = Alignment.Center,
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(Icons.Rounded.Lock, null, tint = Muted, modifier = Modifier.size(26.dp))
                Spacer(Modifier.height(8.dp))
                Text("Estadísticas de viaje con Plus", color = InkSoft, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                Spacer(Modifier.height(10.dp))
                InkButton("Ver planes", onPlans)
            }
        }
        Spacer(Modifier.height(16.dp))
        var currentDay = ""
        SampleData.historyTrips.forEach { t ->
            if (t.day != currentDay) {
                currentDay = t.day
                Text(t.day, color = Muted, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                Spacer(Modifier.height(8.dp))
            }
            HistoryCard(t)
            Spacer(Modifier.height(12.dp))
        }
    }
}

@Composable
private fun HStat(label: String, value: String, modifier: Modifier) {
    Column(modifier) {
        Text(label, color = Muted, fontSize = 12.sp)
        Text(value, color = Ink, fontWeight = FontWeight.Bold, fontSize = 18.sp)
    }
}

@Composable
private fun HistoryCard(t: Trip) {
    RumboCard(Modifier.fillMaxWidth(), padding = 16.dp) {
        Row {
            Text("${t.from} → ${t.to}", color = Ink, fontWeight = FontWeight.Bold, fontSize = 16.sp, modifier = Modifier.weight(1f))
            Text(t.price, color = Ink, fontWeight = FontWeight.Bold, fontSize = 15.sp)
        }
        Text("${t.time} · ${t.duration}", color = Muted, fontSize = 12.sp)
        Spacer(Modifier.height(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            t.lines.forEach { l -> LineTag(l, lineColor(l), lineSoft(l)); Spacer(Modifier.width(8.dp)) }
            Spacer(Modifier.weight(1f))
            Text("Repetir viaje", color = Orange, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
        }
    }
}
