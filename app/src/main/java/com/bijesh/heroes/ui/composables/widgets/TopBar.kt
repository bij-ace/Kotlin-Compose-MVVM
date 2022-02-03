package com.bijesh.heroes.ui

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bijesh.heroes.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TopBar(scope: CoroutineScope, scaffoldState: ScaffoldState, navController: NavController?, isSecondaryScreen: MutableState<Boolean>) {
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name), fontSize = 18.sp) },
        navigationIcon = {
            IconButton(onClick = {
                if (!isSecondaryScreen.value) {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                } else {
                    navController?.navigateUp()
                    isSecondaryScreen.value = false
                }
            }) {
                Icon(if (!isSecondaryScreen.value) Icons.Filled.Menu else Icons.Filled.ArrowBack, "")
            }
        },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White
    )
}

@Preview(showBackground = false)
@Composable
fun TopBarPreview() {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    TopBar(scope = scope, scaffoldState = scaffoldState, null, remember { mutableStateOf(false) })
}