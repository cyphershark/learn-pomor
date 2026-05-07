package org.pomor.learn.data.words

/**
 * Слово, появившееся в одном из пройденных уроков.
 *
 * @param pomor Слово или фраза на поморской говоре.
 * @param ru Перевод на русский.
 * @param firstSeenLessonNumber Номер урока, в котором слово впервые появилось.
 *        Используется для отображения метки «У1», «У2» и т.д. на карточке.
 */
data class LearnedWord(
    val pomor: String,
    val ru: String,
    val firstSeenLessonNumber: Int
)

/**
 * Хранилище списка изученных слов.
 *
 * Текущая реализация — в памяти, без сохранения между запусками. Когда будет
 * добавлен Room (вместе с трекингом прогресса по урокам), эта реализация будет
 * заменена на читающую из БД, а интерфейс не изменится.
 *
 * Чтобы протестировать экран словаря с данными, раскомментируйте строки в _words ниже.
 */
class LearnedWordsRepository {

    private val _words: MutableList<LearnedWord> = mutableListOf(
        // Тестовые данные — раскомментируйте, чтобы увидеть карточки в словаре:
        // LearnedWord(pomor = "цяй",       ru = "чай",      firstSeenLessonNumber = 1),
        // LearnedWord(pomor = "пець",      ru = "печь",     firstSeenLessonNumber = 1),
        // LearnedWord(pomor = "ноць",      ru = "ночь",     firstSeenLessonNumber = 1),
        // LearnedWord(pomor = "доцка",     ru = "дочка",    firstSeenLessonNumber = 1),
        // LearnedWord(pomor = "ешшо",      ru = "ещё",      firstSeenLessonNumber = 2),
        // LearnedWord(pomor = "шшука",     ru = "щука",     firstSeenLessonNumber = 2),
        // LearnedWord(pomor = "товаришш",  ru = "товарищ",  firstSeenLessonNumber = 2),
    )

    /** Возвращает слова в порядке их первого появления (т.е. в порядке добавления). */
    fun getLearnedWords(): List<LearnedWord> = _words.toList()

    /** Добавляет слово, если его ещё нет в списке. */
    fun addWord(word: LearnedWord) {
        if (_words.none { it.pomor == word.pomor }) {
            _words.add(word)
        }
    }
}