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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mistigo.app.data.Dest
import com.mistigo.app.ui.components.*
import com.mistigo.app.ui.theme.*

data class HomeShortcut(
    val label: String,
    val icon: ImageVector,
    val tint: Color,
    val soft: Color,
    val dest: String,
    val badge: String? = null,
    val tag: String? = null,
)

@Composable
fun HomeScreen(onOpen: (String) -> Unit) {
    val shortcuts = listOf(
        HomeShortcut("Horarios", Icons.Rounded.Schedule, Orange, OrangeSoft, Dest.SCHEDULES),
        HomeShortcut("Líneas", Icons.Rounded.SwapVert, LineC4, LineC4Soft, Dest.LINES),
        HomeShortcut("Empresas", Icons.Rounded.Business, LineR7, LineR7Soft, Dest.COMPANIES),
        HomeShortcut("Alertas", Icons.Rounded.WarningAmber, LineR20, LineR20Soft, Dest.ALERTS, badge = "5"),
        HomeShortcut("Favoritos", Icons.Rounded.FavoriteBorder, LineX1, LineX1Soft, Dest.FAVORITES),
        HomeShortcut("Historial", Icons.Rounded.History, LineR7, LineR7Soft, Dest.HISTORY),
        HomeShortcut("Seguridad", Icons.Rounded.Shield, LineC4, LineC4Soft, Dest.SECURITY),
        HomeShortcut("Planes", Icons.Rounded.WorkspacePremium, LineL3, LineL3Soft, Dest.COMPARE_PLANS),
        HomeShortcut("Ciudades", Icons.Rounded.LocationCity, LineR20, LineR20Soft, Dest.CITIES),
        HomeShortcut("Planificador", Icons.Rounded.CalendarMonth, Orange, OrangeSoft, Dest.WEEKLY, tag = "PRO"),
        HomeShortcut("Entre ciudades", Icons.Rounded.AltRoute, LineC4, LineC4Soft, Dest.INTERCITY),
        HomeShortcut("Mi familia", Icons.Rounded.Accessible, LineR7, LineR7Soft, Dest.FAMILY),
    )

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
    ) {
        Spacer(Modifier.height(10.dp))
        // Cabecera usuario
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                Modifier.size(44.dp).clip(RoundedCornerShape(14.dp)).background(OrangeSoft),
                contentAlignment = Alignment.Center,
            ) { Text("TN", color = Orange, fontWeight = FontWeight.Bold) }
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text("¡Buenos días!", color = Muted, fontSize = 13.sp)
                Text("[Tu nombre]", color = Ink, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }
            Column(horizontalAlignment = Alignment.End) {
                Text("Ciudad", color = Muted, fontSize = 11.sp)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { onOpen(Dest.CITIES) },
                ) {
                    Text("Ciudad Capital", color = Ink, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                    Icon(Icons.Rounded.ExpandMore, null, tint = Ink, modifier = Modifier.size(18.dp))
                }
            }
            Spacer(Modifier.width(10.dp))
            Box {
                CircleIconButton(onClick = { onOpen(Dest.ALERTS) }) {
                    Icon(Icons.Rounded.NotificationsNone, null, tint = Ink, modifier = Modifier.size(22.dp))
                }
                Box(
                    Modifier.align(Alignment.TopEnd).padding(6.dp)
                        .size(9.dp).clip(CircleShape).background(Orange)
                )
            }
        }

        Spacer(Modifier.height(16.dp))
        // Buscador
        Row(
            Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(CardWhite)
                .clickable { onOpen(Dest.SEARCH) }.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(Icons.Rounded.Search, null, tint = Muted, modifier = Modifier.size(22.dp))
            Spacer(Modifier.width(12.dp))
            Text("¿A dónde quieres ir?", color = Muted, fontSize = 15.sp, modifier = Modifier.weight(1f))
            Icon(Icons.Rounded.Tune, null, tint = Ink, modifier = Modifier.size(22.dp))
        }

        Spacer(Modifier.height(16.dp))
        // Tarjeta "Tu próximo bus"
        NextBusCard(onClick = { onOpen(Dest.LIVE_TRIP) })

        Spacer(Modifier.height(18.dp))
        // Grid de accesos
        shortcuts.chunked(4).forEach { row ->
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                row.forEach { s ->
                    ShortcutTile(s, Modifier.weight(1f)) { onOpen(s.dest) }
                }
                repeat(4 - row.size) { Spacer(Modifier.weight(1f)) }
            }
            Spacer(Modifier.height(10.dp))
        }

        Spacer(Modifier.height(6.dp))
        // Banner publicidad
        Row(
            Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(CardGrey)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(Modifier.weight(1f)) {
                Text("Anuncio", color = Muted, fontSize = 11.sp)
                Text("Publicidad", color = InkSoft, fontSize = 13.sp)
            }
            Text("Quitar anuncios", color = Orange, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
        }
        Spacer(Modifier.height(14.dp))
    }
}

