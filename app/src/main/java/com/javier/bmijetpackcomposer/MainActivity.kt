package com.javier.bmijetpackcomposer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.javier.bmijetpackcomposer.ui.theme.BMIJetpackComposerTheme
import java.util.Locale

val DarkBackground = Color(0xFF0D0E15)
val CardBackground = Color(0xFF181A26)
val PurpleAccent = Color(0xFF6C27FF)
val CardBorderColor = Color(0xFF2A2D3D)
val TextGray = Color(0xFF8E92A8)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BMIJetpackComposerTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = DarkBackground
                ) { innerPadding ->
                    BMIScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun BMIScreen(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("Josep Maria") }
    var pes by remember { mutableIntStateOf(80) }
    var alcada by remember { mutableFloatStateOf(180f) }

    var bmiResult by remember { mutableStateOf<Float?>(24.7f) }
    var bmiCategory by remember { mutableStateOf("Pes saludable") }
    var categoryColor by remember { mutableStateOf(Color(0xFF00E676)) }

    fun calculateBMI() {
        if (alcada > 0) {
            val alcadaMetres = alcada / 100f
            val imc = pes / (alcadaMetres * alcadaMetres)
            bmiResult = imc

            when {
                imc < 18.5f -> {
                    bmiCategory = "Pes baix"
                    categoryColor = Color(0xFFFFB74D)
                }
                imc in 18.5f..24.9f -> {
                    bmiCategory = "Pes saludable"
                    categoryColor = Color(0xFF00E676)
                }
                imc in 25.0f..29.9f -> {
                    bmiCategory = "Sobrepes"
                    categoryColor = Color(0xFFFF9800)
                }
                else -> {
                    bmiCategory = "Obesitat"
                    categoryColor = Color(0xFFFF5252)
                }
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Calculadora IMC",
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = "Calcula el teu índex de massa corporal",
            color = TextGray,
            fontSize = 14.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        StyledCard {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "NOM",
                    color = TextGray,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFF222533),
                        unfocusedContainerColor = Color(0xFF222533),
                        focusedIndicatorColor = PurpleAccent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )
            }
        }

        StyledCard {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "ALÇADA",
                    color = TextGray,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = "${alcada.toInt()}",
                        color = Color.White,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "cm",
                        color = TextGray,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Slider(
                    value = alcada,
                    onValueChange = { alcada = it },
                    valueRange = 100f..220f,
                    colors = SliderDefaults.colors(
                        thumbColor = PurpleAccent,
                        activeTrackColor = PurpleAccent,
                        inactiveTrackColor = Color(0xFF2C2F42)
                    )
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "100 cm", color = TextGray, fontSize = 12.sp)
                    Text(text = "220 cm", color = TextGray, fontSize = 12.sp)
                }
            }
        }

        StyledCard {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "PES",
                        color = TextGray,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = "$pes",
                            color = Color.White,
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "kg",
                            color = TextGray,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                    }
                }

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Button(
                        onClick = { if (pes > 20) pes-- },
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = PurpleAccent),
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier.size(48.dp)
                    ) {
                        Text(text = "-", color = Color.White, fontSize = 24.sp)
                    }

                    Button(
                        onClick = { if (pes < 300) pes++ },
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = PurpleAccent),
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier.size(48.dp)
                    ) {
                        Text(text = "+", color = Color.White, fontSize = 24.sp)
                    }
                }
            }
        }

        Button(
            onClick = { calculateBMI() },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PurpleAccent)
        ) {
            Text(
                text = "CALCULAR IMC",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        bmiResult?.let { result ->
            StyledCard {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "IMC de ${if (name.isBlank()) "Usuari" else name}",
                        color = TextGray,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = String.format(Locale.getDefault(), "%.1f", result),
                        color = Color.White,
                        fontSize = 44.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    Surface(
                        color = categoryColor.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(8.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, categoryColor.copy(alpha = 0.5f))
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .background(categoryColor, shape = CircleShape)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = bmiCategory,
                                color = categoryColor,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StyledCard(content: @Composable () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, CardBorderColor, RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun BMIScreenPreview() {
    BMIJetpackComposerTheme {
        BMIScreen()
    }
}