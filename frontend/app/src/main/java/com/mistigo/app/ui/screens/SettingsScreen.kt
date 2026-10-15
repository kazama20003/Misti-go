package com.mistigo.app.ui.screens

import androidx.compose.animation.core.animateDpAsState
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
import com.mistigo.app.ui.components.*
import com.mistigo.app.ui.theme.*

@Composable
fun SettingsScreen(onOpenFamily: () -> Unit, onOpenPlan: () -> Unit) {
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)) {
        Spacer(Modifier.height(6.dp))
        RumboCard(Modifier.fillMaxWidth(), padding = 16.dp) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(56.dp).clip(RoundedCornerShape(16.dp)).background(OrangeSoft),
                    contentAlignment = Alignment.Center) {
                    Text("TN", color = Orange, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                }
                Spacer(Modifier.width(14.dp))
                Column {
                    Text("Plan Gratis · Viajero frecuente", color = Muted, fontSize = 12.sp)
                    Text("[Tu nombre]", color = Ink, fontWeight = FontWeight.Bold, fontSize = 22.sp)
                }
            }
            Spacer(Modifier.height(16.dp))
            Box(Modifier.fillMaxWidth().height(1.dp).background(HairLine))
            Spacer(Modifier.height(16.dp))
            Row {
                StatCol("Viajes del mes", "24", Modifier.weight(1f))
                StatCol("Recorrido", "86 km", Modifier.weight(1f))
                StatCol("Paradas guardadas", "1", Modifier.weight(1f))
            }
        }

        Spacer(Modifier.height(16.dp))
        Text("Lugares guardados", color = Ink, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(Modifier.height(10.dp))
        RumboCard(Modifier.fillMaxWidth(), padding = 8.dp) {
            PlaceRow(Icons.Rounded.Home, "Casa", "Cerro Colorado, Calle 8")
            Box(Modifier.padding(horizontal = 8.dp).fillMaxWidth().height(1.dp).background(HairLine))
            PlaceRow(Icons.Rounded.Work, "Trabajo", "Torre Forum, Av. Dolores")
        }

        Spacer(Modifier.height(16.dp))
        Text("Preferencias", color = Ink, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(Modifier.height(10.dp))
        RumboCard(Modifier.fillMaxWidth(), padding = 8.dp) {
            ToggleRow("Aviso antes de bajar", "Vibra una parada antes de tu destino", true)
            ToggleRow("Alertas del servicio", "Desvíos, retrasos y cierres", true)
            ToggleRow("Solo rutas accesibles", "Buses con piso bajo y rampa", false)
            ToggleRow("Ahorro de datos", "Actualiza el mapa cada 30 s", false)
        }

        Spacer(Modifier.height(16.dp))
        RumboCard(Modifier.fillMaxWidth().clickable(onClick = onOpenFamily), padding = 16.dp) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(40.dp).clip(RoundedCornerShape(12.dp)).background(LineR7Soft),
                    contentAlignment = Alignment.Center) {
                    Icon(Icons.Rounded.Groups, null, tint = LineR7, modifier = Modifier.size(22.dp))
                }
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text("Mi familia", color = Ink, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                    Text("Comparte tu viaje con quien quieras", color = Muted, fontSize = 12.sp)
                }
                Icon(Icons.Rounded.ChevronRight, null, tint = Muted)
            }
        }
        Spacer(Modifier.height(12.dp))
        RumboCard(Modifier.fillMaxWidth().clickable(onClick = onOpenPlan), padding = 16.dp) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(40.dp).clip(RoundedCornerShape(12.dp)).background(OrangeSoft),
                    contentAlignment = Alignment.Center) {
                    Icon(Icons.Rounded.WorkspacePremium, null, tint = Orange, modifier = Modifier.size(22.dp))
                }
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text("Mi plan", color = Ink, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                    Text("Gestiona tu suscripción", color = Muted, fontSize = 12.sp)
                }
                Icon(Icons.Rounded.ChevronRight, null, tint = Muted)
            }
        }
        Spacer(Modifier.height(10.dp))
    }
}

@Composable
private fun StatCol(label: String, value: String, modifier: Modifier) {
    Column(modifier) {
        Text(label, color = Muted, fontSize = 11.sp)
        Text(value, color = Ink, fontWeight = FontWeight.Bold, fontSize = 20.sp)
    }
}

@Composable
private fun PlaceRow(icon: ImageVector, title: String, sub: String) {
    Row(Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 12.dp), verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier.size(40.dp).clip(RoundedCornerShape(12.dp)).background(CardGrey), contentAlignment = Alignment.Center) {
            Icon(icon, null, tint = Ink, modifier = Modifier.size(20.dp))
        }
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(title, color = Ink, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
            Text(sub, color = Muted, fontSize = 12.sp)
        }
        Icon(Icons.Rounded.ChevronRight, null, tint = Muted)
    }
}

@Composable
fun ToggleRow(title: String, sub: String, initial: Boolean) {
    var on by remember { mutableStateOf(initial) }
    Row(Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 12.dp), verticalAlignment = Alignment.CenterVertically) {
        Column(Modifier.weight(1f)) {
            Text(title, color = Ink, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
            Text(sub, color = Muted, fontSize = 12.sp)
        }
        RumboSwitch(on) { on = !on }
    }
}

@Composable
fun RumboSwitch(on: Boolean, onToggle: () -> Unit) {
    val offset by animateDpAsState(if (on) 22.dp else 2.dp, label = "sw")
    Box(
        Modifier.size(width = 48.dp, height = 28.dp).clip(CircleShape)
            .background(if (on) Orange else Color(0xFFCFCEC7)).clickable(onClick = onToggle),
        contentAlignment = Alignment.CenterStart,
    ) {
        Box(Modifier.padding(start = offset).size(24.dp).clip(CircleShape).background(Color(0xFFFFFFFF)))
    }
}
