        package com.example.guessthecountryapp1

        import android.os.Bundle
        import androidx.activity.ComponentActivity
        import androidx.activity.compose.setContent
        import androidx.compose.foundation.layout.Column
        import androidx.compose.foundation.layout.fillMaxSize
        import androidx.compose.foundation.layout.padding
        import androidx.compose.foundation.layout.size
        import androidx.compose.material3.Button
        import androidx.compose.material3.MaterialTheme
        import androidx.compose.material3.Surface
        import androidx.compose.material3.Text
        import androidx.compose.runtime.Composable
        import androidx.compose.ui.Modifier
        import androidx.compose.ui.unit.dp
        import com.example.guessthecountryapp1.ui.theme.GuessTheCountryApp1Theme
        import androidx.compose.foundation.Image
        import androidx.compose.foundation.background
        import androidx.compose.foundation.border
        import androidx.compose.foundation.clickable
        import androidx.compose.foundation.layout.Arrangement
        import androidx.compose.foundation.layout.Box
        import androidx.compose.foundation.layout.fillMaxWidth
        import androidx.compose.foundation.layout.height
        import androidx.compose.foundation.lazy.LazyColumn
        import androidx.compose.foundation.lazy.items
        import androidx.compose.foundation.lazy.itemsIndexed
        import androidx.compose.material3.TextField
        import androidx.compose.runtime.getValue
        import androidx.compose.runtime.mutableStateListOf
        import androidx.compose.runtime.mutableStateOf
        import androidx.compose.runtime.remember
        import androidx.compose.runtime.setValue
        import androidx.compose.ui.Alignment
        import androidx.compose.ui.graphics.Color
        import androidx.compose.ui.res.painterResource
        import androidx.compose.ui.text.TextStyle
        import androidx.compose.ui.text.buildAnnotatedString
        import androidx.compose.ui.text.font.FontWeight
        import androidx.compose.ui.unit.sp
        import androidx.navigation.NavHostController
        import androidx.navigation.compose.NavHost
        import androidx.navigation.compose.composable
        import androidx.navigation.compose.rememberNavController
        import kotlin.random.Random
        import java.util.*

        class MainActivity : ComponentActivity() {
            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContent {
                    GuessTheCountryApp1Theme {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            //sends you to the my app function which is the navigation function
                            MyApp()
                        }
                    }
                }
            }

            //This is the main home area which has 4 button that take you to different areas of the app
            @Composable
            fun MainHome(navController: NavHostController) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)

                ) {
                    //When i click any of these buttons they will find the same "code" from MyApp
                    //And take you to the function connected to it
                    //sends me to Guess The Country Game
                    Button(
                        onClick = {
                            navController.navigate("guess_the_country")
                        },
                        modifier = Modifier
                            .size(200.dp, 80.dp)
                            .padding(5.dp)
                    ) {
                        Text("Guess The Country")
                    }
                    //sends me to the the guess hints game
                    Button(
                        onClick = { navController.navigate("guess_hints") },
                        modifier = Modifier
                            .size(200.dp, 80.dp)
                            .padding(5.dp)
                    ) {
                        Text("Guess Hints")
                    }
                    //sends me to the guess the flag game
                    Button(
                        onClick = { navController.navigate("guess_the_flag") },
                        modifier = Modifier
                            .size(200.dp, 80.dp)
                            .padding(5.dp)
                    ) {
                        Text("Guess The Flag")
                    }
                    //sends me to the advanced level game
                    Button(
                        onClick = { navController.navigate("advanced_level") },
                        modifier = Modifier
                            .size(200.dp, 80.dp)
                            .padding(5.dp)
                    ) {
                        Text("Advanced Level")
                    }
                }

            }

            @Composable
            fun MyApp() {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "main_home") {
                    //When the back button is pressed this is called and sends you back to the main home page
                    composable("main_home") {
                        MainHome(navController)
                    }
                    //When the guess the country button is pressed this is called and sends you to the Function for the game
                    composable("guess_the_country") {
                        GuessTheCountryGame(navController)
                    }
                    //When the guess hints button is pressed this is called and sends you to the Function for the game
                    composable("guess_hints") {
                        GuessHints(navController)
                    }
                    //When the guess the flag button is pressed this is called and sends you to the Function for the game
                    composable("guess_the_flag") {
                        GuessTheFlag(navController)
                    }
                    //When the advanced level button is pressed this is called and sends you to the Function for the game
                    composable("advanced_level") {
                        AdvancedLevel(navController)
                    }
                }
            }


            @Composable
            fun GuessTheCountryGame(navController: NavHostController) {

                //list of country names
                val listofcountries = listOf(
                    "Argentina",
                    "Bangladesh",
                    "Belguim",
                    "Brazil",
                    "Canada",
                    "Chile",
                    "Colombia",
                    "Costa Rica",
                    "Cyprus",
                    "Denmark",
                    "Ecuador",
                    "Eygypt",
                    "Spain",
                    "Finland",
                    "France",
                    "Great Britain",
                    "Hong Kong",
                    "Hungary",
                    "Iceland",
                    "India",
                    "Indonesia",
                    "Iran",
                    "Iraq",
                    "Ireland",
                    "Italy",
                    "Jamaica",
                    "Japan",
                    "Jordan",
                    "Kazakhstan",
                    "Kenya",
                    "Kuwait",
                    "Latvia",
                    "Lebanon",
                    "Luxembourg",
                    "Madagascar",
                    "Malaysia",
                    "Mexico",
                    "Morocco",
                )

                //state that help keep track of the currrent round
                var randomcountry by remember { mutableStateOf(Random.nextInt(listofcountries.size)) }
                val selectedcountry = remember { mutableStateOf("") }
                val correctcountry = remember { mutableStateOf(listofcountries[randomcountry]) }
                val submittedanswer = remember { mutableStateOf(false) }

                // Mapping each country name to a flag from the drawable folder
                val flags = remember {
                    when (randomcountry) {
                        0 -> R.drawable.ar
                        1 -> R.drawable.bd
                        2 -> R.drawable.be
                        3 -> R.drawable.br
                        4 -> R.drawable.ca
                        5 -> R.drawable.cl
                        6 -> R.drawable.co
                        7 -> R.drawable.cr
                        8 -> R.drawable.cy
                        9 -> R.drawable.dk
                        10 -> R.drawable.ec
                        11 -> R.drawable.eg
                        12 -> R.drawable.es
                        13 -> R.drawable.fi
                        14 -> R.drawable.fr
                        15 -> R.drawable.gb
                        16 -> R.drawable.hk
                        17 -> R.drawable.hu
                        18 -> R.drawable.`is`
                        19 -> R.drawable.`in`
                        20 -> R.drawable.id
                        21 -> R.drawable.ir
                        22 -> R.drawable.iq
                        23 -> R.drawable.ie
                        24 -> R.drawable.it
                        25 -> R.drawable.jm
                        26 -> R.drawable.jp
                        27 -> R.drawable.jo
                        28 -> R.drawable.kz
                        29 -> R.drawable.ke
                        30 -> R.drawable.kw
                        31 -> R.drawable.la
                        32 -> R.drawable.lb
                        33 -> R.drawable.lu
                        34 -> R.drawable.mg
                        35 -> R.drawable.my
                        36 -> R.drawable.mx
                        37 -> R.drawable.mo
                        else -> R.drawable.us
                    }
                }

                //contains all images and buttons etc
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.TopStart)
                            .padding(10.dp)
                    ) {
                        //image is displayed based on the randomly selected country
                        Image(
                            painterResource(id = flags),
                            contentDescription = "flags",
                            modifier = Modifier.size(200.dp, 100.dp)
                        )
                        //displays the countries and are made clickable
                        LazyColumn() {
                            items(listofcountries) { country ->
                                val clickedcountry = country == selectedcountry.value
                                Text(
                                    text = country,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(5.dp)
                                        .clickable {
                                            if (!submittedanswer.value) {
                                                selectedcountry.value = country.toString()
                                            }
                                        }
                                        //if i click on a country the background turns light grey
                                        .background(if (clickedcountry) Color.LightGray else Color.Transparent)
                                )
                            }
                        }
                    }

                    //button to navigate to the next round and submit answer too
                    Button(
                        onClick = {
                            //if they have submitted answer pressing next will restart game
                            if (submittedanswer.value) {
                                navController.navigate("guess_the_country")
                                submittedanswer.value = false

                                //Will go to else if answer has just been submitted
                            } else {
                                submittedanswer.value = true
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(5.dp)
                    ) {
                        Text("Next")
                    }

                    //button sends you back to home page
                    Button(
                        onClick = {
                            navController.navigate("main_home")
                        },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(5.dp)
                    ) {
                        Text("Back")
                    }

                    //displays after i press submit if i got it right or wrong
                    if (submittedanswer.value) {
                        val feedbackText = if (selectedcountry.value == correctcountry.value) {
                            "CORRECT!"
                        } else {
                            "WRONG! Correct Country: ${correctcountry.value}"
                        }
                        Text(
                            //mofifiers to make the text green or red depending if i got the answer right or wrong
                            text = feedbackText,
                            color = if (selectedcountry.value == correctcountry.value) Color.Green else Color.Red,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(16.dp)
                        )
                    }
                }

            }

            @Composable
            fun GuessHints(navController: NavHostController) {
                //map of country names and their corresponding flag thats accessed throught the drawable folder
                val listofcountryandflags = mapOf(
                    "Argentina" to R.drawable.ar,
                    "Bangladesh" to R.drawable.bd,
                    "Belgium" to R.drawable.be,
                    "Brazil" to R.drawable.br,
                    "Canada" to R.drawable.ca,
                    "Chile" to R.drawable.cl,
                    "Colombia" to R.drawable.co,
                    "Costa Rica" to R.drawable.cr,
                    "Cyprus" to R.drawable.cy,
                    "Denmark" to R.drawable.dk,
                    "Ecuador" to R.drawable.ec,
                    "Egypt" to R.drawable.eg,
                    "Spain" to R.drawable.es,
                    "Finland" to R.drawable.fi,
                    "France" to R.drawable.fr,
                    "Great Britain" to R.drawable.gb,
                    "Hong Kong" to R.drawable.hk,
                    "Hungary" to R.drawable.hu,
                    "Iceland" to R.drawable.`is`,
                    "India" to R.drawable.`in`,
                    "Indonesia" to R.drawable.id,
                    "Iran" to R.drawable.ir,
                    "Iraq" to R.drawable.iq,
                    "Ireland" to R.drawable.ie,
                    "Italy" to R.drawable.it,
                    "Jamaica" to R.drawable.jm,
                    "Japan" to R.drawable.jp,
                    "Jordan" to R.drawable.jo,
                    "Kazakhstan" to R.drawable.kz,
                    "Kenya" to R.drawable.ke,
                    "Kuwait" to R.drawable.kw,
                    "Latvia" to R.drawable.la,
                    "Lebanon" to R.drawable.lb,
                    "Luxembourg" to R.drawable.lu,
                    "Madagascar" to R.drawable.mg,
                    "Malaysia" to R.drawable.my,
                    "Mexico" to R.drawable.mx,
                    "Morocco" to R.drawable.mo
                )

                //state that help keep track of the currrent round
                //randomly select a country and its flag from the map
                val randomchosenindex = remember { Random.nextInt(listofcountryandflags.size) }
                val randomisedcountry = listofcountryandflags.keys.toList()[randomchosenindex]
                val randomflagimage = listofcountryandflags[randomisedcountry] ?: R.drawable.us


                val guessedletters = remember { mutableStateOf("") }
                val displayedletters = remember { mutableStateOf("") }
                var wrongguesses by remember { mutableStateOf(0) }
                var rightorwrongmessage by remember { mutableStateOf("") }
                var nextbutton by remember { mutableStateOf(false) }

                //the coulmn holds all images and buttons etc
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    //random image is displayed
                    Image(
                        painter = painterResource(id = randomflagimage),
                        contentDescription = "Flag",
                        modifier = Modifier
                            .size(200.dp)
                            .padding(bottom = 16.dp)
                    )

                    //displays country name but with - instead
                    Text(
                        text = buildAnnotatedString {
                            randomisedcountry.forEachIndexed { index, char ->
                                //if there is a no letter they add a space but if there is a letter they add a -
                                if (char.isWhitespace()) {
                                    append("  ")
                                } else {
                                    append(
                                        if (displayedletters.value.contains(
                                                char,
                                                ignoreCase = true
                                            )
                                        ) char else '-'
                                    )
                                    append(" ")
                                }
                            }
                        },
                        fontSize = 20.sp,
                        modifier = Modifier.padding(bottom = 14.dp)
                    )

                    //user can input its guessed characters
                    TextField(
                        value = guessedletters.value,
                        onValueChange = { text ->
                            //once i type one letter the textfield will automatically stop you from typing another letter
                            if (text.length <= 1) {
                                guessedletters.value = text
                            }
                        },
                        label = { Text("Type a character") },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp)
                    )

                    //button for submitting and next round
                    Button(
                        onClick = {
                            //if nextbutton is shown and is clicked a new game starts
                            if (nextbutton) {
                                navController.navigate("guess_hints")
                                nextbutton = false
                            } else {
                                //get the guessed letter
                                val guessedcharacter = guessedletters.value.lowercase(Locale.getDefault())
                                    .firstOrNull()
                                //if its not null and is contained in the random country
                                if (guessedcharacter != null && randomisedcountry.contains(
                                        guessedcharacter,
                                        ignoreCase = true
                                    )
                                ) {
                                    //goes through the characters of the country
                                    val lettersinputed = buildString {
                                        randomisedcountry.forEach { c ->
                                            append(
                                                //if the character matches the letter then they will add the letter there
                                                if (displayedletters.value.contains(
                                                        c,
                                                        ignoreCase = true
                                                    )
                                                    || c.equals(guessedcharacter, true)
                                                    //if the letter doesnt match none of the character then they keep the -
                                                ) c else '-'
                                            )
                                        }
                                    }
                                    //updates the letters on the screen and clears the guessedletters so we can add a new letter
                                    displayedletters.value = lettersinputed
                                    guessedletters.value = ""
                                    //if there are no more dashes you win if there are still dashes and you three wrong guesses you lose
                                    if (!displayedletters.value.contains('-')) {
                                        rightorwrongmessage = "CORRECT!"
                                        nextbutton = true
                                    }
                                } else {
                                    wrongguesses++
                                    if (wrongguesses >= 3) {
                                        rightorwrongmessage =
                                            "WRONG! The correct country is $randomisedcountry"
                                        nextbutton = true
                                    }
                                }
                            }
                        },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text(if (nextbutton) "Next" else "Submit")
                    }

                    //back button that takes you back to the main page
                    Button(
                        onClick = {
                            navController.navigate("main_home")
                        },
                        modifier = Modifier
                            .align(Alignment.Start)

                    ) {
                        Text("Back")
                    }

                    //if the state has been changed and is no longer empty the text for rightorwrong will be modified
                    if (rightorwrongmessage.isNotEmpty()) {
                        Text(
                            text = rightorwrongmessage,
                            //if i have three wrong guesses the text will change to red but if not it will be green
                            color = if (wrongguesses >= 3) Color.Red else Color.Green,
                            modifier = Modifier.padding(top = 16.dp)
                        )
                    }
                }

            }

            @Composable
            fun GuessTheFlag(navController: NavHostController) {
                //map of country names and their corresponding flag thats accessed throught the drawable folder
                val listofcountryandflags = mapOf(
                    "Argentina" to R.drawable.ar,
                    "Bangladesh" to R.drawable.bd,
                    "Belgium" to R.drawable.be,
                    "Brazil" to R.drawable.br,
                    "Canada" to R.drawable.ca,
                    "Chile" to R.drawable.cl,
                    "Colombia" to R.drawable.co,
                    "Costa Rica" to R.drawable.cr,
                    "Cyprus" to R.drawable.cy,
                    "Denmark" to R.drawable.dk,
                    "Ecuador" to R.drawable.ec,
                    "Egypt" to R.drawable.eg,
                    "Spain" to R.drawable.es,
                    "Finland" to R.drawable.fi,
                    "France" to R.drawable.fr,
                    "Great Britain" to R.drawable.gb,
                    "Hong Kong" to R.drawable.hk,
                    "Hungary" to R.drawable.hu,
                    "Iceland" to R.drawable.`is`,
                    "India" to R.drawable.`in`,
                    "Indonesia" to R.drawable.id,
                    "Iran" to R.drawable.ir,
                    "Iraq" to R.drawable.iq,
                    "Ireland" to R.drawable.ie,
                    "Italy" to R.drawable.it,
                    "Jamaica" to R.drawable.jm,
                    "Japan" to R.drawable.jp,
                    "Jordan" to R.drawable.jo,
                    "Kazakhstan" to R.drawable.kz,
                    "Kenya" to R.drawable.ke,
                    "Kuwait" to R.drawable.kw,
                    "Latvia" to R.drawable.la,
                    "Lebanon" to R.drawable.lb,
                    "Luxembourg" to R.drawable.lu,
                    "Madagascar" to R.drawable.mg,
                    "Malaysia" to R.drawable.my,
                    "Mexico" to R.drawable.mx,
                    "Morocco" to R.drawable.mo
                )

                //state that help keep track of the currrent round
                var randomisedcountries by remember { mutableStateOf(emptyList<String>()) }
                var flags by remember { mutableStateOf(emptyList<Int>()) }
                var rightcountry by remember { mutableStateOf("") }
                var correctornot by remember { mutableStateOf(false) }
                var selectedflag by remember { mutableStateOf<Int?>(null) }
                var nextbutton by remember { mutableStateOf(false) }
                var answer by remember { mutableStateOf("") }

                //function that is called when a new round starts which sets back each state back to default state
                fun nextround() {
                    randomisedcountries = listofcountryandflags.keys.shuffled().take(3)
                    flags = randomisedcountries.map { listofcountryandflags[it] ?: R.drawable.us }
                    rightcountry = randomisedcountries.random()
                    selectedflag = null
                    correctornot = false
                    nextbutton = false
                    answer = ""
                }

                //starts game if there is no game in progress
                if (randomisedcountries.isEmpty()) {
                    nextround()
                }

                //column to arrange images and buttons etc
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    //displays the flags and the lazycolmn organises it
                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        itemsIndexed(flags) { index, flag ->
                            Image(
                                painter = painterResource(id = flag),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(200.dp)
                                    //makes images clickable
                                    .clickable {
                                        //if the next button is not enabled
                                        if (!nextbutton) {
                                            //depending on if its right or not after i press image is what text will appear
                                            if (randomisedcountries[index] == rightcountry) {
                                                correctornot = true
                                                answer = "CORRECT!"
                                            } else {
                                                correctornot = false
                                                answer = "WRONG!"
                                            }
                                            //if selected flag = index the image i press on will have a border
                                            selectedflag = index
                                            //next button is shown after ive clicked on image
                                            nextbutton = true
                                        }
                                    }
                                    //handles the border if selected == index it will turn yellow
                                    .border(
                                        width = 4.dp,
                                        color = if (selectedflag == index) Color.Yellow else Color.Transparent
                                    )
                            )
                        }
                    }

                    //shows the text that tells you what country does the flag belong to with the country name
                    Text(
                        text = "Which country does this flag belong to? ${rightcountry}",
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    //if its true it will turn green for the text
                    Text(
                        text = answer,
                        color = if (correctornot) Color.Green else Color.Red,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    //next button is true it will be shown on screen
                    // and when clicked it will call the next round fundtion
                    if (nextbutton) {
                        Button(
                            onClick = {
                                nextround()
                            },
                            modifier = Modifier.padding(bottom = 16.dp)
                        ) {
                            Text("Next")
                        }
                    }

                    //button that when pressed will take you back the main screen
                    Button(
                        onClick = {
                            navController.navigate("main_home")
                        },
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(5.dp)
                    ) {
                        Text("Back")
                    }
                }
            }

            @Composable
            fun AdvancedLevel(navController: NavHostController) {
                //map of country names and their corresponding flag thats accessed throught the drawable folder
                val listofcountryandflags = mapOf(
                    "Argentina" to R.drawable.ar,
                    "Bangladesh" to R.drawable.bd,
                    "Belgium" to R.drawable.be,
                    "Brazil" to R.drawable.br,
                    "Canada" to R.drawable.ca,
                    "Chile" to R.drawable.cl,
                    "Colombia" to R.drawable.co,
                    "Costa Rica" to R.drawable.cr,
                    "Cyprus" to R.drawable.cy,
                    "Denmark" to R.drawable.dk,
                    "Ecuador" to R.drawable.ec,
                    "Egypt" to R.drawable.eg,
                    "Spain" to R.drawable.es,
                    "Finland" to R.drawable.fi,
                    "France" to R.drawable.fr,
                    "Great Britain" to R.drawable.gb,
                    "Hong Kong" to R.drawable.hk,
                    "Hungary" to R.drawable.hu,
                    "Iceland" to R.drawable.`is`,
                    "India" to R.drawable.`in`,
                    "Indonesia" to R.drawable.id,
                    "Iran" to R.drawable.ir,
                    "Iraq" to R.drawable.iq,
                    "Ireland" to R.drawable.ie,
                    "Italy" to R.drawable.it,
                    "Jamaica" to R.drawable.jm,
                    "Japan" to R.drawable.jp,
                    "Jordan" to R.drawable.jo,
                    "Kazakhstan" to R.drawable.kz,
                    "Kenya" to R.drawable.ke,
                    "Kuwait" to R.drawable.kw,
                    "Latvia" to R.drawable.la,
                    "Lebanon" to R.drawable.lb,
                    "Luxembourg" to R.drawable.lu,
                    "Madagascar" to R.drawable.mg,
                    "Malaysia" to R.drawable.my,
                    "Mexico" to R.drawable.mx,
                    "Morocco" to R.drawable.mo)

                //state that help keep track of the currrent round
                var randomisedflags by remember { mutableStateOf(listofcountryandflags.entries.shuffled().take(3)) }
                val answerssubmitted = remember { mutableStateOf(mutableListOf("", "", "")) }
                val editabletext = remember { mutableStateListOf(true, true, true) }
                val submitbuttonclicked = remember { mutableStateOf(false) }
                var wrongguesses = remember { mutableStateOf(0) }
                var score = remember { mutableStateOf(0) }

                //column to arrange images and buttons etc
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    //displays the score of the user
                    Text(
                        text = "Score: ${score.value}",
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(end = 5.dp, top = 5.dp),
                        fontWeight = FontWeight.Bold
                    )

                    //iterate through each randomly selected flag and country name
                    randomisedflags.forEachIndexed { index, (country, flagResId) ->
                        var answer by remember { mutableStateOf(answerssubmitted.value[index]) }

                        //displays the flag image
                        Image(
                            painter = painterResource(id = flagResId),
                            contentDescription = country,
                            modifier = Modifier.size(120.dp)
                        )

                        //textfield to enter the country name
                        TextField(
                            value = answer,
                            onValueChange = { newsubmittedanswer ->
                                //updates the current answers with new answers
                                answerssubmitted.value[index] = newsubmittedanswer
                                answer = newsubmittedanswer
                                //return if the submit button has not been clicked
                                if (!submitbuttonclicked.value) return@TextField
                            },
                            //error indication if submitted answer is not correct
                            isError = wrongguesses.value > 0 && answerssubmitted.value[index] != country,
                            label = { Text("Enter country name") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .background(
                                    //background changes when
                                    when {
                                        //if submitted answer is correct
                                        answerssubmitted.value[index] == country -> Color.Green
                                        //if the field text becomes non editable
                                        !editabletext[index] -> Color.Green
                                        //if the guess was wrong or the submitted answer was wrong too
                                        wrongguesses.value > 0 && answerssubmitted.value[index] != country -> Color.Red
                                        else -> Color.White
                                    }
                                )
                                //if the textfield and the submitted answer is correct then background changed to green
                                .then(if (editabletext[index] && answerssubmitted.value[index] == country)
                                    Modifier.background(Color.Green)
                                else Modifier),
                            enabled = editabletext[index],
                            textStyle = TextStyle(
                                //Changes the color of text depending if you right or wrong
                                color = if (!editabletext[index]
                                && answerssubmitted.value[index] == country)
                                Color.Green
                                else if (wrongguesses.value > 0
                                && answerssubmitted.value[index] != country)
                                Color.Red
                                else Color.Black)
                        )

                        //if wrong guesses is equal to 3 or more and teh submitted answer is not correct
                        //then text with the right answers will show up
                        if (wrongguesses.value >= 3 && answerssubmitted.value[index] != country) {
                            Text(
                                text = "Correct answer: $country",
                                color = Color.Blue,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }

                    //submit and next button
                    Button(
                        onClick = {
                            //checks if the submitted answer is right
                            randomisedflags.forEachIndexed { index, (country, _) ->
                                //if correct the score is incremented and the text is no longer editable
                                if (answerssubmitted.value[index] == country && editabletext[index]) {
                                    editabletext[index] = false
                                    score.value++
                                }
                            }

                            //if the guess is equal to 3 then the game is reset to start a new round
                            if (wrongguesses.value >= 3) {
                                answerssubmitted.value = mutableListOf("", "", "")
                                for (i in editabletext.indices) {
                                    editabletext[i] = true
                                }
                                submitbuttonclicked.value = false
                                wrongguesses.value = 0
                                randomisedflags = listofcountryandflags.entries.shuffled().take(3)
                            } else {
                                //if the wrong guess has not been reached then its incremented instead
                                wrongguesses.value++
                            }
                            submitbuttonclicked.value = true
                        },
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text(if (wrongguesses.value >= 3) "Next" else "Submit")
                    }

                    //back button that sends you back to the main page
                    Button(
                        onClick = {
                            navController.navigate("main_home")
                        },
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(5.dp)
                    ) {
                        Text("Back")
                    }
                }
            }}

