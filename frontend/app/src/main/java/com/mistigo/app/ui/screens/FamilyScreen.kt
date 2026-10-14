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
import com.mistigo.app.data.FamilyMember
import com.mistigo.app.data.SampleData
import com.mistigo.app.data.lineColor
import com.mistigo.app.data.lineSoft
import com.mistigo.app.ui.components.*
import com.mistigo.app.ui.theme.*

@Composable
fun FamilyScreen(onBack: () -> Unit) {
    var notify by remember { mutableStateOf(true) }
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)) {
        BackHeader(onBack, "Plan Familia · 4 de 5 lugares usados", "Mi", "familia")
        Spacer(Modifier.height(14.dp))
        SampleData.family.forEach { m ->
            MemberRow(m)
            Spacer(Modifier.height(10.dp))
        }
        Spacer(Modifier.height(6.dp))
        RumboCard(Modifier.fillMaxWidth(), padding = 16.dp) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text("Avisarme cuando lleguen", color = Ink, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
                    Text("Notificación al llegar a su destino", color = Muted, fontSize = 12.sp)
                }
                RumboSwitch(notify) { notify = !notify }
            }
        }
        Spacer(Modifier.height(12.dp))
        Box(
            Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp))
                .border(1.5.dp, HairLine, RoundedCornerShape(16.dp)).padding(vertical = 16.dp),
            contentAlignment = Alignment.Center,
        ) { Text("+ Invitar a alguien · 1 lugar libre", color = Muted, fontWeight = FontWeight.SemiBold, fontSize = 14.sp) }
        Spacer(Modifier.height(10.dp))
    }
}

@Composable
private fun MemberRow(m: FamilyMember) {
    RumboCard(Modifier.fillMaxWidth(), padding = 14.dp) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(44.dp).clip(RoundedCornerShape(14.dp)).background(m.soft),
                contentAlignment = Alignment.Center) {
                Text(m.initials, color = m.color, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(m.name, color = Ink, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (!m.arrived) {
                        Box(Modifier.size(7.dp).clip(CircleShape).background(Success))
                        Spacer(Modifier.width(5.dp))
                    }
                    Text(m.status, color = if (m.arrived) Muted else InkSoft, fontSize = 12.sp)
                }
            }
            if (m.line != null) LineTag(m.line, lineColor(m.line), lineSoft(m.line))
        }
    }
}
