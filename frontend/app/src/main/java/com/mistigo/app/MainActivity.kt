package com.mistigo.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mistigo.app.data.Dest
import com.mistigo.app.ui.components.RumboBottomBar
import com.mistigo.app.ui.components.Tab
import com.mistigo.app.ui.screens.*
import com.mistigo.app.ui.theme.Cream
import com.mistigo.app.ui.theme.RumboTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RumboTheme { RumboApp() }
        }
    }
}

@Composable
private fun Safe(fullBleed: Boolean = false, content: @Composable () -> Unit) {
    val base = Modifier.fillMaxSize().background(Cream)
    Box(if (fullBleed) base else base.systemBarsPadding()) { content() }
}

@Composable
fun RumboApp() {
    val nav = rememberNavController()
    val go: (String) -> Unit = { nav.navigate(it) }
    val back: () -> Unit = { nav.popBackStack() }

    NavHost(navController = nav, startDestination = Dest.WELCOME) {
        composable(Dest.WELCOME) {
            Safe(fullBleed = true) {
                WelcomeScreen(onStart = { nav.navigate(Dest.MAIN) }, onSkip = { nav.navigate(Dest.MAIN) })
            }
        }
        composable(Dest.MAIN) { MainScaffold(onNavigate = go) }

        composable(Dest.SEARCH) { Safe { SearchScreen(onBack = back, onPick = { go(Dest.ROUTE_OPTIONS) }) } }
        composable(Dest.ROUTE_OPTIONS) { Safe(fullBleed = true) { RouteOptionsScreenWrapped(back, go) } }
        composable(Dest.LIVE_TRIP) { Safe(fullBleed = true) { LiveTripScreen(onBack = back, onUnit = { go(Dest.UNIT_DETAIL) }) } }
        composable(Dest.LINE_LIVE) { Safe { LineLiveScreen(onBack = back, onSchedules = { go(Dest.SCHEDULES) }, onCompany = { go(Dest.COMPANY_DETAIL) }) } }
        composable(Dest.SCHEDULES) { Safe { SchedulesScreen(onBack = back) } }
        composable(Dest.COMPANIES) { Safe { CompaniesScreen(onBack = back, onOpen = { go(Dest.COMPANY_DETAIL) }) } }
        composable(Dest.COMPANY_DETAIL) { Safe { CompanyDetailScreen(onBack = back, onOpenLine = { go(Dest.LINE_LIVE) }) } }
        composable(Dest.ALERTS) { Safe { AlertsScreen(onBack = back) } }
        composable(Dest.UNIT_DETAIL) { Safe { UnitDetailScreen(onBack = back) } }
        composable(Dest.SECURITY) { Safe { SecurityScreen(onBack = back) } }
        composable(Dest.FAVORITES) { Safe { FavoritesScreen(onBack = back, onGo = { go(Dest.LIVE_TRIP) }) } }
        composable(Dest.HISTORY) { Safe { HistoryScreen(onBack = back, onPlans = { go(Dest.COMPARE_PLANS) }) } }
        composable(Dest.COMPARE_PLANS) { Safe { ComparePlansScreen(onBack = back, onTry = { go(Dest.PLAN_ACTIVATED) }) } }
        composable(Dest.MY_PLAN) { Safe { MyPlanScreen(onBack = back) } }
        composable(Dest.PLAN_ACTIVATED) { Safe(fullBleed = true) { PlanActivatedScreen(onStart = back, onSeePlan = { go(Dest.MY_PLAN) }) } }
        composable(Dest.FAMILY) { Safe { FamilyScreen(onBack = back) } }
        composable(Dest.CITIES) { Safe { CitiesScreen(onBack = back, onPick = back) } }
        composable(Dest.INTERCITY) { Safe { IntercityScreen(onBack = back) } }
        composable(Dest.WEEKLY) { Safe { WeeklyPlannerScreen(onBack = back) } }
    }
}

@Composable
private fun RouteOptionsScreenWrapped(onBack: () -> Unit, go: (String) -> Unit) {
    RouteOptionsScreen(onBack = onBack, onStart = { go(Dest.LIVE_TRIP) })
}

@Composable
private fun MainScaffold(onNavigate: (String) -> Unit) {
    var tab by remember { mutableStateOf(Tab.INICIO) }

    val openOrTab: (String) -> Unit = { route ->
        when (route) {
            Dest.HOME -> tab = Tab.INICIO
            Dest.MAP -> tab = Tab.MAPA
            Dest.LINES -> tab = Tab.LINEAS
            Dest.PLANS -> tab = Tab.PLANES
            Dest.SETTINGS -> tab = Tab.AJUSTES
            else -> onNavigate(route)
        }
    }

    Box(Modifier.fillMaxSize().background(Cream)) {
        Box(
            Modifier.fillMaxSize().statusBarsPadding().padding(bottom = 96.dp),
        ) {
            when (tab) {
                Tab.INICIO -> HomeScreen(onOpen = openOrTab)
                Tab.MAPA -> MapScreen(onBack = { tab = Tab.INICIO }, onOpenTrip = { onNavigate(Dest.LIVE_TRIP) })
                Tab.LINEAS -> LinesScreen(onOpenLine = { onNavigate(Dest.LINE_LIVE) })
                Tab.PLANES -> PlansScreen(onCompare = { onNavigate(Dest.COMPARE_PLANS) }, onTry = { onNavigate(Dest.PLAN_ACTIVATED) })
                Tab.AJUSTES -> SettingsScreen(onOpenFamily = { onNavigate(Dest.FAMILY) }, onOpenPlan = { onNavigate(Dest.MY_PLAN) })
            }
        }
        RumboBottomBar(
            current = tab,
            onSelect = { tab = it },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(horizontal = 16.dp, vertical = 12.dp),
        )
    }
}
