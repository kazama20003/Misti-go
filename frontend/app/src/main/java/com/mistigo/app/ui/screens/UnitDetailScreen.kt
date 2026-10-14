package com.mistigo.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mistigo.app.ui.components.*
import com.mistigo.app.ui.theme.*

@Composable
fun UnitDetailScreen(onBack: () -> Unit) {
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)) {
        BackHeader(onBack, "Unidad en servicio", "Unidad", "2417")
        Spacer(Modifier.height(14.dp))
        // Ilustración del bus
        Box(Modifier.fillMaxWidth().height(170.dp).clip(RoundedCornerShape(20.dp)).background(Color(0xFFD7DDE4))) {
            Row(
                Modifier.align(Alignment.TopStart).padding(12.dp)
                    .clip(CircleShape).background(CardWhite).padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text("Línea ", color = Muted, fontSize = 12.sp)
                Text("R12 · TUM", color = Ink, fontWeight = FontWeight.Bold, fontSize = 12.sp)
            }
            Row(
                Modifier.align(Alignment.TopEnd).padding(12.dp)
                    .clip(CircleShape).background(LineC4Soft).padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(Modifier.size(7.dp).clip(CircleShape).background(Success))
                Spacer(Modifier.width(5.dp))
                Text("En vivo", color = Success, fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
            }
            Box(Modifier.align(Alignment.Center).fillMaxWidth(0.8f).height(74.dp)
                .clip(RoundedCornerShape(14.dp)).background(Orange))
            Box(
                Modifier.align(Alignment.BottomStart).padding(12.dp)
                    .clip(RoundedCornerShape(8.dp)).background(CardWhite).padding(horizontal = 12.dp, vertical = 6.dp),
            ) { Text("C 2417 TUM", color = Ink, fontWeight = FontWeight.Bold, fontSize = 13.sp) }
        }
        Spacer(Modifier.height(12.dp))
        RumboCard(Modifier.fillMaxWidth(), padding = 16.dp) {
            Row {
                UnitStat("Velocidad", "29 km/h", Modifier.weight(1f))
                UnitStat("Próxima parada", "Catedral", Modifier.weight(1f))
                UnitStat("Llega en", "3 min", Modifier.weight(1f))
            }
        }
        Spacer(Modifier.height(12.dp))
        RumboCard(Modifier.fillMaxWidth(), padding = 16.dp) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Ocupación", color = Ink, fontWeight = FontWeight.Bold, fontSize = 16.sp, modifier = Modifier.weight(1f))
                Text("Medio lleno", color = Warning, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
            }
            Spacer(Modifier.height(10.dp))
            Box(Modifier.fillMaxWidth().height(8.dp).clip(CircleShape).background(CardGrey)) {
                Box(Modifier.fillMaxWidth(0.62f).height(8.dp).clip(CircleShape).background(Warning))
            }
            Spacer(Modifier.height(8.dp))
            Row {
                Text("37 de 60 pasajeros", color = Muted, fontSize = 12.sp, modifier = Modifier.weight(1f))
                Text("3 asientos libres", color = Muted, fontSize = 12.sp)
            }
        }
        Spacer(Modifier.height(18.dp))
        Text("Comodidades", color = Ink, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(Modifier.height(10.dp))
        val amenities = listOf(
            Triple(Icons.Rounded.AcUnit, "Aire acondicionado", "Disponible"),
            Triple(Icons.Rounded.Accessible, "Rampa", "Disponible"),
            Triple(Icons.Rounded.Wifi, "Wi-Fi", "Disponible"),
            Triple(Icons.Rounded.Power, "Cargador USB", "Disponible"),
            Triple(Icons.Rounded.Videocam, "Cámaras", "Disponible"),
            Triple(Icons.Rounded.LocationOn, "GPS en vivo", "Disponible"),
        )
        amenities.chunked(3).forEach { row ->
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                row.forEach { (icon, title, state) ->
                    AmenityTile(icon, title, state, Modifier.weight(1f))
                }
            }
            Spacer(Modifier.height(10.dp))
        }
        RumboCard(Modifier.fillMaxWidth(), padding = 14.dp) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Rounded.Verified, null, tint = Success, modifier = Modifier.size(22.dp))
                Spacer(Modifier.width(10.dp))
                Text("Conductor certificado", color = Ink, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
            }
        }
        Spacer(Modifier.height(10.dp))
    }
}

@Composable
private fun UnitStat(label: String, value: String, modifier: Modifier) {
    Column(modifier) {
        Text(label, color = Muted, fontSize = 11.sp)
        Text(value, color = Ink, fontWeight = FontWeight.Bold, fontSize = 15.sp)
    }
}

@Composable
private fun AmenityTile(icon: ImageVector, title: String, state: String, modifier: Modifier) {
    Column(
        modifier.clip(RoundedCornerShape(16.dp)).background(LineC4Soft).padding(14.dp),
    ) {
        Icon(icon, null, tint = Success, modifier = Modifier.size(22.dp))
        Spacer(Modifier.height(8.dp))
        Text(title, color = Ink, fontWeight = FontWeight.SemiBold, fontSize = 12.sp, lineHeight = 15.sp)
        Text(state, color = Success, fontSize = 11.sp)
    }
}
