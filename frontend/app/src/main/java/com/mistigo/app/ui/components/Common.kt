package com.mistigo.app.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mistigo.app.ui.theme.*

/** Tarjeta blanca redondeada estándar. */
@Composable
fun RumboCard(
    modifier: Modifier = Modifier,
    color: Color = CardWhite,
    corner: Int = 22,
    padding: Dp = 16.dp,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(corner.dp))
            .background(color)
            .padding(padding),
        content = content,
    )
}

/** Badge de línea tipo "R12" en su color. */
@Composable
fun LineBadge(
    code: String,
    color: Color,
    soft: Color,
    size: Dp = 44.dp,
    textColor: Color = color,
) {
    Box(
        modifier = Modifier
            .size(size)
            .clip(RoundedCornerShape(14.dp))
            .background(soft),
        contentAlignment = Alignment.Center,
    ) {
        Text(code, color = textColor, fontWeight = FontWeight.Bold, fontSize = 15.sp)
    }
}

/** Pastilla pequeña de código de línea (para listas de empresa). */
@Composable
fun LineTag(code: String, color: Color, soft: Color) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(soft)
            .padding(horizontal = 10.dp, vertical = 4.dp),
    ) {
        Text(code, color = color, fontWeight = FontWeight.Bold, fontSize = 12.sp)
    }
}

/** Chip seleccionable (filtros). */
@Composable
fun FilterChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(if (selected) Ink else CardWhite)
            .then(if (!selected) Modifier.border(1.dp, HairLine, CircleShape) else Modifier)
            .clickable(onClick = onClick)
            .padding(horizontal = 18.dp, vertical = 9.dp),
    ) {
        Text(
            text,
            color = if (selected) Color(0xFFFFFFFF) else InkSoft,
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp,
        )
    }
}

/** Título a dos tonos: primera palabra fina, resto en negrita. */
@Composable
fun TwoToneTitle(light: String, bold: String, modifier: Modifier = Modifier) {
    Text(
        buildAnnotatedString {
            withStyle(SpanStyle(fontWeight = FontWeight.Normal, color = Ink)) { append(light) }
            append(" ")
            withStyle(SpanStyle(fontWeight = FontWeight.Bold, color = Blue)) { append(bold) }
        },
        fontSize = 26.sp,
        modifier = modifier,
    )
}

/** Cabecera con botón de volver circular y, opcionalmente, acción a la derecha. */
@Composable
fun BackHeader(
    onBack: () -> Unit,
    eyebrow: String? = null,
    titleLight: String? = null,
    titleBold: String? = null,
    trailing: (@Composable () -> Unit)? = null,
) {
    Row(verticalAlignment = Alignment.Top) {
        CircleIconButton(onClick = onBack) {
            Icon(Icons.Rounded.ArrowBackIosNew, contentDescription = "Volver",
                tint = Ink, modifier = Modifier.size(18.dp))
        }
        Spacer(Modifier.width(14.dp))
        Column(Modifier.weight(1f)) {
            if (eyebrow != null) {
                Text(eyebrow, color = Muted, fontSize = 12.sp)
                Spacer(Modifier.height(2.dp))
            }
            if (titleBold != null) {
                TwoToneTitle(titleLight ?: "", titleBold)
            }
        }
        if (trailing != null) {
            Spacer(Modifier.width(8.dp))
            trailing()
        }
    }
}

@Composable
fun CircleIconButton(
    onClick: () -> Unit,
    size: Dp = 44.dp,
    bg: Color = CardWhite,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .background(bg)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
        content = { content() },
    )
}

/** Botón primario negro (CTA principal del prototipo). */
@Composable
fun InkButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    leading: (@Composable () -> Unit)? = null,
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Ink)
            .clickable(onClick = onClick)
            .padding(vertical = 16.dp, horizontal = 20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (leading != null) { leading(); Spacer(Modifier.width(8.dp)) }
        Text(text, color = Color(0xFFFFFFFF), fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
    }
}

/** Botón primario naranja. */
@Composable
fun OrangeButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Orange)
            .clickable(onClick = onClick)
            .padding(vertical = 16.dp, horizontal = 20.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(text, color = Color(0xFFFFFFFF), fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
    }
}

/** Pastilla de estado pequeña con texto en color. */
@Composable
fun StatusPill(text: String, color: Color, soft: Color) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(soft)
            .padding(horizontal = 12.dp, vertical = 6.dp),
    ) {
        Text(text, color = color, fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
    }
}

@Composable
fun VerifiedPill() {
    StatusPill("✓ Verificada", Success, LineC4Soft)
}
