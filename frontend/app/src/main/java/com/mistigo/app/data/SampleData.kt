package com.mistigo.app.data

import com.mistigo.app.ui.theme.*

object SampleData {

    val lines = listOf(
        BusLine("R12", "Periférico – Centro", "Cada 6 min", "Normal", StatusKind.NORMAL,
            LineR12, LineR12Soft, "Terminal Sur", "Hospital General", 0.45f, favorite = true,
            fare = "$2.50"),
        BusLine("C4", "Catedral – Hospital", "Cada 10 min", "Desvío por obras", StatusKind.WARNING,
            LineC4, LineC4Soft, "Parque Colón", "Aeropuerto", 0.7f, fare = "$2.50"),
        BusLine("R7", "Universidad – Hospital", "Cada 12 min", "Normal", StatusKind.NORMAL,
            LineR7, LineR7Soft, "Av. Reforma", "Plaza Sur", 0.95f, fare = "$3.00"),
        BusLine("L3", "Circular Centro", "Cada 15 min", "Retraso 5 min", StatusKind.DELAY,
            LineL3, LineL3Soft, "Col. Las Flores", "Universidad Central", 0.25f, fare = "$2.00"),
    )

    val nearbyStops = listOf(
        Stop("Plaza Central", "120 m", "2 min a pie", "R12", "3 min", LineR12, LineR12Soft),
        Stop("Mercado Norte", "280 m", "4 min a pie", "R7", "8 min", LineR7, LineR7Soft),
        Stop("Catedral", "650 m", "9 min a pie", "C4", "4 min", LineC4, LineC4Soft),
    )

    val companies = listOf(
        Company("TUM", "Transportes Unidos Metropolitanos", "Desde 1998", "48 buses",
            "4.6", "1240 opiniones", LineR12, LineR12Soft, listOf("R12", "R20")),
        Company("CVN", "Cooperativa Valle Norte", "Desde 2006", "26 buses",
            "4.2", "610 opiniones", LineC4, LineC4Soft, listOf("C4", "L3")),
        Company("EUS", "Expresos Unión del Sur", "Desde 2011", "31 buses",
            "4.4", "845 opiniones", LineR7, LineR7Soft, listOf("R7", "X1")),
    )

    val alerts = listOf(
        Alert("C4", LineC4, LineC4Soft, "Hace 3 min", "Tráfico denso en Av. Reforma",
            "Detectamos tráfico lento entre Catedral y Hospital General. El C4 llega con unos 4 min de retraso.",
            AlertKind.TRAFFIC),
        Alert("C4", LineC4, LineC4Soft, "Hace 12 min", "Desvío por obras en Av. Reforma",
            "El C4 no pasa por Catedral norte. Usa la parada provisional en 5ª Calle.",
            AlertKind.DETOUR),
        Alert("L3", LineL3, LineL3Soft, "Hace 25 min", "Retrasos de 5 minutos",
            "Tráfico intenso en el Centro Histórico. Considera el R12 como alternativa.",
            AlertKind.DELAY),
        Alert("R12", LineR12, LineR12Soft, "Hoy, 08:00", "Más buses en hora pico",
            "De 17:00 a 19:00 el R12 pasará cada 4 minutos.", AlertKind.INFO),
        Alert("X1", LineX1, LineX1Soft, "Ayer", "Nueva parada en Terminal Sur",
            "Desde el lunes el X1 se detiene frente a la Terminal Sur.", AlertKind.STOP),
    )

    val recentDestinations = listOf(
        DestinationRecent("Hospital General", "Zona 1 · 2.4 km", "26 min"),
        DestinationRecent("Universidad Central", "Campus Norte · 3.1 km", "34 min"),
        DestinationRecent("Terminal de Buses Central", "Zona 4 · 1.8 km", "19 min"),
        DestinationRecent("Centro Comercial Plaza Sur", "Calzada Sur · 4.0 km", "41 min"),
    )

    val historyTrips = listOf(
        Trip("Casa", "Hospital General", "08:12 – 08:38", "26 min", "$5.00",
            listOf("R12", "C4"), "Hoy"),
        Trip("Trabajo", "Casa", "18:05 – 18:31", "26 min", "$5.00",
            listOf("C4", "R12"), "Ayer"),
        Trip("Casa", "Trabajo", "07:40 – 08:04", "24 min", "$5.00",
            listOf("R12", "C4"), "Ayer"),
    )

    val family = listOf(
        FamilyMember("MA", "Mamá", "En el R7 · llega a las 10:42", "R7", LineR7, LineR7Soft),
        FamilyMember("HI", "Hijo", "Llegó a Instituto Nacional · 07:58", null, LineR20, LineR20Soft, arrived = true),
        FamilyMember("PA", "Papá", "En el C4 · tráfico denso, +4 min", "C4", LineC4, LineC4Soft),
        FamilyMember("AB", "Abuela", "En casa", null, LineR7, LineR7Soft, arrived = true),
    )

    val cities = listOf(
        City("CC", "Ciudad Capital", "42 líneas · 3 empresas · 610 paradas", LineR12, LineR12Soft, current = true),
        City("CN", "Ciudad del Norte", "18 líneas · 2 empresas · 240 paradas", LineC4, LineC4Soft),
        City("PS", "Puerto Sur", "12 líneas · 2 empresas · 165 paradas", LineR20, LineR20Soft),
    )

    val citiesSoon = listOf(
        City("VA", "Valle Alto", "Llega en diciembre", Muted, ChipGrey, soon = "Llega en diciembre"),
        City("CA", "Costa Azul", "Llega en marzo de 2027", Muted, ChipGrey, soon = "Llega en marzo de 2027"),
    )

    val intercity = listOf(
        IntercityDeparture("09:45", "Ciudad del Norte", "2 h 50 min", "TUM", "Andén 2", "Sin asientos", BoardingState.LEFT),
        IntercityDeparture("10:20", "Puerto Sur", "3 h 10 min", "EUS", "Andén 5", "8 asientos libres", BoardingState.BOARDING),
        IntercityDeparture("10:30", "Ciudad del Norte", "2 h 40 min", "EUS", "Andén 4", "14 asientos libres", BoardingState.ON_TIME),
        IntercityDeparture("11:15", "Valle Alto", "4 h 05 min", "CVN", "Andén 1", "22 asientos libres", BoardingState.ON_TIME),
    )
}
