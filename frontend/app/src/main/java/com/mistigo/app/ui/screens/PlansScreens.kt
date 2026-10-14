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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mistigo.app.ui.components.*
import com.mistigo.app.ui.theme.*

// 9 · Planes
@Composable
fun PlansScreen(onCompare: () -> Unit, onTry: () -> Unit) {
    var period by remember { mutableStateOf("Mensual") }
    Column(
        Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
    ) {
        Spacer(Modifier.height(6.dp))
        Text("Tu plan: Gratis", color = Muted, fontSize = 13.sp)
        TwoToneTitle("Elige tu", "plan")
        Spacer(Modifier.height(14.dp))

        // Promo
        Box(
            Modifier.fillMaxWidth().clip(RoundedCornerShape(20.dp))
                .background(Brush.horizontalGradient(listOf(Orange, OrangeDark))).padding(18.dp),
        ) {
            Icon(Icons.Rounded.WorkspacePremium, null, tint = Color(0x33FFFFFF),
                modifier = Modifier.size(90.dp).align(Alignment.CenterEnd))
            Column {
                Text("Más herramientas para moverte", color = Color(0xCCFFFFFF), fontSize = 12.sp)
                Spacer(Modifier.height(6.dp))
                Text("Prueba Plus gratis", color = Color(0xFFFFFFFF), fontWeight = FontWeight.Bold, fontSize = 22.sp)
                Text("durante 7 días", color = Color(0xFFFFFFFF), fontSize = 22.sp)
                Spacer(Modifier.height(12.dp))
                InkButton("Probar gratis", onTry)
            }
        }

        Spacer(Modifier.height(16.dp))
        // Toggle periodo
        Row(
            Modifier.fillMaxWidth().clip(CircleShape).background(CardWhite).padding(4.dp),
        ) {
            PeriodTab("Mensual", period == "Mensual", Modifier.weight(1f)) { period = "Mensual" }
            PeriodTab("Anual  -20%", period == "Anual", Modifier.weight(1f)) { period = "Anual" }
        }

        Spacer(Modifier.height(16.dp))
        // Plan Gratis
        Column(
            Modifier.fillMaxWidth().clip(RoundedCornerShape(20.dp)).background(CardWhite)
                .border(2.dp, Orange, RoundedCornerShape(20.dp)).padding(18.dp),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Gratis", color = Ink, fontWeight = FontWeight.Bold, fontSize = 20.sp, modifier = Modifier.weight(1f))
                StatusPill("Tu plan", InkSoft, CardGrey)
            }
            Text("Lo esencial para moverte", color = Muted, fontSize = 13.sp)
            Spacer(Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.Bottom) {
                Text("$0", color = Ink, fontWeight = FontWeight.Bold, fontSize = 32.sp)
                Spacer(Modifier.width(6.dp))
                Text("para siempre", color = Muted, fontSize = 13.sp, modifier = Modifier.padding(bottom = 6.dp))
            }
            Spacer(Modifier.height(12.dp))
            FeatureLine("Ruta más rápida y mapa en vivo", true)
            FeatureLine("Horarios y tráfico en tu ruta", true)
            FeatureLine("1 recordatorio de horario", true)
            FeatureLine("Rutas alternativas para evitar tráfico", false)
            FeatureLine("Con anuncios", false)
            Spacer(Modifier.height(12.dp))
            Box(
                Modifier.fillMaxWidth().clip(RoundedCornerShape(14.dp)).background(CardGrey)
                    .padding(vertical = 14.dp),
                contentAlignment = Alignment.Center,
            ) { Text("Es tu plan actual", color = Muted, fontWeight = FontWeight.SemiBold) }
        }
        Spacer(Modifier.height(12.dp))
        Box(
            Modifier.fillMaxWidth().clickable(onClick = onCompare).padding(8.dp),
            contentAlignment = Alignment.Center,
        ) { Text("Comparar todos los planes ›", color = Orange, fontWeight = FontWeight.SemiBold) }
        Spacer(Modifier.height(10.dp))
    }
}

