package com.mistigo.app.data

import com.mistigo.app.ui.theme.*

object SampleData {

    val lines = listOf(
        BusLine("R12", "Periférico – Centro", "Cada 6 min", "Normal", StatusKind.NORMAL,
            LineR12, LineR12Soft, "Terminal Terrestre", "Hospital Goyeneche", 0.45f, favorite = true,
            fare = "$2.50"),
        BusLine("C4", "Catedral – Hospital", "Cada 10 min", "Desvío por obras", StatusKind.WARNING,
            LineC4, LineC4Soft, "Yanahuara", "Aeropuerto R. Ballón", 0.7f, fare = "$2.50"),
        BusLine("R7", "Universidad – Hospital", "Cada 12 min", "Normal", StatusKind.NORMAL,
            LineR7, LineR7Soft, "Av. Dolores", "Paucarpata", 0.95f, fare = "$3.00"),
        BusLine("L3", "Circular Centro", "Cada 15 min", "Retraso 5 min", StatusKind.DELAY,
            LineL3, LineL3Soft, "Cerro Colorado", "UNSA", 0.25f, fare = "$2.00"),
    )

    val nearbyStops = listOf(
        Stop("Plaza de Armas", "120 m", "2 min a pie", "R12", "3 min", LineR12, LineR12Soft),
        Stop("Mercado San Camilo", "280 m", "4 min a pie", "R7", "8 min", LineR7, LineR7Soft),
        Stop("Catedral", "650 m", "9 min a pie", "C4", "4 min", LineC4, LineC4Soft),
    )

    val companies = listOf(
        Company("HAG", "Transportes Hagemsa", "Desde 1998", "48 buses",
            "4.6", "1240 opiniones", LineR12, LineR12Soft, listOf("R12", "R20")),
        Company("SOL", "Sol de Arequipa", "Desde 2006", "26 buses",
            "4.2", "610 opiniones", LineC4, LineC4Soft, listOf("C4", "L3")),
        Company("CHA", "El Chasqui del Misti", "Desde 2011", "31 buses",
            "4.4", "845 opiniones", LineR7, LineR7Soft, listOf("R7", "X1")),
    )

    val alerts = listOf(
        Alert("C4", LineC4, LineC4Soft, "Hace 3 min", "Tráfico denso en Av. Dolores",
            "Detectamos tráfico lento entre Catedral y Hospital Goyeneche. El C4 llega con unos 4 min de retraso.",
            AlertKind.TRAFFIC),
        Alert("C4", LineC4, LineC4Soft, "Hace 12 min", "Desvío por obras en Av. Dolores",
            "El C4 no pasa por Catedral norte. Usa la parada provisional en 5ª Calle.",
            AlertKind.DETOUR),
        Alert("L3", LineL3, LineL3Soft, "Hace 25 min", "Retrasos de 5 minutos",
            "Tráfico intenso en el Centro Histórico. Considera el R12 como alternativa.",
            AlertKind.DELAY),
        Alert("R12", LineR12, LineR12Soft, "Hoy, 08:00", "Más buses en hora pico",
            "De 17:00 a 19:00 el R12 pasará cada 4 minutos.", AlertKind.INFO),
        Alert("X1", LineX1, LineX1Soft, "Ayer", "Nueva parada en Terminal Terrestre",
            "Desde el lunes el X1 se detiene frente a la Terminal Terrestre.", AlertKind.STOP),
    )

    val recentDestinations = listOf(
        DestinationRecent("Hospital Goyeneche", "Zona 1 · 2.4 km", "26 min"),
        DestinationRecent("UNSA", "Campus Norte · 3.1 km", "34 min"),
        DestinationRecent("Terminal Terrestre", "Zona 4 · 1.8 km", "19 min"),
        DestinationRecent("Mall Plaza Cayma", "Av. Dolores · 4.0 km", "41 min"),
    )

    val historyTrips = listOf(
        Trip("Casa", "Hospital Goyeneche", "08:12 – 08:38", "26 min", "$5.00",
            listOf("R12", "C4"), "Hoy"),
        Trip("Trabajo", "Casa", "18:05 – 18:31", "26 min", "$5.00",
            listOf("C4", "R12"), "Ayer"),
        Trip("Casa", "Trabajo", "07:40 – 08:04", "24 min", "$5.00",
            listOf("R12", "C4"), "Ayer"),
    )

    val family = listOf(
        FamilyMember("MA", "Mamá", "En el R7 · llega a las 10:42", "R7", LineR7, LineR7Soft),
        FamilyMember("HI", "Hijo", "Llegó a Colegio Independencia · 07:58", null, LineR20, LineR20Soft, arrived = true),
        FamilyMember("PA", "Papá", "En el C4 · tráfico denso, +4 min", "C4", LineC4, LineC4Soft),
        FamilyMember("AB", "Abuela", "En casa", null, LineR7, LineR7Soft, arrived = true),
    )

    val cities = listOf(
        City("AQP", "Arequipa", "42 líneas · 3 empresas · 610 paradas", LineR12, LineR12Soft, current = true),
    )

    val citiesSoon = listOf(
        City("CUS", "Cusco", "Llega en diciembre", Muted, ChipGrey, soon = "Llega en diciembre"),
        City("TAC", "Tacna", "Llega en enero de 2026", Muted, ChipGrey, soon = "Llega en enero de 2026"),
        City("MOL", "Mollendo", "Llega en marzo de 2026", Muted, ChipGrey, soon = "Llega en marzo de 2026"),
    )

    val intercity = listOf(
        IntercityDeparture("09:45", "Lima", "2 h 50 min", "HAG", "Andén 2", "Sin asientos", BoardingState.LEFT),
        IntercityDeparture("10:20", "Cusco", "3 h 10 min", "CHA", "Andén 5", "8 asientos libres", BoardingState.BOARDING),
        IntercityDeparture("10:30", "Lima", "2 h 40 min", "CHA", "Andén 4", "14 asientos libres", BoardingState.ON_TIME),
        IntercityDeparture("11:15", "Puno", "4 h 05 min", "SOL", "Andén 1", "22 asientos libres", BoardingState.ON_TIME),
    )
}
