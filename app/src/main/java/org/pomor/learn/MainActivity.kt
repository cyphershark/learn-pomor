/*
package org.pomor.learn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.pomor.learn.data.content.ContentRepository
import org.pomor.learn.ui.screens.home.HomeScreen
import org.pomor.learn.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = ContentRepository(applicationContext)
        setContent {
            MyApplicationTheme {
                HomeScreen(contentRepository = repository)
            }
        }
    }
} // - старая версия
*/

package org.pomor.learn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.pomor.learn.data.content.ContentRepository
import org.pomor.learn.data.words.LearnedWordsRepository
import org.pomor.learn.ui.navigation.PomorApp
import org.pomor.learn.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val contentRepository = ContentRepository(applicationContext)
        val learnedWordsRepository = LearnedWordsRepository()
        setContent {
            MyApplicationTheme {
                PomorApp(
                    contentRepository = contentRepository,
                    learnedWordsRepository = learnedWordsRepository
                )
            }
        }
    }
}