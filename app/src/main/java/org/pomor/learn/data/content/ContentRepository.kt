package org.pomor.learn.data.content

import android.content.Context
import kotlinx.serialization.json.Json
import org.pomor.learn.data.model.Lesson

class ContentRepository(private val context: Context) {

    private val json = Json {
        classDiscriminator = "type"
        ignoreUnknownKeys = true
    }

    fun loadLesson(assetPath: String): Lesson {
        val text = context.assets.open(assetPath).bufferedReader().use { it.readText() }
        return json.decodeFromString<Lesson>(text)
    }

    fun loadIntroLessons(): List<Lesson> {
        val basePath = "content/tier_1/intro"
        val files = context.assets.list(basePath) ?: emptyArray()
        return files
            .filter { it.endsWith(".json") }
            .sorted()
            .map { loadLesson("$basePath/$it") }
    }
}
