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
import androidx.compose.material.icons.rounded.GpsFixed
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
import com.mistigo.app.data.City
import com.mistigo.app.data.SampleData
import com.mistigo.app.ui.components.*
import com.mistigo.app.ui.theme.*

@Composable
fun CitiesScreen(onBack: () -> Unit, onPick: () -> Unit) {
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)) {
        BackHeader(onBack, "3 ciudades disponibles", "Elige tu", "ciudad")
        Spacer(Modifier.height(14.dp))
        InkButton("Detectar mi ciudad con el GPS", onClick = onPick, modifier = Modifier.fillMaxWidth(),
            leading = { Icon(Icons.Rounded.GpsFixed, null, tint = Color(0xFFFFFFFF), modifier = Modifier.size(18.dp)) })
        Spacer(Modifier.height(18.dp))
        Text("Disponibles", color = Ink, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(Modifier.height(10.dp))
        SampleData.cities.forEach { c ->
            CityCard(c, onPick)
            Spacer(Modifier.height(10.dp))
        }
        Spacer(Modifier.height(8.dp))
        Text("Próximamente", color = Ink, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(Modifier.height(10.dp))
        SampleData.citiesSoon.forEach { c ->
            SoonCard(c)
            Spacer(Modifier.height(10.dp))
        }
        Spacer(Modifier.height(6.dp))
    }
}

@Composable
private fun CityCard(c: City, onClick: () -> Unit) {
    Column(
        Modifier.fillMaxWidth().clip(RoundedCornerShape(18.dp)).background(CardWhite)
            .then(if (c.current) Modifier.border(2.dp, Orange, RoundedCornerShape(18.dp)) else Modifier)
            .clickable(onClick = onClick).padding(16.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            LineBadge(c.code, c.color, c.soft, size = 48.dp)
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(c.name, color = Ink, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(c.detail, color = Muted, fontSize = 12.sp)
            }
            if (c.current) StatusPill("Actual", InkSoft, CardGrey)
        }
    }
}

@Composable
private fun SoonCard(c: City) {
    RumboCard(Modifier.fillMaxWidth(), padding = 14.dp) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            LineBadge(c.code, Muted, ChipGrey, size = 44.dp, textColor = Muted)
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(c.name, color = Ink, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                Text(c.soon ?: "", color = Muted, fontSize = 12.sp)
            }
            Box(
                Modifier.clip(CircleShape).border(1.dp, HairLine, CircleShape).padding(horizontal = 16.dp, vertical = 9.dp),
            ) { Text("Avísame", color = Ink, fontWeight = FontWeight.SemiBold, fontSize = 13.sp) }
        }
    }
}
