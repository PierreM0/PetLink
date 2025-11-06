package com.example.petlink.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.petlink.R
import com.example.petlink.navigation.PetLinkScreens
import com.example.petlink.ui.theme.LightGray
import com.example.petlink.ui.theme.MainGreen

data class BottomNavItem(
    val iconRes: Int,
    val contentDescription: String,
    val route: String
)

@Composable
fun BottomNavigationBar(
    currentRoute: String?,
    onIconClick: (String) -> Unit
) {
    val navItems = listOf(
        BottomNavItem(R.drawable.ic_home, "Menu principal", PetLinkScreens.HomeScreen.route),
        BottomNavItem(R.drawable.ic_adoption, "Espace adoption", PetLinkScreens.AdoptionScreen.route),
        BottomNavItem(R.drawable.ic_carnet_sante, "Carnet de santé", PetLinkScreens.HealthRecordScreen.route),
        BottomNavItem(R.drawable.ic_blog, "Blog", PetLinkScreens.BlogScreen.route),
        BottomNavItem(R.drawable.ic_veterinaires, "Vétérinaires", PetLinkScreens.VeterinaryScreen.route)
    )

    BottomAppBar(
        modifier = Modifier.background(LightGray)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            navItems.forEach { item ->
                val iconColor = if (item.route == currentRoute) MainGreen else Color.Black

                IconButton(onClick = { onIconClick(item.route) }) {
                    Icon(
                        painter = painterResource(item.iconRes),
                        contentDescription = item.contentDescription,
                        tint = iconColor,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    }
}