@Composable
private fun PeriodTab(text: String, selected: Boolean, modifier: Modifier, onClick: () -> Unit) {
    Box(
        modifier.clip(CircleShape).background(if (selected) Ink else Color(0x00000000))
            .clickable(onClick = onClick).padding(vertical = 12.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(text, color = if (selected) Color(0xFFFFFFFF) else Muted,
            fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
    }
}

@Composable
fun FeatureLine(text: String, included: Boolean) {
    Row(Modifier.fillMaxWidth().padding(vertical = 5.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(
            if (included) Icons.Rounded.CheckCircle else Icons.Rounded.RemoveCircleOutline,
            null, tint = if (included) Orange else MutedLight, modifier = Modifier.size(20.dp),
        )
        Spacer(Modifier.width(10.dp))
        Text(text, color = if (included) InkSoft else Muted, fontSize = 14.sp)
    }
}

// 19 · Comparar planes
@Composable
fun ComparePlansScreen(onBack: () -> Unit, onTry: () -> Unit) {
    data class Row4(val name: String, val cells: List<String>)
    val features = listOf(
        Row4("Ruta más rápida y mapa en vivo", listOf("✓", "✓", "✓", "✓")),
        Row4("Horarios oficiales", listOf("✓", "✓", "✓", "✓")),
        Row4("Tráfico en tu ruta", listOf("✓", "✓", "✓", "✓")),
        Row4("Rutas alternativas para evitar tráfico", listOf("–", "✓", "✓", "✓")),
        Row4("Desvíos sugeridos en el viaje", listOf("–", "–", "✓", "✓")),
        Row4("Alertas del servicio", listOf("Todas", "Tus líneas", "Tus líneas", "Tus líneas")),
        Row4("Sin anuncios", listOf("–", "✓", "✓", "✓")),
        Row4("Recordatorios de horario", listOf("1", "∞", "∞", "∞")),
    )
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)) {
        BackHeader(onBack, "Todas las funciones", "Comparar", "planes")
        Spacer(Modifier.height(14.dp))
        Row(
            Modifier.fillMaxWidth().clip(CircleShape).background(CardWhite).padding(4.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            PlanTab("Gratis", "$0", true, Modifier.weight(1f))
            PlanTab("Plus", "$1.99", false, Modifier.weight(1f))
            PlanTab("Pro", "$3.99", false, Modifier.weight(1f))
            PlanTab("Familia", "$5.99", false, Modifier.weight(1f))
        }
        Spacer(Modifier.height(14.dp))
        RumboCard(Modifier.fillMaxWidth(), padding = 16.dp) {
            features.forEachIndexed { i, f ->
                Text(f.name, color = Ink, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                Spacer(Modifier.height(6.dp))
                Row(Modifier.fillMaxWidth()) {
                    f.cells.forEachIndexed { idx, c ->
                        Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
                            CompareCell(c, highlight = idx == 0)
                        }
                    }
                }
                if (i != features.lastIndex) {
                    Spacer(Modifier.height(10.dp))
                    Box(Modifier.fillMaxWidth().height(1.dp).background(HairLine))
                    Spacer(Modifier.height(10.dp))
                }
            }
        }
        Spacer(Modifier.height(14.dp))
        OrangeButton("Probar Plus gratis 7 días", onTry, Modifier.fillMaxWidth())
        Spacer(Modifier.height(10.dp))
    }
}

@Composable
private fun PlanTab(name: String, price: String, selected: Boolean, modifier: Modifier) {
    Column(
        modifier.clip(CircleShape).background(if (selected) Ink else Color(0x00000000))
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(name, color = if (selected) Color(0xFFFFFFFF) else Ink, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
        Text(price, color = if (selected) Color(0xCCFFFFFF) else Muted, fontSize = 10.sp)
    }
}

@Composable
private fun CompareCell(c: String, highlight: Boolean) {
    when (c) {
        "✓" -> if (highlight) {
            Box(Modifier.clip(RoundedCornerShape(8.dp)).background(OrangeSoft).padding(horizontal = 14.dp, vertical = 4.dp)) {
                Text("✓", color = Orange, fontWeight = FontWeight.Bold)
            }
        } else Text("✓", color = Orange, fontWeight = FontWeight.Bold)
        "–" -> if (highlight) {
            Box(Modifier.clip(RoundedCornerShape(8.dp)).background(OrangeSoft).padding(horizontal = 14.dp, vertical = 4.dp)) {
                Text("–", color = Muted)
            }
        } else Text("–", color = Muted)
        else -> Text(c, color = if (highlight) Orange else InkSoft, fontSize = 11.sp, fontWeight = FontWeight.Medium)
    }
}

// 20 · Mi plan
@Composable
fun MyPlanScreen(onBack: () -> Unit) {
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)) {
        BackHeader(onBack, "Suscripción", "Mi", "plan")
        Spacer(Modifier.height(14.dp))
        Box(
            Modifier.fillMaxWidth().clip(RoundedCornerShape(20.dp))
                .background(Brush.horizontalGradient(listOf(Orange, OrangeDark))).padding(18.dp),
        ) {
            Icon(Icons.Rounded.WorkspacePremium, null, tint = Color(0x26FFFFFF),
                modifier = Modifier.size(80.dp).align(Alignment.CenterEnd))
            Column {
                Text("Plan actual", color = Color(0xCCFFFFFF), fontSize = 12.sp)
                Text("Plus", color = Color(0xFFFFFFFF), fontWeight = FontWeight.Bold, fontSize = 30.sp)
                Text("Prueba gratis hasta el 21 oct · luego $1.99 / mes", color = Color(0xE6FFFFFF), fontSize = 13.sp)
            }
        }
        Spacer(Modifier.height(18.dp))
        Text("Uso este mes", color = Ink, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(Modifier.height(10.dp))
        RumboCard(Modifier.fillMaxWidth(), padding = 16.dp) {
            UsageBar("Recordatorios activos", "0 de ilimitados", 0.15f)
            Spacer(Modifier.height(14.dp))
            UsageBar("Rutas guardadas", "2 de ilimitados", 0.35f)
            Spacer(Modifier.height(14.dp))
            UsageBar("Contactos para compartir viaje", "2 de 3", 0.66f)
        }
        Spacer(Modifier.height(18.dp))
        Text("Tus herramientas", color = Ink, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(Modifier.height(10.dp))
        RumboCard(Modifier.fillMaxWidth(), padding = 8.dp) {
            ToolRow("Rutas alternativas para evitar tráfico", true, null)
            ToolRow("Desvíos sugeridos en el viaje", false, "Pro")
            ToolRow("Alertas del servicio: Tus líneas", true, null)
            ToolRow("Sin anuncios", true, null)
            ToolRow("Recordatorios de horario: ∞", true, null)
            ToolRow("Rutas guardadas: ∞", true, null)
        }
        Spacer(Modifier.height(10.dp))
    }
}

@Composable
private fun UsageBar(label: String, value: String, progress: Float) {
    Row { Text(label, color = InkSoft, fontSize = 14.sp, modifier = Modifier.weight(1f))
        Text(value, color = Ink, fontWeight = FontWeight.SemiBold, fontSize = 13.sp) }
    Spacer(Modifier.height(6.dp))
    Box(Modifier.fillMaxWidth().height(6.dp).clip(CircleShape).background(CardGrey)) {
        Box(Modifier.fillMaxWidth(progress).height(6.dp).clip(CircleShape).background(Orange))
    }
}

@Composable
private fun ToolRow(text: String, enabled: Boolean, tag: String?) {
    Row(Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 12.dp), verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier.size(26.dp).clip(CircleShape).background(if (enabled) LineC4Soft else CardGrey),
            contentAlignment = Alignment.Center) {
            Icon(if (enabled) Icons.Rounded.Check else Icons.Rounded.Lock, null,
                tint = if (enabled) Success else Muted, modifier = Modifier.size(15.dp))
        }
        Spacer(Modifier.width(12.dp))
        Text(text, color = if (enabled) InkSoft else Muted, fontSize = 14.sp, modifier = Modifier.weight(1f))
        if (tag != null) StatusPill(tag, Orange, OrangeSoft)
    }
}

// 21 · Plan activado
@Composable
fun PlanActivatedScreen(onStart: () -> Unit, onSeePlan: () -> Unit) {
    Box(
        Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(OrangeLight, Orange, OrangeDark))).padding(28.dp),
    ) {
        Column(Modifier.fillMaxSize().systemBarsPadding(), verticalArrangement = Arrangement.Center) {
            Box(Modifier.size(72.dp).clip(RoundedCornerShape(20.dp)).background(Color(0x33FFFFFF)),
                contentAlignment = Alignment.Center) {
                Icon(Icons.Rounded.WorkspacePremium, null, tint = Color(0xFFFFFFFF), modifier = Modifier.size(40.dp))
            }
            Spacer(Modifier.height(20.dp))
            Text("— — — —", color = Color(0xCCFFFFFF))
            Spacer(Modifier.height(12.dp))
            Text("¡Ya tienes", color = Color(0xFFFFFFFF), fontSize = 28.sp)
            Text("Rumbo Plus!", color = Color(0xFFFFFFFF), fontWeight = FontWeight.Bold, fontSize = 28.sp)
            Spacer(Modifier.height(12.dp))
            Text("Tu prueba gratis de 7 días termina el 21 oct. Te avisaremos antes y puedes cancelar cuando quieras.",
                color = Color(0xE6FFFFFF), fontSize = 15.sp, lineHeight = 21.sp)
            Spacer(Modifier.height(18.dp))
            Column(
                Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(Color(0x26FFFFFF)).padding(16.dp),
            ) {
                listOf(
                    "Rutas alternativas para evitar el tráfico",
                    "Sin anuncios",
                    "Recordatorios ilimitados y horarios sin conexión",
                    "Alertas solo de tus líneas",
                    "Estadísticas de tus viajes",
                ).forEach {
                    Row(Modifier.padding(vertical = 5.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Rounded.Check, null, tint = Color(0xFFFFFFFF), modifier = Modifier.size(18.dp))
                        Spacer(Modifier.width(10.dp))
                        Text(it, color = Color(0xFFFFFFFF), fontSize = 14.sp)
                    }
                }
            }
            Spacer(Modifier.height(20.dp))
            InkButton("Empezar a usar", onStart, Modifier.fillMaxWidth())
            Spacer(Modifier.height(12.dp))
            Box(
                Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(Color(0x33FFFFFF))
                    .clickable(onClick = onSeePlan).padding(vertical = 16.dp),
                contentAlignment = Alignment.Center,
            ) { Text("Ver mi plan", color = Color(0xFFFFFFFF), fontWeight = FontWeight.SemiBold, fontSize = 16.sp) }
        }
    }
}
