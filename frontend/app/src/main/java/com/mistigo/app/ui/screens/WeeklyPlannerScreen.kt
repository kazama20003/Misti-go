package com.mistigo.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
fun WeeklyPlannerScreen(onBack: () -> Unit) {
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)) {
        BackHeader(onBack, "Te avisamos cuándo salir", "Planificador", "semanal", trailing = {
            Box(Modifier.clip(CircleShape).background(Ink).padding(horizontal = 12.dp, vertical = 6.dp)) {
                Text("PRO", color = Color(0xFFFFFFFF), fontWeight = FontWeight.Bold, fontSize = 12.sp)
            }
        })
        Spacer(Modifier.height(14.dp))
        RumboCard(Modifier.fillMaxWidth(), padding = 16.dp) {
            Row {
                Text("Mañana · jueves", color = Ink, fontWeight = FontWeight.Bold, fontSize = 16.sp, modifier = Modifier.weight(1f))
                Text("Llegar 8:00 al Trabajo", color = Muted, fontSize = 13.sp)
            }
            Spacer(Modifier.height(14.dp))
            PlanStep(Orange, false, "7:14", "Te avisamos: prepárate", "Tráfico moderado en Av. Central", true)
            PlanStep(Ink, true, "7:24", "Sal de casa", "Camina 3 min a Plaza Central", true)
            PlanStep(Orange, false, "7:31", "Pasa tu R12", "Transbordo al C4 en Catedral", true)
            PlanStep(Success, true, "7:56", "Llegas al Trabajo", "4 min antes de tu hora", false)
        }
        Spacer(Modifier.height(18.dp))
        Text("Tus viajes programados", color = Ink, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(Modifier.height(10.dp))
        ScheduledTrip(
            "Llegar al Trabajo a las 8:00", "Desde Casa · R12 + C4", true,
            listOf("L" to true, "M" to true, "M" to true, "J" to true, "V" to true, "S" to false, "D" to false),
            "Mañana sale a las 7:24 (5 min antes por tráfico)",
        )
        Spacer(Modifier.height(12.dp))
        ScheduledTrip(
            "Llegar a la Universidad a las 14:00", "Desde Trabajo · R7", true,
            listOf("L" to false, "M" to true, "M" to false, "J" to true, "V" to false, "S" to false, "D" to false),
            "El jueves sale a las 13:18",
        )
        Spacer(Modifier.height(12.dp))
        Box(
            Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp))
                .border(1.5.dp, HairLine, RoundedCornerShape(16.dp)).padding(vertical = 16.dp),
            contentAlignment = Alignment.Center,
        ) { Text("+ Programar otro viaje", color = Muted, fontWeight = FontWeight.SemiBold, fontSize = 14.sp) }
        Spacer(Modifier.height(10.dp))
    }
}

@Composable
private fun PlanStep(color: Color, filled: Boolean, time: String, title: String, sub: String, line: Boolean) {
    Row(Modifier.fillMaxWidth().height(IntrinsicSize.Min)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            if (filled) Box(Modifier.size(14.dp).clip(CircleShape).background(color))
            else Box(Modifier.size(14.dp).clip(CircleShape).background(CardWhite).border(2.5.dp, color, CircleShape))
            if (line) Box(Modifier.width(2.dp).weight(1f).background(HairLine))
        }
        Spacer(Modifier.width(12.dp))
        Row(Modifier.weight(1f).padding(bottom = if (line) 16.dp else 0.dp)) {
            Text(time, color = Ink, fontWeight = FontWeight.SemiBold, fontSize = 14.sp, modifier = Modifier.width(42.dp))
            Spacer(Modifier.width(8.dp))
            Column {
                Text(title, color = Ink, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
                Text(sub, color = Muted, fontSize = 12.sp)
            }
        }
    }
}

@Composable
private fun ScheduledTrip(title: String, sub: String, on: Boolean, days: List<Pair<String, Boolean>>, note: String) {
    var enabled by remember { mutableStateOf(on) }
    RumboCard(Modifier.fillMaxWidth(), padding = 16.dp) {
        Row(verticalAlignment = Alignment.Top) {
            Column(Modifier.weight(1f)) {
                Text(title, color = Ink, fontWeight = FontWeight.Bold, fontSize = 16.sp, lineHeight = 20.sp)
                Text(sub, color = Muted, fontSize = 12.sp)
            }
            RumboSwitch(enabled) { enabled = !enabled }
        }
        Spacer(Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            days.forEach { (d, active) ->
                Box(
                    Modifier.size(32.dp).clip(CircleShape).background(if (active) Ink else CardGrey),
                    contentAlignment = Alignment.Center,
                ) { Text(d, color = if (active) Color(0xFFFFFFFF) else Muted, fontWeight = FontWeight.SemiBold, fontSize = 13.sp) }
            }
        }
        Spacer(Modifier.height(10.dp))
        Text("⏰ $note", color = Muted, fontSize = 12.sp)
    }
}
