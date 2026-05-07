/*
package org.pomor.learn.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.pomor.learn.data.content.ContentRepository
import org.pomor.learn.data.model.Lesson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(contentRepository: ContentRepository) {
    var lessons by remember { mutableStateOf<List<Lesson>>(emptyList()) }
    var error by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {
            lessons = contentRepository.loadIntroLessons()
        } catch (e: Exception) {
            error = e.message ?: "Неизвестная ошибка"
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Поморьска говоря") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            error?.let {
                Text(
                    text = "Ошибка: $it",
                    color = MaterialTheme.colorScheme.error
                )
                Spacer(Modifier.height(16.dp))
            }

            Text(
                text = "Ярус 1 — Основы",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "Введение · найдено ${lessons.size} уроков",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(Modifier.height(16.dp))

            LazyColumn {
                items(lessons) { lesson ->
                    LessonCard(lesson)
                    Spacer(Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
private fun LessonCard(lesson: Lesson) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp)) {
            lesson.title.ru?.let {
                Text(text = it, style = MaterialTheme.typography.titleMedium)
            }
            lesson.title.pomor?.let {
                Text(text = it, style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(Modifier.height(4.dp))
            Text(
                text = "${lesson.words.size} слов · ${lesson.exercises.size} упражнений",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}*/

package org.pomor.learn.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.pomor.learn.data.content.ContentRepository
import org.pomor.learn.data.model.Lesson

@Composable
fun HomeScreen(contentRepository: ContentRepository) {
    var lessons by remember { mutableStateOf<List<Lesson>>(emptyList()) }
    var error by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {
            lessons = contentRepository.loadIntroLessons()
        } catch (e: Exception) {
            error = e.message ?: "Неизвестная ошибка"
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Поморьска говоря",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(16.dp))

        error?.let {
            Text(
                text = "Ошибка: $it",
                color = MaterialTheme.colorScheme.error
            )
            Spacer(Modifier.height(16.dp))
        }

        Text(
            text = "Ярус 1 — Основы",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = "Введение · найдено ${lessons.size} уроков",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.height(16.dp))

        LazyColumn {
            items(lessons) { lesson ->
                LessonCard(lesson)
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}

@Composable
private fun LessonCard(lesson: Lesson) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp)) {
            lesson.title.ru?.let {
                Text(text = it, style = MaterialTheme.typography.titleMedium)
            }
            lesson.title.pomor?.let {
                Text(text = it, style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(Modifier.height(4.dp))
            Text(
                text = "${lesson.words.size} слов · ${lesson.exercises.size} упражнений",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
