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
import androidx.compose.material.icons.rounded.Shield
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SecurityScreen(onBack: () -> Unit) {
    var share by remember { mutableStateOf(false) }
    var anon by remember { mutableStateOf(true) }
    var selected by remember { mutableStateOf(setOf<String>()) }
    val incidents = listOf("Bus lleno", "Conducción peligrosa", "No se detuvo", "Retraso", "Acoso", "Objeto perdido", "Otro")

    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)) {
        BackHeader(onBack, "Centro de seguridad", "Viaja", "seguro")
        Spacer(Modifier.height(14.dp))
        // Tarjeta emergencia
        Column(Modifier.fillMaxWidth().clip(RoundedCornerShape(20.dp)).background(Ink).padding(18.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(44.dp).clip(RoundedCornerShape(14.dp)).background(Danger),
                    contentAlignment = Alignment.Center) {
                    Icon(Icons.Rounded.Shield, null, tint = Color(0xFFFFFFFF), modifier = Modifier.size(24.dp))
                }
                Spacer(Modifier.width(12.dp))
                Column {
                    Text("¿Estás en peligro?", color = Color(0xFFFFFFFF), fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text("Llamamos a emergencias y enviamos tu ubicación, la unidad y la línea en la que viajas.",
                        color = Color(0xB3FFFFFF), fontSize = 12.sp, lineHeight = 16.sp)
                }
            }
            Spacer(Modifier.height(14.dp))
            Box(
                Modifier.fillMaxWidth().clip(RoundedCornerShape(14.dp)).background(Danger).padding(vertical = 15.dp),
                contentAlignment = Alignment.Center,
            ) { Text("Llamar a emergencias", color = Color(0xFFFFFFFF), fontWeight = FontWeight.Bold, fontSize = 16.sp) }
        }

        Spacer(Modifier.height(12.dp))
        RumboCard(Modifier.fillMaxWidth(), padding = 16.dp) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text("Compartir viaje en vivo", color = Ink, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
                    Text("Tus contactos ven dónde vas hasta que llegues", color = Muted, fontSize = 12.sp)
                }
                RumboSwitch(share) { share = !share }
            }
        }

        Spacer(Modifier.height(16.dp))
        Text("Reportar un incidente", color = Ink, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(Modifier.height(10.dp))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            incidents.forEach { s ->
                val sel = selected.contains(s)
                Box(
                    Modifier.clip(CircleShape)
                        .background(if (sel) Ink else CardGrey)
                        .then(if (!sel) Modifier.border(1.dp, HairLine, CircleShape) else Modifier)
                        .clickable { selected = if (sel) selected - s else selected + s }
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                ) {
                    Text(s, color = if (sel) Color(0xFFFFFFFF) else InkSoft, fontWeight = FontWeight.Medium, fontSize = 13.sp)
                }
            }
        }
        Spacer(Modifier.height(14.dp))
        Text("Cuéntanos qué pasó (opcional)", color = Muted, fontSize = 13.sp)
        Spacer(Modifier.height(6.dp))
        Box(
            Modifier.fillMaxWidth().height(90.dp).clip(RoundedCornerShape(14.dp)).background(CardGrey).padding(14.dp),
        ) { Text("Ej. el bus no se detuvo en la parada", color = MutedLight, fontSize = 13.sp) }

        Spacer(Modifier.height(14.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Enviar de forma anónima", color = Ink, fontWeight = FontWeight.SemiBold, fontSize = 14.sp, modifier = Modifier.weight(1f))
            RumboSwitch(anon) { anon = !anon }
        }
        Spacer(Modifier.height(8.dp))
        Text("Se adjunta: Unidad 2417 · Línea R12 · TUM · tu ubicación", color = Orange, fontSize = 12.sp)
        Spacer(Modifier.height(12.dp))
        Box(
            Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp))
                .background(if (selected.isEmpty()) Color(0xFFAFAEA8) else Orange).padding(vertical = 16.dp),
            contentAlignment = Alignment.Center,
        ) { Text("Enviar reporte", color = Color(0xFFFFFFFF), fontWeight = FontWeight.Bold, fontSize = 16.sp) }
        Spacer(Modifier.height(10.dp))
    }
}
