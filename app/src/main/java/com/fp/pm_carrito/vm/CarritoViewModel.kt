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
        throw UnsupportedOperationException("A implementar por el alumno")
    }

    fun eliminarProducto(producto: Producto) {
        throw UnsupportedOperationException("A implementar por el alumno")
    }

    private fun calcularTotal(lista: List<Producto>): Double {
        throw UnsupportedOperationException("A implementar por el alumno")
    }
}
