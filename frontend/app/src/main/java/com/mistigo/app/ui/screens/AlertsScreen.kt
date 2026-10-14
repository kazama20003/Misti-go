package com.mistigo.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mistigo.app.data.Alert
import com.mistigo.app.data.AlertKind
import com.mistigo.app.data.SampleData
import com.mistigo.app.ui.components.*
import com.mistigo.app.ui.theme.*

@Composable
fun AlertsScreen(onBack: () -> Unit) {
    var tab by remember { mutableStateOf("Todas") }
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)) {
        BackHeader(onBack, "5 sin leer", "Alertas del", "servicio", trailing = {
            Text("Marcar leídas", color = Orange, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
        })
        Spacer(Modifier.height(14.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterChip("Todas", tab == "Todas", { tab = "Todas" })
            Row(
                Modifier.clip(CircleShape).background(if (tab == "Mis líneas") Ink else CardWhite)
                    .clickable { tab = "Mis líneas" }.padding(horizontal = 18.dp, vertical = 9.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text("Mis líneas", color = if (tab == "Mis líneas") Color(0xFFFFFFFF) else InkSoft,
                    fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                Spacer(Modifier.width(8.dp))
                StatusPill("Plus", Orange, OrangeSoft)
            }
        }
        Spacer(Modifier.height(14.dp))
        SampleData.alerts.forEach { a ->
            AlertCard(a)
            Spacer(Modifier.height(12.dp))
        }
    }
}

@Composable
private fun AlertCard(a: Alert) {
    val icon: ImageVector = when (a.kind) {
        AlertKind.TRAFFIC -> Icons.Rounded.DirectionsBus
        AlertKind.DETOUR -> Icons.Rounded.WarningAmber
        AlertKind.DELAY -> Icons.Rounded.Schedule
        AlertKind.INFO -> Icons.Rounded.Info
        AlertKind.STOP -> Icons.Rounded.Flag
    }
    RumboCard(Modifier.fillMaxWidth(), padding = 14.dp) {
        Row(verticalAlignment = Alignment.Top) {
            Box(Modifier.size(40.dp).clip(RoundedCornerShape(12.dp)).background(a.lineSoft),
                contentAlignment = Alignment.Center) {
                Icon(icon, null, tint = a.lineColor, modifier = Modifier.size(20.dp))
            }
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    LineTag(a.line, a.lineColor, a.lineSoft)
                    Spacer(Modifier.width(8.dp))
                    Text(a.time, color = Muted, fontSize = 11.sp)
                }
                Spacer(Modifier.height(6.dp))
                Text(a.title, color = Ink, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                Text(a.body, color = Muted, fontSize = 13.sp, lineHeight = 18.sp)
            }
            Box(Modifier.padding(top = 4.dp).size(9.dp).clip(CircleShape).background(Orange))
        }
    }
}
