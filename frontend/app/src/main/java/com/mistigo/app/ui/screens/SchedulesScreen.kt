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
import androidx.compose.material.icons.rounded.NotificationsNone
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mistigo.app.data.lineColor
import com.mistigo.app.data.lineSoft
import com.mistigo.app.ui.components.*
import com.mistigo.app.ui.theme.*

@Composable
fun SchedulesScreen(onBack: () -> Unit) {
    var line by remember { mutableStateOf("C4") }
    var day by remember { mutableStateOf("Lun – Vie") }
    var stop by remember { mutableStateOf("Hospital General") }
    val lines = listOf("R12", "C4", "R7", "L3", "X1", "R20")
    val stops = listOf("Parque Colón", "Catedral", "Hospital General", "Universidad")
    val mins = listOf(":06", ":16", ":26", ":36", ":46", ":56")

    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)) {
        BackHeader(onBack, "Horarios oficiales · CVN", "Horario de la", line)
        Spacer(Modifier.height(14.dp))
        // Selector de línea
        Row(Modifier.horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            lines.forEach { l ->
                val sel = l == line
                Box(
                    Modifier.size(44.dp).clip(RoundedCornerShape(14.dp))
                        .background(if (sel) lineSoft(l) else CardWhite)
                        .then(if (sel) Modifier.border(2.dp, lineColor(l), RoundedCornerShape(14.dp)) else Modifier)
                        .clickable { line = l },
                    contentAlignment = Alignment.Center,
                ) { Text(l, color = lineColor(l), fontWeight = FontWeight.Bold, fontSize = 14.sp) }
            }
        }
        Spacer(Modifier.height(14.dp))
        RumboCard(Modifier.fillMaxWidth(), padding = 16.dp) {
            Row(Modifier.fillMaxWidth().clip(CircleShape).background(CardGrey).padding(3.dp)) {
                listOf("Lun – Vie", "Sábado", "Domingo").forEach { d ->
                    DayTab(d, d == day, Modifier.weight(1f)) { day = d }
                }
            }
            Spacer(Modifier.height(14.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                DirBox("Ida", "Hacia Aeropuerto", Modifier.weight(1f))
                DirBox("Vuelta", "Hacia Parque Colón", Modifier.weight(1f))
            }
            Spacer(Modifier.height(12.dp))
            Text("Parada", color = Muted, fontSize = 12.sp)
            Spacer(Modifier.height(6.dp))
            Row(Modifier.horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                stops.forEach { s -> FilterChip(s, s == stop, { stop = s }) }
            }
        }
        Spacer(Modifier.height(12.dp))
        RumboCard(Modifier.fillMaxWidth(), padding = 16.dp) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text("Próxima salida", color = Muted, fontSize = 12.sp)
                    Text("10:16", color = Ink, fontWeight = FontWeight.Bold, fontSize = 28.sp)
                    Text("En 2 min desde $stop", color = Muted, fontSize = 12.sp)
                }
                Row(
                    Modifier.clip(CircleShape).border(1.dp, HairLine, CircleShape).padding(horizontal = 14.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(Icons.Rounded.NotificationsNone, null, tint = Ink, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(6.dp))
                    Text("Recordarme", color = Ink, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                }
            }
        }
        Spacer(Modifier.height(12.dp))
        RumboCard(Modifier.fillMaxWidth(), padding = 16.dp) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text("Todas las salidas", color = Ink, fontWeight = FontWeight.Bold, fontSize = 17.sp)
                    Text("Cada 10 min · 5:00 a 22:30", color = Muted, fontSize = 12.sp)
                }
                StatusPill("⬇ Sin conexión · Plus", Orange, OrangeSoft)
            }
            Spacer(Modifier.height(12.dp))
            (5..10).forEach { hour ->
                Row(Modifier.fillMaxWidth().padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text("%02d".format(hour), color = Ink, fontWeight = FontWeight.SemiBold, fontSize = 15.sp,
                        modifier = Modifier.width(34.dp))
                    mins.forEach { m ->
                        val next = hour == 10 && m == ":16"
                        val past = hour < 10 || (hour == 10 && m == ":06")
                        Box(
                            Modifier.weight(1f).padding(horizontal = 2.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (next) Orange else Color(0x00000000))
                                .padding(vertical = 4.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                m,
                                color = when { next -> Color(0xFFFFFFFF); past -> MutedLight; else -> InkSoft },
                                fontSize = 13.sp,
                                textDecoration = if (past) TextDecoration.LineThrough else null,
                            )
                        }
                    }
                }
            }
        }
        Spacer(Modifier.height(10.dp))
    }
}

@Composable
private fun DayTab(text: String, selected: Boolean, modifier: Modifier, onClick: () -> Unit) {
    Box(
        modifier.clip(CircleShape).background(if (selected) Ink else Color(0x00000000))
            .clickable(onClick = onClick).padding(vertical = 10.dp),
        contentAlignment = Alignment.Center,
    ) { Text(text, color = if (selected) Color(0xFFFFFFFF) else Muted, fontWeight = FontWeight.SemiBold, fontSize = 13.sp) }
}

@Composable
private fun DirBox(label: String, value: String, modifier: Modifier) {
    Column(modifier.clip(RoundedCornerShape(12.dp)).border(1.dp, HairLine, RoundedCornerShape(12.dp)).padding(12.dp)) {
        Text(label, color = Muted, fontSize = 11.sp)
        Text(value, color = Ink, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
    }
}
