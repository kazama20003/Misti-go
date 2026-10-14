package com.mistigo.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.SwapVert
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
import com.mistigo.app.ui.components.*
import com.mistigo.app.ui.theme.*

@Composable
fun FavoritesScreen(onBack: () -> Unit, onGo: () -> Unit) {
    var tab by remember { mutableStateOf("Rutas") }
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)) {
        BackHeader(onBack, "4 guardados", "Mis", "favoritos")
        Spacer(Modifier.height(14.dp))
        Row(Modifier.fillMaxWidth().clip(CircleShape).background(CardWhite).padding(4.dp)) {
            listOf("Rutas", "Paradas", "Líneas").forEach { t ->
                Box(
                    Modifier.weight(1f).clip(CircleShape).background(if (t == tab) Ink else Color(0x00000000))
                        .clickable { tab = t }.padding(vertical = 11.dp),
                    contentAlignment = Alignment.Center,
                ) { Text(t, color = if (t == tab) Color(0xFFFFFFFF) else Muted, fontWeight = FontWeight.SemiBold, fontSize = 14.sp) }
            }
        }
        Spacer(Modifier.height(14.dp))
        FavRow("Casa → Trabajo", "R12 + C4 · 26 min · Lun-Vie 7:30", onGo)
        Spacer(Modifier.height(10.dp))
        FavRow("Casa → Universidad Central", "R7 · 34 min", onGo)
    }
}

@Composable
private fun FavRow(title: String, sub: String, onGo: () -> Unit) {
    RumboCard(Modifier.fillMaxWidth(), padding = 14.dp) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(42.dp).clip(RoundedCornerShape(12.dp)).background(OrangeSoft),
                contentAlignment = Alignment.Center) {
                Icon(Icons.Rounded.SwapVert, null, tint = Orange, modifier = Modifier.size(22.dp))
            }
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(title, color = Ink, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                Text(sub, color = Muted, fontSize = 12.sp)
            }
            Box(
                Modifier.clip(CircleShape).background(Ink).clickable(onClick = onGo)
                    .padding(horizontal = 20.dp, vertical = 10.dp),
            ) { Text("Ir", color = Color(0xFFFFFFFF), fontWeight = FontWeight.SemiBold, fontSize = 14.sp) }
        }
    }
}
