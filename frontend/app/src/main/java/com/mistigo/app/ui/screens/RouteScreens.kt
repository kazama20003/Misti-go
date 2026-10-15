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
import com.mistigo.app.ui.components.*
import com.mistigo.app.ui.theme.*

@Composable
fun RouteOptionsScreen(onBack: () -> Unit, onStart: () -> Unit) {
    Column(Modifier.fillMaxSize()) {
        // Zona mapa con cabecera
        Box(Modifier.fillMaxWidth().weight(1f)) {
            FauxMap(Modifier.fillMaxSize())
            Column(Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    CircleIconButton(onClick = onBack) {
                        Icon(Icons.Rounded.ArrowBackIosNew, null, tint = Ink, modifier = Modifier.size(18.dp))
                    }
                    Spacer(Modifier.width(10.dp))
                    Row(
                        Modifier.weight(1f).clip(RoundedCornerShape(16.dp)).background(CardWhite)
                            .padding(horizontal = 16.dp, vertical = 14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(Icons.Rounded.Search, null, tint = Muted, modifier = Modifier.size(20.dp))
                        Spacer(Modifier.width(10.dp))
                        Text("Hospital Goyeneche", color = Ink, fontSize = 15.sp)
                    }
                    Spacer(Modifier.width(10.dp))
                    CircleIconButton(onClick = {}) {
                        Icon(Icons.Rounded.Accessible, null, tint = Ink, modifier = Modifier.size(20.dp))
                    }
                }
                Spacer(Modifier.height(12.dp))
                Row(
                    Modifier.clip(CircleShape).background(CardWhite).padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text("Tráfico en tu ruta", color = Ink, fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
                    Spacer(Modifier.width(10.dp))
                    TrafficDot("Fluido", Success)
                    Spacer(Modifier.width(8.dp))
                    TrafficDot("Moderado", Warning)
                    Spacer(Modifier.width(8.dp))
                    TrafficDot("Denso", Danger)
                }
            }
        }

        // Panel inferior
        RumboCard(Modifier.fillMaxWidth(), corner = 26, padding = 18.dp) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Ruta más rápida", color = Ink, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Spacer(Modifier.weight(1f))
                Icon(Icons.Rounded.FavoriteBorder, null, tint = Orange, modifier = Modifier.size(24.dp))
            }
            Row {
                Text("Mi ubicación → Hospital Goyeneche · ", color = Muted, fontSize = 13.sp)
                Text("Salir ahora", color = Orange, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
            }
            Spacer(Modifier.height(12.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                RouteThumb("26 min", selected = true, locked = false, Modifier.weight(1f))
                RouteThumb("32 min · Plus", selected = false, locked = true, Modifier.weight(1f))
                RouteThumb("30 min · Plus", selected = false, locked = true, Modifier.weight(1f))
            }
            Spacer(Modifier.height(14.dp))
            Row {
                MiniStat("Buses", "R12 + C4", Modifier.weight(1f))
                MiniStat("A pie", "6 min", Modifier.weight(1f))
                MiniStat("Sale en", "3 min", Modifier.weight(1f))
            }
            Spacer(Modifier.height(12.dp))
            NoteRow(Icons.Rounded.DirectionsBus, "Tráfico denso en Av. Dolores (tramo del C4)",
                "+4 min", DangerSoft, Danger)
            Spacer(Modifier.height(8.dp))
            NoteRow(Icons.Rounded.SwapVert, "Evita el tráfico · R7 · 32 min · tráfico fluido",
                "Plus", LineC4Soft, Success)
            Spacer(Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text("$5.00", color = Ink, fontWeight = FontWeight.Bold, fontSize = 26.sp)
                    Text("Llegas a las 10:40", color = Muted, fontSize = 12.sp)
                }
                OrangeButton("Iniciar viaje", onStart)
            }
        }
    }
}

@Composable
private fun TrafficDot(text: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier.size(8.dp).clip(CircleShape).background(color))
        Spacer(Modifier.width(4.dp))
        Text(text, color = Muted, fontSize = 11.sp)
    }
}

