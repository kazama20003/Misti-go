package com.mistigo.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mistigo.app.data.SampleData
import com.mistigo.app.ui.components.*
import com.mistigo.app.ui.theme.*

@Composable
fun SearchScreen(onBack: () -> Unit, onPick: () -> Unit) {
    var cat by remember { mutableStateOf("Todos") }
    val cats = listOf("Todos", "Salud", "Estudio", "Compras", "Terminal")

    Column(
        Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            CircleIconButton(onClick = onBack) {
                Icon(Icons.Rounded.ArrowBackIosNew, null, tint = Ink, modifier = Modifier.size(18.dp))
            }
            Spacer(Modifier.width(10.dp))
            Row(
                Modifier.weight(1f).clip(RoundedCornerShape(16.dp)).background(CardWhite)
                    .padding(horizontal = 16.dp, vertical = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(Icons.Rounded.Search, null, tint = Muted, modifier = Modifier.size(20.dp))
                Spacer(Modifier.width(10.dp))
                Text("Buscar destino…", color = Muted, fontSize = 15.sp)
            }
        }

        Spacer(Modifier.height(14.dp))
        Row(
            Modifier.horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            cats.forEach { FilterChip(it, it == cat, { cat = it }) }
        }

        Spacer(Modifier.height(20.dp))
        Text("Destinos populares", color = Ink, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(Modifier.height(12.dp))
        Row(
            Modifier.horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            PopularCard("Más buscado", "Hospital Goyeneche", "26 min · R12 + C4", LineR12, onPick)
            PopularCard("Compras", "Mall Plaza Cay…", "41 min · X1", LineX1, onPick)
        }

        Spacer(Modifier.height(22.dp))
        Text("Búsquedas recientes", color = Ink, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(Modifier.height(10.dp))
        SampleData.recentDestinations.forEach { d ->
            RumboCard(Modifier.fillMaxWidth().padding(bottom = 10.dp).clickable(onClick = onPick), padding = 14.dp) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        Modifier.size(42.dp).clip(RoundedCornerShape(12.dp)).background(CardGrey),
                        contentAlignment = Alignment.Center,
                    ) { Icon(Icons.Rounded.Schedule, null, tint = Muted, modifier = Modifier.size(20.dp)) }
                    Spacer(Modifier.width(12.dp))
                    Column(Modifier.weight(1f)) {
                        Text(d.name, color = Ink, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                        Text(d.zone, color = Muted, fontSize = 12.sp)
                    }
                    Text(d.time, color = Ink, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }
            }
        }
    }
}

@Composable
private fun PopularCard(tag: String, title: String, sub: String, accent: Color, onClick: () -> Unit) {
    Box(
        Modifier.width(220.dp).height(150.dp).clip(RoundedCornerShape(18.dp))
            .background(CardGrey).clickable(onClick = onClick),
    ) {
        Box(Modifier.fillMaxWidth().height(6.dp).background(accent))
        Box(
            Modifier.align(Alignment.TopStart).padding(12.dp)
                .clip(CircleShape).background(CardWhite).padding(horizontal = 10.dp, vertical = 5.dp),
        ) { Text(tag, color = InkSoft, fontWeight = FontWeight.SemiBold, fontSize = 11.sp) }
        Column(
            Modifier.align(Alignment.BottomStart).fillMaxWidth()
                .background(Brush.verticalGradient(listOf(Color(0x00000000), Color(0xCC000000))))
                .padding(12.dp),
        ) {
            Text(title, color = Color(0xFFFFFFFF), fontWeight = FontWeight.Bold, fontSize = 15.sp, maxLines = 1)
            Text(sub, color = Color(0xCCFFFFFF), fontSize = 12.sp)
        }
    }
}
