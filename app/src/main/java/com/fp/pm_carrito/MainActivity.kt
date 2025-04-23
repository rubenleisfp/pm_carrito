package com.fp.pm_carrito

import CarritoViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fp.pm_carrito.ui.theme.Pm_carritoTheme
import com.fp.pm_carrito.vm.state.CarritoState
import com.fp.pm_carrito.vm.state.Producto

class MainActivity : ComponentActivity() {

    private val carritoViewModel by viewModels<CarritoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Pm_carritoTheme {
                CarritoApp(carritoViewModel)
            }
        }
    }
}

@Composable
fun CarritoApp(carritoViewModel: CarritoViewModel) {
    val nombre by carritoViewModel.nombre.collectAsState()
    val precio by carritoViewModel.precio.collectAsState()
    val uiState by carritoViewModel.uiState.collectAsState()

    CarritoScreen(
        nombre = nombre,
        precio = precio,
        uiState = uiState,
        onNombreChanged = { name -> carritoViewModel.onNombreChanged(name) },
        onPrecioChanged = { price -> carritoViewModel.onPrecioChanged(price) },
        onAgregarProducto = { carritoViewModel.agregarProductoDesdeCampos()},
        onEliminarProducto = {producto -> carritoViewModel.eliminarProducto(producto)}
    )
}

@Composable
fun CarritoScreen(
    nombre: String,
    precio: String,
    uiState: CarritoState,
    onNombreChanged: (String) -> Unit,
    onPrecioChanged: (String) -> Unit,
    onAgregarProducto: () -> Unit,
    onEliminarProducto:(Producto) -> Unit
) {


    Column(modifier = Modifier.background(Color.White).padding(16.dp)) {
        Text("Carrito de Compras", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = nombre,
            onValueChange = { valor -> onNombreChanged(valor) },
            label = { Text("Nombre del producto") }
        )

        OutlinedTextField(
            value = precio,
            onValueChange = { valor -> onPrecioChanged(valor) },
            label = { Text("Precio") },
            modifier = Modifier.padding(top = 8.dp)
        )

        Button(
            onClick = onAgregarProducto,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Agregar")
        }

        Spacer(Modifier.height(16.dp))

        Text("Productos en el carrito:")

        LazyColumn {
            items(uiState.productos) { producto ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("${producto.nombre} - ${producto.precio}€")
                    Button(onClick = { onEliminarProducto(producto) }) {
                        Text("Eliminar")
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))
        Text("Total: ${uiState.total} €")
    }
}


@Preview
@Composable
fun CarritoScreenPreview() {
    CarritoScreen(
        nombre = "",
        precio = "",
        uiState = CarritoState(
            productos = listOf(
                Producto("Pan", 1.0),
                Producto("Leche", 1.5)
            ),
            total = 2.5
        ),
        onNombreChanged = {},
        onPrecioChanged = {},
        onAgregarProducto = {},
        onEliminarProducto = {}
    )
}

