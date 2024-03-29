package com.example.cripty.presentation.coin_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.cripty.presentation.Screen
import com.example.cripty.presentation.coin_list.components.CoinListItem


@Composable
fun CoinListScreen(
    navController: NavController,
    coinListViewModel: CoinListViewModel = hiltViewModel()
){
    
    val state = coinListViewModel.state.value

    Box(Modifier.fillMaxSize()) {
        
        LazyColumn(Modifier.fillMaxSize()){
            items(state.coins){coin->
                CoinListItem(coin = coin){
                    navController.navigate(Screen.CoinDetailScreen.route + "/${coin.id}")
                }
            }
        }

        if(state.error.isNotBlank()){

            Text(text = state.error , color = Color.Red ,textAlign =  TextAlign.Center , modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .align(Alignment.Center))
        }


        if(state.isLoading){
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
        
    }
}