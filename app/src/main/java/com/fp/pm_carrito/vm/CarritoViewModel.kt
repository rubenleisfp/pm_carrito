import androidx.lifecycle.ViewModel
import com.fp.pm_carrito.vm.state.CarritoState
import com.fp.pm_carrito.vm.state.Producto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CarritoViewModel : ViewModel() {

    private val _nombre = MutableStateFlow("")
    val nombre: StateFlow<String> = _nombre

    private val _precio = MutableStateFlow("")
    val precio: StateFlow<String> = _precio

    private val _uiState = MutableStateFlow(CarritoState())
    val uiState: StateFlow<CarritoState> = _uiState

    fun onNombreChanged(nuevo: String) {
        _nombre.value = nuevo
    }

    fun onPrecioChanged(nuevo: String) {
        _precio.value = nuevo
    }

    fun agregarProductoDesdeCampos() {
        val nombreActual = _nombre.value
        val precioActual = _precio.value.toDoubleOrNull() ?: 0.0
        val nuevoProducto = Producto(nombreActual, precioActual)

        val nuevaLista = _uiState.value.productos + nuevoProducto
        val nuevoTotal = calcularTotal(nuevaLista)
        _uiState.value = _uiState.value.copy(productos = nuevaLista, total = nuevoTotal)

        _nombre.value = ""
        _precio.value = ""
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
