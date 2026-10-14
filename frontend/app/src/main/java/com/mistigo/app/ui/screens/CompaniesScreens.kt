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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mistigo.app.data.Company
import com.mistigo.app.data.SampleData
import com.mistigo.app.data.lineColor
import com.mistigo.app.data.lineSoft
import com.mistigo.app.ui.components.*
import com.mistigo.app.ui.theme.*

@Composable
fun CompaniesScreen(onBack: () -> Unit, onOpen: () -> Unit) {
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)) {
        BackHeader(onBack, "3 empresas · 105 buses", "Empresas", "afiliadas")
        Spacer(Modifier.height(14.dp))
        Row(
            Modifier.fillMaxWidth().clip(RoundedCornerShape(14.dp)).background(OrangeSoft).padding(14.dp),
            verticalAlignment = Alignment.Top,
        ) {
            Icon(Icons.Rounded.VerifiedUser, null, tint = Orange, modifier = Modifier.size(20.dp))
            Spacer(Modifier.width(10.dp))
            Text("Todas las empresas afiliadas pasan revisión técnica de sus unidades y tienen conductores certificados.",
                color = InkSoft, fontSize = 13.sp, lineHeight = 18.sp)
        }
        Spacer(Modifier.height(14.dp))
        SampleData.companies.forEach { c ->
            CompanyCard(c, onOpen)
            Spacer(Modifier.height(12.dp))
        }
    }
}

@Composable
private fun CompanyCard(c: Company, onClick: () -> Unit) {
    RumboCard(Modifier.fillMaxWidth().clickable(onClick = onClick), padding = 16.dp) {
        Row {
            LineBadge(c.code, c.color, c.soft, size = 52.dp)
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(c.name, color = Ink, fontWeight = FontWeight.Bold, fontSize = 16.sp, lineHeight = 20.sp)
                Text("${c.since} · ${c.buses}", color = Muted, fontSize = 12.sp)
            }
            Column(horizontalAlignment = Alignment.End) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Rounded.Star, null, tint = Warning, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(3.dp))
                    Text(c.rating, color = Ink, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                }
                Text(c.reviews, color = Muted, fontSize = 11.sp)
            }
        }
        Spacer(Modifier.height(12.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            c.lines.forEach { l ->
                LineTag(l, lineColor(l), lineSoft(l)); Spacer(Modifier.width(8.dp))
            }
            Spacer(Modifier.weight(1f))
            VerifiedPill()
        }
    }
}

@Composable
fun CompanyDetailScreen(onBack: () -> Unit, onOpenLine: () -> Unit) {
    val c = SampleData.companies.first()
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            CircleIconButton(onClick = onBack) {
                Icon(Icons.Rounded.ArrowBackIosNew, null, tint = Ink, modifier = Modifier.size(18.dp))
            }
            Spacer(Modifier.weight(1f))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Rounded.Verified, null, tint = Success, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(6.dp))
                Text("Empresa verificada", color = Success, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
            }
        }
        Spacer(Modifier.height(14.dp))
        RumboCard(Modifier.fillMaxWidth(), padding = 16.dp) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                LineBadge(c.code, c.color, c.soft, size = 56.dp)
                Spacer(Modifier.width(14.dp))
                Column {
                    Text(c.name, color = Ink, fontWeight = FontWeight.Bold, fontSize = 17.sp, lineHeight = 21.sp)
                    Text("Afiliada desde 1998", color = Muted, fontSize = 12.sp)
                }
            }
            Spacer(Modifier.height(16.dp))
            Box(Modifier.fillMaxWidth().height(1.dp).background(HairLine))
            Spacer(Modifier.height(16.dp))
            Row {
                DetailStat("Flota", "48 buses", Modifier.weight(1f))
                DetailStat("Líneas", "2", Modifier.weight(1f))
                DetailStat("Calificación", "★ 4.6", Modifier.weight(1f))
            }
        }
        Spacer(Modifier.height(18.dp))
        Text("Líneas que opera", color = Ink, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(Modifier.height(10.dp))
        OperatedLine("R12", "Periférico – Centro", "Cada 6 min · Normal", onOpenLine)
        Spacer(Modifier.height(10.dp))
        OperatedLine("R20", "Terminal – Estadio", "Cada 9 min · Normal", onOpenLine)

        Spacer(Modifier.height(18.dp))
        Text("Flota", color = Ink, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(Modifier.height(10.dp))
        RumboCard(Modifier.fillMaxWidth(), padding = 16.dp) {
            Row {
                DetailStat("En servicio", "16 buses", Modifier.weight(1f))
                DetailStat("Con aire", "90%", Modifier.weight(1f))
                DetailStat("Accesibles", "75%", Modifier.weight(1f))
            }
        }

        Spacer(Modifier.height(18.dp))
        Text("Opiniones de pasajeros", color = Ink, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(Modifier.height(10.dp))
        RumboCard(Modifier.fillMaxWidth(), padding = 16.dp) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(90.dp)) {
                    Text("4.6", color = Ink, fontWeight = FontWeight.Bold, fontSize = 40.sp)
                    Text("1240 opiniones", color = Muted, fontSize = 11.sp)
                }
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    RatingBar("5", 0.68f, "68%")
                    RatingBar("4", 0.20f, "20%")
                    RatingBar("3", 0.07f, "7%")
                    RatingBar("2", 0.03f, "3%")
                    RatingBar("1", 0.02f, "2%")
                }
            }
        }
        Spacer(Modifier.height(10.dp))
    }
}

@Composable
private fun DetailStat(label: String, value: String, modifier: Modifier) {
    Column(modifier) {
        Text(label, color = Muted, fontSize = 12.sp)
        Text(value, color = Ink, fontWeight = FontWeight.Bold, fontSize = 16.sp)
    }
}

@Composable
private fun OperatedLine(code: String, name: String, freq: String, onClick: () -> Unit) {
    RumboCard(Modifier.fillMaxWidth().clickable(onClick = onClick), padding = 14.dp) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            LineBadge(code, lineColor(code), lineSoft(code))
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(name, color = Ink, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                Text(freq, color = Muted, fontSize = 12.sp)
            }
            Icon(Icons.Rounded.ChevronRight, null, tint = Muted)
        }
    }
}

@Composable
private fun RatingBar(label: String, fraction: Float, pct: String) {
    Row(Modifier.fillMaxWidth().padding(vertical = 3.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(label, color = Muted, fontSize = 12.sp, modifier = Modifier.width(14.dp))
        Spacer(Modifier.width(6.dp))
        Box(Modifier.weight(1f).height(7.dp).clip(CircleShape).background(CardGrey)) {
            Box(Modifier.fillMaxWidth(fraction).height(7.dp).clip(CircleShape).background(Orange))
        }
        Spacer(Modifier.width(8.dp))
        Text(pct, color = Muted, fontSize = 11.sp, modifier = Modifier.width(32.dp))
    }
}
