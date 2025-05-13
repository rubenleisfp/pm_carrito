import androidx.lifecycle.ViewModel
import com.fp.pm_carrito.vm.state.CarritoState
import com.fp.pm_carrito.vm.state.Producto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CarritoViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CarritoState())
    val uiState: StateFlow<CarritoState> = _uiState

    fun onNombreChanged(nuevoNombre: String) {
        _uiState.value= _uiState.value.copy(nombre = nuevoNombre)
    }

    fun onPrecioChanged(nuevoPrecio: String) {
        _uiState.value= _uiState.value.copy(precio = nuevoPrecio)
    }

    fun agregarProductoDesdeCampos() {
        val nombreActual = _uiState.value.nombre
        val precioActual = _uiState.value.precio.toDoubleOrNull() ?: 0.0
        val nuevoProducto = Producto(nombreActual, precioActual)

        val nuevaLista = _uiState.value.productos + nuevoProducto
        val nuevoTotal = calcularTotal(nuevaLista)
        _uiState.value = _uiState.value.copy(productos = nuevaLista, total = nuevoTotal)

        _uiState.value= _uiState.value.copy(nombre = "")
        _uiState.value= _uiState.value.copy(precio = "")
    }

    fun eliminarProducto(producto: Producto) {
        val nuevaLista = _uiState.value.productos.toMutableList()
        nuevaLista.remove(producto)
        val nuevoTotal = calcularTotal(nuevaLista)
        _uiState.value = _uiState.value.copy(productos = nuevaLista, total = nuevoTotal)
    }

    private fun calcularTotal(lista: List<Producto>): Double {
        var suma = 0.0
        for (p in lista) {
            suma += p.precio
        }
        return suma
    }
}
