package com.example.arduino_led_app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Animation
import androidx.compose.material.icons.filled.ColorLens
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Animation
import androidx.compose.material.icons.outlined.ColorLens
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.arduino_led_app.ui.composables.BottomNavigationItem
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.arduino_led_app.ui.composables.ClearButton
import com.example.arduino_led_app.ui.composables.CustomHeader
import com.example.arduino_led_app.ui.composables.SendButton
import com.example.arduino_led_app.ui.theme.Orange
import com.example.arduino_led_app.utils.command.CommandBuilder
import com.example.arduino_led_app.utils.command.FunctionValue
import kotlin.math.absoluteValue


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, onFABClicked: (String) -> Unit) {

    val items = listOf(
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
        ),
        BottomNavigationItem(
            title = "Color",
            selectedIcon = Icons.Filled.ColorLens,
            unselectedIcon = Icons.Outlined.ColorLens,
        ),
        BottomNavigationItem(
            title = "Rainbow",
            selectedIcon = Icons.Filled.Animation,
            unselectedIcon = Icons.Outlined.Animation,
        ),
        BottomNavigationItem(
            title = "Settings",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
        ),
    )
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    val command = remember {
        mutableStateOf("")
    }

    fun onCommandChange(newCommand : String)
    {
        command.value = newCommand
    }

    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            floatingActionButton = { if (selectedItemIndex.absoluteValue != 0) SendButton {
                if(command.value.isNotEmpty())
                {
                    onFABClicked(
                        command.value
                    )
                }

            }
            else{
                ClearButton{
                    onFABClicked(
                        buildClearCommand()
                    )

                }
            }
            },
            floatingActionButtonPosition = FabPosition.End,
            bottomBar = {
                NavigationBar(containerColor = Orange) {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color.Black,
                                selectedTextColor = Color.Black,
                                indicatorColor = Color.White,
                                unselectedIconColor = Color.Black,
                            )
                            ,
                            selected = selectedItemIndex == index,
                            onClick = {
                                selectedItemIndex = index
                            },
                            label = {
                                Text(text = item.title)
                            },
                            alwaysShowLabel = false,
                            icon = {
                                BadgedBox(
                                    badge = {
                                        if(item.badgeCount != null) {
                                            Badge {
                                                Text(text = item.badgeCount.toString())
                                            }
                                        } else if(item.hasNews) {
                                            Badge()
                                        }
                                    }
                                ) {
                                    Icon(
                                        imageVector = if (index == selectedItemIndex) {
                                            item.selectedIcon
                                        } else item.unselectedIcon,
                                        contentDescription = item.title
                                    )
                                }
                            }
                        )
                    }
                }
            }
        )
        { innerPadding ->
            when (items[selectedItemIndex].title) {
                "Color" -> {
                    ColorChooserScreen { onCommandChange(it) }
                }
                "Rainbow" -> {
                    RainbowScreen { onCommandChange(it) }
                }
                "Settings" -> {
                    SettingsScreen { onCommandChange(it) }
                }
                else ->
                {
                    Column(modifier = Modifier.padding(innerPadding)) {
                           AppInfoScreen()
                    }
                }
            }

        }
    }
}

private fun buildClearCommand(): String
{
    return CommandBuilder.instance.apply {
        clearState()
        appendFunction(FunctionValue.CLEAR)
        appendBrightness(255)
        appendWait(50)
        appendRGB(0,0,0)
    }.buildString()
}


