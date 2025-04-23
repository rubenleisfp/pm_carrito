package com.fp.pm_carrito.vm.state

data class CarritoState(
    val productos: List<Producto> = emptyList(),
    val total: Double = 0.0
)