@Composable
private fun NextBusCard(onClick: () -> Unit) {
    Box(
        Modifier.fillMaxWidth().height(230.dp).clip(RoundedCornerShape(22.dp))
            .background(Color(0xFFCDD4DC)).clickable(onClick = onClick),
    ) {
        // "Edificio" + bus simplificados
        Box(
            Modifier.align(Alignment.Center).padding(top = 20.dp)
                .size(width = 200.dp, height = 110.dp)
                .clip(RoundedCornerShape(10.dp)).background(Color(0xFF2B3440))
        )
        Box(
            Modifier.align(Alignment.BottomCenter).padding(bottom = 60.dp)
                .fillMaxWidth(0.82f).height(78.dp)
                .clip(RoundedCornerShape(12.dp)).background(Orange)
        )
        // Pill superior
        Row(
            Modifier.align(Alignment.TopStart).padding(14.dp)
                .clip(CircleShape).background(Orange).padding(horizontal = 12.dp, vertical = 7.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(Icons.Rounded.DirectionsBus, null, tint = Color(0xFFFFFFFF), modifier = Modifier.size(16.dp))
            Spacer(Modifier.width(6.dp))
            Text("Tu próximo bus", color = Color(0xFFFFFFFF), fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
        }
        Box(Modifier.align(Alignment.TopEnd).padding(14.dp)) {
            CircleIconButton(onClick = onClick, size = 38.dp, bg = CardWhite, content = {
                Icon(Icons.Rounded.Favorite, null, tint = Orange, modifier = Modifier.size(20.dp))
            })
        }
        // Overlay inferior con datos
        Row(
            Modifier.align(Alignment.BottomCenter).fillMaxWidth()
                .background(Brush.verticalGradient(listOf(Color(0x00000000), Color(0xCC000000))))
                .padding(16.dp),
        ) {
            InfoCol("Línea", "R12 Centro", Modifier.weight(1f))
            InfoCol("Llega en", "3 min", Modifier.weight(1f))
            InfoCol("Parada", "Plaza Central", Modifier.weight(1f))
        }
    }
}

@Composable
private fun InfoCol(label: String, value: String, modifier: Modifier = Modifier) {
    Column(modifier) {
        Text(label, color = Color(0xB3FFFFFF), fontSize = 11.sp)
        Text(value, color = Color(0xFFFFFFFF), fontWeight = FontWeight.Bold, fontSize = 14.sp)
    }
}

@Composable
private fun ShortcutTile(s: HomeShortcut, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Column(
        modifier.clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(contentAlignment = Alignment.TopEnd) {
            Box(
                Modifier.fillMaxWidth().aspectRatio(1f)
                    .clip(RoundedCornerShape(18.dp)).background(s.soft),
                contentAlignment = Alignment.Center,
            ) {
                Icon(s.icon, null, tint = s.tint, modifier = Modifier.size(26.dp))
            }
            if (s.badge != null) {
                Box(
                    Modifier.padding(6.dp).size(18.dp).clip(CircleShape).background(Orange),
                    contentAlignment = Alignment.Center,
                ) { Text(s.badge, color = Color(0xFFFFFFFF), fontSize = 10.sp, fontWeight = FontWeight.Bold) }
            }
            if (s.tag != null) {
                Box(
                    Modifier.padding(4.dp).clip(RoundedCornerShape(7.dp)).background(Ink)
                        .padding(horizontal = 6.dp, vertical = 2.dp),
                ) { Text(s.tag, color = Color(0xFFFFFFFF), fontSize = 8.sp, fontWeight = FontWeight.Bold) }
            }
        }
        Spacer(Modifier.height(6.dp))
        Text(s.label, color = InkSoft, fontSize = 11.sp, fontWeight = FontWeight.Medium,
            maxLines = 1)
    }
}
