package com.mistigo.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.NearMe
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mistigo.app.data.BoardingState
import com.mistigo.app.data.IntercityDeparture
import com.mistigo.app.data.SampleData
import com.mistigo.app.ui.components.*
import com.mistigo.app.ui.theme.*

@Composable
fun IntercityScreen(onBack: () -> Unit) {
    var filter by remember { mutableStateOf("Todos") }
    val filters = listOf("Todos", "Ciudad del Norte", "Puerto Sur", "Valle Alto")
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)) {
        BackHeader(onBack, "Terminal de Buses · Ciudad Capital", "Viajes entre", "ciudades")
        Spacer(Modifier.height(14.dp))
        Row(
            Modifier.fillMaxWidth().clip(RoundedCornerShape(14.dp)).background(OrangeSoft).padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(Icons.Rounded.NearMe, null, tint = Orange, modifier = Modifier.size(20.dp))
            Spacer(Modifier.width(10.dp))
            Column(Modifier.weight(1f)) {
                Text("¿Cómo llego a la terminal?", color = Ink, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                Text("R12 · 19 min desde tu ubicación", color = Muted, fontSize = 12.sp)
            }
            Icon(Icons.Rounded.ChevronRight, null, tint = Muted)
        }
        Spacer(Modifier.height(14.dp))
        Row(Modifier.horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            filters.forEach { f -> FilterChip(f, f == filter, { filter = f }) }
        }
        Spacer(Modifier.height(14.dp))
        SampleData.intercity.forEach { d ->
            DepartureCard(d)
            Spacer(Modifier.height(12.dp))
        }
    }
}

@Composable
private fun DepartureCard(d: IntercityDeparture) {
    val (stateText, stateColor) = when (d.boarding) {
        BoardingState.LEFT -> "Ya salió" to Muted
        BoardingState.BOARDING -> "Abordando" to Orange
        BoardingState.ON_TIME -> "A tiempo" to Success
    }
    val seatsColor = if (d.seats.startsWith("Sin")) Danger else Success
    RumboCard(Modifier.fillMaxWidth(), padding = 16.dp) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.width(74.dp)) {
                Text(d.time, color = Ink, fontWeight = FontWeight.Bold, fontSize = 22.sp)
                Text(stateText, color = stateColor, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
            }
            Spacer(Modifier.width(10.dp))
            Column(Modifier.weight(1f)) {
                Text(d.destination, color = Ink, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                Text("${d.duration} · ${d.company}", color = Muted, fontSize = 12.sp)
            }
            Box(Modifier.clip(RoundedCornerShape(10.dp)).background(Ink).padding(horizontal = 10.dp, vertical = 5.dp)) {
                Text(d.gate, color = Color(0xFFFFFFFF), fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
            }
        }
        Spacer(Modifier.height(10.dp))
        Box(Modifier.fillMaxWidth().height(1.dp).background(HairLine))
        Spacer(Modifier.height(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("💺 ${d.seats}", color = seatsColor, fontSize = 12.sp, fontWeight = FontWeight.Medium, modifier = Modifier.weight(1f))
            Box(
                Modifier.clip(CircleShape).border(1.dp, HairLine, CircleShape).padding(horizontal = 16.dp, vertical = 8.dp),
            ) { Text("Recordarme", color = Ink, fontWeight = FontWeight.SemiBold, fontSize = 13.sp) }
        }
    }
}