@Composable
private fun RouteThumb(label: String, selected: Boolean, locked: Boolean, modifier: Modifier) {
    Column(
        modifier.clip(RoundedCornerShape(14.dp))
            .background(CardGrey)
            .then(if (selected) Modifier.border(2.dp, Orange, RoundedCornerShape(14.dp)) else Modifier)
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(Modifier.fillMaxWidth().height(44.dp).clip(RoundedCornerShape(8.dp)).background(Color(0xFFCDD4DC)),
            contentAlignment = Alignment.Center) {
            if (locked) Icon(Icons.Rounded.Lock, null, tint = Muted, modifier = Modifier.size(18.dp))
        }
        Spacer(Modifier.height(8.dp))
        if (selected) {
            Box(Modifier.clip(CircleShape).background(Ink).padding(horizontal = 10.dp, vertical = 4.dp)) {
                Text(label, color = Color(0xFFFFFFFF), fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
            }
        } else {
            Text(label, color = Muted, fontSize = 11.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
private fun MiniStat(label: String, value: String, modifier: Modifier) {
    Column(modifier) {
        Text(label, color = Muted, fontSize = 12.sp)
        Text(value, color = Ink, fontWeight = FontWeight.Bold, fontSize = 15.sp)
    }
}

@Composable
private fun NoteRow(icon: androidx.compose.ui.graphics.vector.ImageVector, text: String, tag: String, bg: Color, accent: Color) {
    Row(
        Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(bg).padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(icon, null, tint = accent, modifier = Modifier.size(18.dp))
        Spacer(Modifier.width(10.dp))
        Text(text, color = InkSoft, fontSize = 12.sp, modifier = Modifier.weight(1f))
        Spacer(Modifier.width(8.dp))
        Text(tag, color = accent, fontWeight = FontWeight.Bold, fontSize = 12.sp)
    }
}

// ----- Viaje en vivo -----

@Composable
fun LiveTripScreen(onBack: () -> Unit, onUnit: () -> Unit) {
    Column(Modifier.fillMaxSize()) {
        Box(Modifier.fillMaxWidth().height(260.dp)) {
            FauxMap(Modifier.fillMaxSize())
            // Tarjeta superior
            Row(
                Modifier.padding(16.dp).fillMaxWidth().clip(RoundedCornerShape(18.dp))
                    .background(CardWhite).padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                LineBadge("R12", LineR12, LineR12Soft, size = 40.dp)
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text("Bájate en Catedral", color = Ink, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text("Faltan 2 paradas", color = Muted, fontSize = 12.sp)
                }
                CircleIconButton(onClick = onBack, size = 36.dp, bg = CardGrey) {
                    Icon(Icons.Rounded.Close, null, tint = Ink, modifier = Modifier.size(18.dp))
                }
            }
            Box(
                Modifier.align(Alignment.Center).padding(top = 40.dp)
                    .clip(RoundedCornerShape(10.dp)).background(Ink).padding(horizontal = 12.dp, vertical = 6.dp),
            ) { Text("Hospital Goyeneche", color = Color(0xFFFFFFFF), fontSize = 12.sp, fontWeight = FontWeight.SemiBold) }
        }

        Column(
            Modifier.fillMaxSize().clip(RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp))
                .background(CardWhite).verticalScroll(rememberScrollState()).padding(18.dp),
        ) {
            Text("Llegas a Hospital Goyeneche", color = Muted, fontSize = 13.sp)
            Row(verticalAlignment = Alignment.Bottom) {
                Text("a las ", color = Ink, fontSize = 24.sp)
                Text("10:40", color = Ink, fontWeight = FontWeight.Bold, fontSize = 30.sp)
                Spacer(Modifier.weight(1f))
                Box(Modifier.clip(CircleShape).background(CardGrey).padding(horizontal = 14.dp, vertical = 8.dp)) {
                    Text("15min", color = Ink, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                }
            }
            Text("Incluye +4 min por tráfico", color = Orange, fontSize = 13.sp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Rounded.WorkspacePremium, null, tint = Orange, modifier = Modifier.size(14.dp))
                Spacer(Modifier.width(4.dp))
                Text("Predicción exacta de llegada con Pro", color = Muted, fontSize = 12.sp)
            }

            Spacer(Modifier.height(14.dp))
            Row {
                MiniStat("Bus", "R12 · 2417", Modifier.weight(1f))
                MiniStat("Ocupación", "Medio lleno", Modifier.weight(1f))
                Column(Modifier.weight(1f)) {
                    Text("Tráfico aquí", color = Muted, fontSize = 12.sp)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(Modifier.size(8.dp).clip(CircleShape).background(Success))
                        Spacer(Modifier.width(4.dp))
                        Text("Fluido", color = Ink, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                    }
                }
            }
            Spacer(Modifier.height(12.dp))
            Row(
                Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(CardGrey)
                    .clickable(onClick = onUnit).padding(14.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(Icons.Rounded.DirectionsBus, null, tint = Ink, modifier = Modifier.size(20.dp))
                Spacer(Modifier.width(10.dp))
                Text("Unidad 2417 · HAG", color = Ink, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                Spacer(Modifier.weight(1f))
                Text("Ver detalles ›", color = Orange, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
            }

            Spacer(Modifier.height(16.dp))
            TripStep(Ink, "Camina a Plaza de Armas", "250 m · 3 min", "10:14", true)
            TripStep(LineR12, "R12 hacia Cerro Colorado", "4 paradas · bájate en Catedral · 12 min", "10:17", true)
            TripStep(Muted, "Transbordo al C4", "2 paradas · bájate en Hospital · 8 min", "10:29", true)
            TripStep(Muted, "Llega a Hospital Goyeneche", "180 m · 3 min", "10:37", false)

            Spacer(Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                InkButton("Aviso activado", onClick = {}, modifier = Modifier.weight(1f),
                    leading = { Icon(Icons.Rounded.Notifications, null, tint = Color(0xFFFFFFFF), modifier = Modifier.size(18.dp)) })
                Spacer(Modifier.width(8.dp))
                CircleIconButton(onClick = {}, bg = DangerSoft) {
                    Icon(Icons.Rounded.PriorityHigh, null, tint = Danger, modifier = Modifier.size(20.dp))
                }
                Spacer(Modifier.width(8.dp))
                CircleIconButton(onClick = {}, bg = CardGrey) {
                    Icon(Icons.Rounded.Share, null, tint = Ink, modifier = Modifier.size(20.dp))
                }
            }
            Spacer(Modifier.height(10.dp))
        }
    }
}

@Composable
private fun TripStep(color: Color, title: String, sub: String, time: String, line: Boolean) {
    Row(Modifier.fillMaxWidth().height(IntrinsicSize.Min)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(Modifier.size(14.dp).clip(CircleShape).background(color))
            if (line) Box(Modifier.width(2.dp).weight(1f).background(HairLine))
        }
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f).padding(bottom = 16.dp)) {
            Text(title, color = Ink, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
            Text(sub, color = Muted, fontSize = 12.sp)
        }
        Text(time, color = Ink, fontSize = 13.sp)
    }
}
