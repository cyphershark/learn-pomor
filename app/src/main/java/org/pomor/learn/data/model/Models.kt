package org.pomor.learn.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocalizedString(
    val ru: String? = null,
    val pomor: String? = null,
    val en: String? = null,
    val de: String? = null
)

@Serializable
data class Example(
    val pomor: String,
    val ru: String
)

@Serializable
data class Word(
    val id: String,
    val pomor: String,
    val ru: String,
    val note_ru: String? = null,
    val example: Example? = null,
    val tags: List<String> = emptyList()
)

@Serializable
sealed class Exercise {
    abstract val instruction: LocalizedString?

    @Serializable
    @SerialName("fill_blank")
    data class FillBlank(
        override val instruction: LocalizedString? = null,
        val sentence_pomor: String,
        val correct: String,
        val distractors: List<String>
    ) : Exercise()

    @Serializable
    @SerialName("find_rusism")
    data class FindRusism(
        override val instruction: LocalizedString? = null,
        val sentence_pomor: List<String>,
        val rusism_index: Int,
        val correct: String,
        val distractors: List<String>
    ) : Exercise()

    @Serializable
    @SerialName("phonetic_rule")
    data class PhoneticRule(
        override val instruction: LocalizedString? = null,
        val ru_word: String,
        val rule_hint: LocalizedString? = null,
        val correct: String,
        val distractors: List<String>
    ) : Exercise()
}

@Serializable
data class Lesson(
    val id: String,
    val title: LocalizedString,
    val description: LocalizedString? = null,
    val grammar_note: LocalizedString? = null,
    val words: List<Word>,
    val exercises: List<Exercise>
)
