package com.mistigo.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mistigo.app.ui.theme.*

enum class Tab(val label: String, val icon: ImageVector) {
    INICIO("Inicio", Icons.Rounded.Home),
    MAPA("Mapa", Icons.Rounded.Map),
    LINEAS("Líneas", Icons.Rounded.SwapVert),
    PLANES("Planes", Icons.Rounded.WorkspacePremium),
    AJUSTES("Ajustes", Icons.Rounded.Settings),
}

@Composable
fun RumboBottomBar(
    current: Tab,
    onSelect: (Tab) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(28.dp))
            .background(CardWhite)
            .padding(horizontal = 10.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Tab.entries.forEach { tab ->
            val selected = tab == current
            if (selected) {
                Row(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Ink)
                        .clickable { onSelect(tab) }
                        .padding(horizontal = 16.dp, vertical = 11.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(tab.icon, contentDescription = tab.label,
                        tint = Color(0xFFFFFFFF), modifier = Modifier.size(20.dp))
                    Spacer(Modifier.width(7.dp))
                    Text(tab.label, color = Color(0xFFFFFFFF),
                        fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                }
            } else {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable { onSelect(tab) }
                        .padding(14.dp),
                ) {
                    Icon(tab.icon, contentDescription = tab.label,
                        tint = Muted, modifier = Modifier.size(22.dp))
                }
            }
        }
    }
}
