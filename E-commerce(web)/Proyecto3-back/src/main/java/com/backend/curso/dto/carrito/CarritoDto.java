package com.backend.curso.dto.carrito;

import java.util.List;

public class CarritoDto {
    private List<CarritoItemsDto> carritoItem;
    private double costoTotal;

    public CarritoDto() {
    }

    public List<CarritoItemsDto> getCarritoItem() {
        return carritoItem;
    }

    public void setCarritoItem(List<CarritoItemsDto> carritoItem) {
        this.carritoItem = carritoItem;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }
}
