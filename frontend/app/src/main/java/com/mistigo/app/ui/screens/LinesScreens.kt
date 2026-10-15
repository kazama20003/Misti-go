package com.mistigo.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mistigo.app.data.BusLine
import com.mistigo.app.data.SampleData
import com.mistigo.app.data.StatusKind
import com.mistigo.app.ui.components.*
import com.mistigo.app.ui.theme.*

@Composable
fun LinesScreen(onOpenLine: () -> Unit) {
    Column(
        Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
    ) {
        Spacer(Modifier.height(6.dp))
        Text("34 buses en servicio", color = Muted, fontSize = 13.sp)
        TwoToneTitle("Líneas de", "Arequipa")
        Spacer(Modifier.height(14.dp))
        Row(
            Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(CardWhite)
                .padding(horizontal = 16.dp, vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(Icons.Rounded.Search, null, tint = Muted, modifier = Modifier.size(20.dp))
            Spacer(Modifier.width(10.dp))
            Text("Buscar línea…", color = Muted, fontSize = 15.sp)
        }
        Spacer(Modifier.height(12.dp))
        SampleData.lines.forEach { line ->
            LineCard(line, onClick = onOpenLine)
            Spacer(Modifier.height(12.dp))
        }
    }
}

@Composable
private fun LineCard(line: BusLine, onClick: () -> Unit) {
    val statusColor = when (line.statusKind) {
        StatusKind.NORMAL -> Muted
        StatusKind.WARNING -> Orange
        StatusKind.DELAY -> Danger
    }
    RumboCard(Modifier.fillMaxWidth().clickable(onClick = onClick), padding = 16.dp) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            LineBadge(line.code, line.color, line.soft)
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(line.name, color = Ink, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Row {
                    Text(line.frequency, color = Muted, fontSize = 12.sp)
                    Text(" · ", color = Muted, fontSize = 12.sp)
                    Text(line.status, color = statusColor, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                }
            }
            Icon(
                if (line.favorite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                null, tint = Orange, modifier = Modifier.size(22.dp),
            )
        }
        Spacer(Modifier.height(12.dp))
        ProgressTrack(line.color, line.progress)
        Spacer(Modifier.height(6.dp))
        Row {
            Text(line.from, color = Muted, fontSize = 11.sp)
            Spacer(Modifier.weight(1f))
            Text(line.to, color = Muted, fontSize = 11.sp)
        }
    }
}

@Composable
private fun ProgressTrack(color: Color, progress: Float) {
    Box(Modifier.fillMaxWidth().height(14.dp), contentAlignment = Alignment.CenterStart) {
        Box(Modifier.fillMaxWidth().height(3.dp).clip(CircleShape).background(HairLine))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            repeat(5) {
                Box(Modifier.size(7.dp).clip(CircleShape).background(HairLine))
            }
        }
        BoxWithConstraints(Modifier.fillMaxWidth()) {
            val x = maxWidth * progress.coerceIn(0f, 1f)
            Box(
                Modifier.padding(start = x - 7.dp).size(14.dp)
                    .clip(CircleShape).background(color),
            )
        }
    }
}

// ----- Línea en vivo -----

private data class StopRow(val name: String, val eta: String, val note: String?, val noteColor: Color, val state: StopState)
private enum class StopState { PASSED, HERE, AHEAD }

