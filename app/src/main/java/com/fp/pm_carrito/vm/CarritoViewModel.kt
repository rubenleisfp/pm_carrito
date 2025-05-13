import androidx.lifecycle.ViewModel
import com.fp.pm_carrito.vm.state.CarritoState
import com.fp.pm_carrito.vm.state.Producto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CarritoViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CarritoState())
    val uiState: StateFlow<CarritoState> = _uiState.asStateFlow()

    fun onNombreChanged(nuevoNombre: String) {
        throw UnsupportedOperationException("A implementar por el alumno")
    }

    fun onPrecioChanged(nuevoPrecio: String) {
        throw UnsupportedOperationException("A implementar por el alumno")
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
