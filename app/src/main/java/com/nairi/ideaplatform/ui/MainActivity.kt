package com.nairi.ideaplatform.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nairi.ideaplatform.R
import com.nairi.ideaplatform.ui.composables.HomeScreen
import com.nairi.ideaplatform.ui.theme.IdeaPlatformTheme
import com.nairi.ideaplatform.ui.theme.TopAppBarColor
import com.nairi.ideaplatform.ui.viewmodel.MainViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                TopAppBarColor.toArgb(),
                TopAppBarColor.toArgb(),
            )
        )
        setContent {

            val viewModel = koinViewModel<MainViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            IdeaPlatformTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CenterAlignedTopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = colorResource(R.color.app_bar_color)
                            ),
                            title = { Text(text = stringResource(id = R.string.items_list)) }
                        )
                    }
                ) { innerPadding ->
                    HomeScreen(
                        innerPadding = innerPadding,
                        uiState = uiState,
                        onAction = viewModel::onAction
                    )
                }
            }
        }
    }
}

