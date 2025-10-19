package com.example.petlink.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.petlink.R

@Composable
fun BottomNavigationBar() {
    BottomAppBar {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(R.drawable.ic_home),
                    contentDescription = "Menu principal",
                    modifier = Modifier.size(32.dp)
                )
            }

            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(R.drawable.ic_adoption),
                    contentDescription = "Espace adoption",
                    modifier = Modifier.size(32.dp)
                )
            }

            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(R.drawable.ic_carnet_sante),
                    contentDescription = "Carnet de santé",
                    modifier = Modifier.size(32.dp)
                )
            }

            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(R.drawable.ic_blog),
                    contentDescription = "Blog",
                    modifier = Modifier.size(32.dp)
                )
            }

            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(R.drawable.ic_veterinaires),
                    contentDescription = "Vétérinaires",
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar()
}