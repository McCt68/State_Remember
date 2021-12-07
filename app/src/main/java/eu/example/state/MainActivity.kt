package eu.example.state

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf


import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import eu.example.state.ui.theme.StateTheme
import kotlin.random.Random


// State describes how our current UI looks at the moment.
// Example a variable shown on screen. Its current value is its state, -
// and everytime the value is changed the state is changed.
// Redrawing the UI when a state changes is called recomposition in Jetpack Compose.

// State represents any value that can change over time !

// Composable functions can store local state in memory by using remember -
// and track changes to the value passed to mutableStateOf.
// Composables (and its children) using this state will get redrawn automatically when the value is updated.
// We call this recomposition.

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()

        }
    }
}

@Composable
fun MainScreen(){
    androidx.compose.material.Surface {
        ColorBox(Modifier.fillMaxSize())

    }
}

// Example showing an initial state of color yellow to begin with
// then change to a random color when we click on it
// an also remembers the last state it had when it recomposes
// So it dont set the state back to yellow everytime it recomposes
@Composable
fun ColorBox(modifier: Modifier = Modifier) {
    // Initial state set to yellow
    // we use remember so when its recomposed it will not set the state to its initial value everytime
    // it will set it to the last value the state had before it was recomposed
    val myColor = remember {
        mutableStateOf(Color.Yellow)
    }

    Box(modifier = modifier
        .background(color = myColor.value)
        .clickable { /* set the value of color to state of a random color */
            myColor.value = Color( /* call Color function with 3 float values and a alpha value*/
                Random.nextFloat(),
                Random.nextFloat(),
                Random.nextFloat(),
                1f
            )
        }
    )
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen()
}