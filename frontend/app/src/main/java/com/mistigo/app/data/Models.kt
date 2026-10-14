package com.mistigo.app.data

import androidx.compose.ui.graphics.Color
import com.mistigo.app.ui.theme.*

data class BusLine(
    val code: String,
    val name: String,
    val frequency: String,
    val status: String,
    val statusKind: StatusKind = StatusKind.NORMAL,
    val color: Color,
    val soft: Color,
    val from: String,
    val to: String,
    val progress: Float,
    val favorite: Boolean = false,
    val firstBus: String = "5:00",
    val lastBus: String = "22:30",
    val fare: String = "$2.50",
)

enum class StatusKind { NORMAL, WARNING, DELAY }

data class Stop(
    val name: String,
    val distance: String,
    val walk: String,
    val line: String,
    val eta: String,
    val color: Color,
    val soft: Color,
)

data class Company(
    val code: String,
    val name: String,
    val since: String,
    val buses: String,
    val rating: String,
    val reviews: String,
    val color: Color,
    val soft: Color,
    val lines: List<String>,
)

data class Alert(
    val line: String,
    val lineColor: Color,
    val lineSoft: Color,
    val time: String,
    val title: String,
    val body: String,
    val kind: AlertKind,
)

enum class AlertKind { TRAFFIC, DETOUR, DELAY, INFO, STOP }

data class DestinationRecent(
    val name: String,
    val zone: String,
    val time: String,
)

data class Trip(
    val from: String,
    val to: String,
    val time: String,
    val duration: String,
    val price: String,
    val lines: List<String>,
    val day: String,
)

data class FamilyMember(
    val initials: String,
    val name: String,
    val status: String,
    val line: String?,
    val color: Color,
    val soft: Color,
    val arrived: Boolean = false,
)

data class City(
    val code: String,
    val name: String,
    val detail: String,
    val color: Color,
    val soft: Color,
    val current: Boolean = false,
    val soon: String? = null,
)

data class IntercityDeparture(
    val time: String,
    val destination: String,
    val duration: String,
    val company: String,
    val gate: String,
    val seats: String,
    val boarding: BoardingState,
)

enum class BoardingState { LEFT, BOARDING, ON_TIME }

fun lineColor(code: String): Color = when (code) {
    "R12" -> LineR12
    "C4" -> LineC4
    "R7" -> LineR7
    "L3" -> LineL3
    "X1" -> LineX1
    "R20" -> LineR20
    else -> Muted
}

fun lineSoft(code: String): Color = when (code) {
    "R12" -> LineR12Soft
    "C4" -> LineC4Soft
    "R7" -> LineR7Soft
    "L3" -> LineL3Soft
    "X1" -> LineX1Soft
    "R20" -> LineR20Soft
    else -> ChipGrey
}