@Composable
fun LineLiveScreen(onBack: () -> Unit, onSchedules: () -> Unit, onCompany: () -> Unit) {
    val stops = listOf(
        StopRow("Yanahuara", "Ya pasó", null, Muted, StopState.PASSED),
        StopRow("Catedral", "Bus aquí", null, Muted, StopState.HERE),
        StopRow("Hospital Goyeneche", "3 min", "Tráfico moderado", Warning, StopState.AHEAD),
        StopRow("UNSA", "8 min", null, Muted, StopState.AHEAD),
        StopRow("Estadio Melgar", "11 min", null, Muted, StopState.AHEAD),
        StopRow("Paucarpata", "14 min", "Tráfico moderado", Warning, StopState.AHEAD),
        StopRow("Cerro Colorado", "19 min", "Tráfico denso", Danger, StopState.AHEAD),
        StopRow("Aeropuerto R. Ballón", "26 min", null, Muted, StopState.AHEAD),
    )
    Column(
        Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            CircleIconButton(onClick = onBack) {
                Icon(Icons.Rounded.ArrowBackIosNew, null, tint = Ink, modifier = Modifier.size(18.dp))
            }
            Spacer(Modifier.weight(1f))
            Row(
                Modifier.clip(CircleShape).background(CardWhite).padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(Icons.Rounded.FavoriteBorder, null, tint = Orange, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
                Text("Seguir línea", color = Ink, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
            }
        }
        Spacer(Modifier.height(14.dp))
        RumboCard(Modifier.fillMaxWidth(), padding = 16.dp) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                LineBadge("C4", LineC4, LineC4Soft, size = 52.dp)
                Spacer(Modifier.width(14.dp))
                Column {
                    Text("Catedral – Hospital", color = Ink, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Text("Cada 10 min · Desvío por obras", color = Orange, fontSize = 12.sp)
                }
            }
            Spacer(Modifier.height(14.dp))
            Box(Modifier.fillMaxWidth().height(1.dp).background(HairLine))
            Spacer(Modifier.height(14.dp))
            Row {
                MiniField("Primer bus", "5:00", Modifier.weight(1f))
                MiniField("Último bus", "22:30", Modifier.weight(1f))
                MiniField("Pasaje", "$2.50", Modifier.weight(1f))
            }
        }
        Spacer(Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            ActionChip(Icons.Rounded.Schedule, "Horarios", "Por parada", Orange, OrangeSoft,
                Modifier.weight(1f), onSchedules)
            ActionChip(null, "Empresa", "Ver operadora", LineC4, LineC4Soft,
                Modifier.weight(1f), onCompany, badge = "SOL")
        }
        Spacer(Modifier.height(18.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Recorrido en vivo", color = Ink, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(Modifier.weight(1f))
            StatusPill("🚌 Tráfico denso adelante", Orange, OrangeSoft)
        }
        Spacer(Modifier.height(12.dp))
        RumboCard(Modifier.fillMaxWidth(), padding = 16.dp) {
            stops.forEachIndexed { i, s -> LiveStopRow(s, i == stops.lastIndex) }
        }
        Spacer(Modifier.height(10.dp))
    }
}

@Composable
private fun MiniField(label: String, value: String, modifier: Modifier) {
    Column(modifier) {
        Text(label, color = Muted, fontSize = 11.sp)
        Text(value, color = Ink, fontWeight = FontWeight.Bold, fontSize = 16.sp)
    }
}

@Composable
private fun ActionChip(
    icon: androidx.compose.ui.graphics.vector.ImageVector?,
    title: String, sub: String, tint: Color, soft: Color,
    modifier: Modifier, onClick: () -> Unit, badge: String? = null,
) {
    Row(
        modifier.clip(RoundedCornerShape(16.dp)).background(CardWhite).clickable(onClick = onClick).padding(14.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(Modifier.size(38.dp).clip(RoundedCornerShape(11.dp)).background(soft), contentAlignment = Alignment.Center) {
            if (icon != null) Icon(icon, null, tint = tint, modifier = Modifier.size(18.dp))
            else Text(badge ?: "", color = tint, fontWeight = FontWeight.Bold, fontSize = 12.sp)
        }
        Spacer(Modifier.width(10.dp))
        Column {
            Text(title, color = Ink, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
            Text(sub, color = Muted, fontSize = 11.sp)
        }
    }
}

@Composable
private fun LiveStopRow(s: StopRow, last: Boolean) {
    Row(Modifier.fillMaxWidth().height(IntrinsicSize.Min)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            when (s.state) {
                StopState.HERE -> Box(Modifier.size(22.dp).clip(CircleShape).background(LineC4),
                    contentAlignment = Alignment.Center) {
                    Icon(Icons.Rounded.DirectionsBus, null, tint = Color(0xFFFFFFFF), modifier = Modifier.size(13.dp))
                }
                StopState.PASSED -> Box(Modifier.size(14.dp).clip(CircleShape).background(LineC4))
                StopState.AHEAD -> Box(Modifier.size(14.dp).clip(CircleShape).background(CardWhite)
                    .border(2.dp, HairLine, CircleShape))
            }
            if (!last) Box(Modifier.width(2.dp).weight(1f).background(HairLine))
        }
        Spacer(Modifier.width(14.dp))
        Row(Modifier.weight(1f).padding(bottom = if (last) 0.dp else 16.dp)) {
            Text(s.name, color = if (s.state == StopState.PASSED) Muted else Ink,
                fontWeight = if (s.state == StopState.HERE) FontWeight.Bold else FontWeight.SemiBold, fontSize = 15.sp,
                modifier = Modifier.weight(1f))
            Column(horizontalAlignment = Alignment.End) {
                Text(s.eta, color = if (s.state == StopState.PASSED) Muted else Ink, fontSize = 13.sp)
                if (s.note != null) Text(s.note, color = s.noteColor, fontSize = 11.sp)
            }
        }
    }
}
