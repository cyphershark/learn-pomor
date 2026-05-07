package org.pomor.learn.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.pomor.learn.data.content.ContentRepository
import org.pomor.learn.data.words.LearnedWordsRepository
import org.pomor.learn.ui.screens.dictionary.DictionaryScreen
import org.pomor.learn.ui.screens.home.HomeScreen
import org.pomor.learn.ui.screens.settings.SettingsScreen

/**
 * Три основных раздела приложения, доступных через нижнюю навигацию.
 *
 * Чтобы заменить иконки на свои:
 * 1. Положите файлы своих иконок в app/src/main/res/drawable/ как векторные XML
 *    (Android Studio: правый клик на drawable → New → Vector Asset).
 *    Назовите их, например: ic_lessons.xml, ic_dictionary.xml, ic_settings.xml.
 * 2. Замените `icon = Icons.Default.X` на `iconRes = R.drawable.ic_X`,
 *    и в NavigationBarItem используйте Icon(painter = painterResource(iconRes)).
 *
 * Сейчас используются стандартные Material-иконки как заглушки.
 */
sealed class PomorDestination(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    data object Lessons : PomorDestination("lessons", "Уроки", Icons.Default.PlayArrow)
    data object Dictionary : PomorDestination("org/pomor/learn/ui/screens/dictionary", "Словарь", Icons.AutoMirrored.Filled.List)
    data object Settings : PomorDestination("settings", "Настройки", Icons.Default.Settings)
}

private val destinations = listOf(
    PomorDestination.Lessons,
    PomorDestination.Dictionary,
    PomorDestination.Settings
)

@Composable
fun PomorApp(
    contentRepository: ContentRepository,
    learnedWordsRepository: LearnedWordsRepository
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { PomorBottomNav(navController = navController) }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = PomorDestination.Lessons.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(PomorDestination.Lessons.route) {
                HomeScreen(contentRepository = contentRepository)
            }
            composable(PomorDestination.Dictionary.route) {
                DictionaryScreen(learnedWordsRepository = learnedWordsRepository)
            }
            composable(PomorDestination.Settings.route) {
                SettingsScreen()
            }
        }
    }
}

@Composable
private fun PomorBottomNav(navController: NavHostController) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    NavigationBar {
        destinations.forEach { destination ->
            NavigationBarItem(
                selected = currentRoute == destination.route,
                onClick = {
                    if (currentRoute != destination.route) {
                        navController.navigate(destination.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = destination.label
                    )
                },
                label = { Text(destination.label) }
            )
        }
    }
}