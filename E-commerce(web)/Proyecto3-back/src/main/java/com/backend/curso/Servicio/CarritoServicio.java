package com.backend.curso.Servicio;

import com.backend.curso.Entidades.Cursos;
import com.backend.curso.Entidades.Usuarios;
import com.backend.curso.Entidades.Carrito;
import com.backend.curso.Excepciones.CustomException;
import com.backend.curso.dto.carrito.AgregarcarritoDto;
import com.backend.curso.Repositorio.CarritoRepositorio;
import com.backend.curso.dto.carrito.CarritoDto;
import com.backend.curso.dto.carrito.CarritoItemsDto;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CarritoServicio {

    @Autowired
    private CursoServicio cursoServicio;

    @Autowired
    private CarritoRepositorio carritoRepositorio;

    @SneakyThrows
    public void agregarCarrito(AgregarcarritoDto agregarcarritoDto, Usuarios usuarios) throws CustomException {
        Cursos cursos = cursoServicio.findById(agregarcarritoDto.getCursoId());

        // Comprobar si el curso ya está en el carrito del usuario
        Optional<Carrito> carritoExistente = carritoRepositorio.findByCursosAndUsuarios(cursos, usuarios);
        if (carritoExistente.isPresent()) {
            throw new CustomException("El curso ya está en el carrito");
        }

        Carrito carrito = new Carrito();
        carrito.setCursos(cursos);
        carrito.setUsuarios(usuarios);
        carrito.setEstado(Carrito.Estado.DISPONIBLE);
        carrito.setFechacreada(new Date());

        carritoRepositorio.save(carrito);
    }
    public CarritoDto ListaCarritoItems(Usuarios usuarios) {
        List<Carrito> carritoLista = carritoRepositorio.findAllByUsuariosOrderByFechacreadaDesc(usuarios);

        List<CarritoItemsDto> carritoItems = new ArrayList<>();
        double costototal = 0;
        for(Carrito carrito: carritoLista){
            CarritoItemsDto carritoItemsDto = new CarritoItemsDto(carrito);
            costototal += carrito.getCursos().getPrecio();
            carritoItems.add(carritoItemsDto);

        }
        CarritoDto carritoDto = new CarritoDto();
        carritoDto.setCostoTotal(costototal);
        carritoDto.setCarritoItem(carritoItems);
        return carritoDto;
    }
    //Eliminar items del carrito
    @SneakyThrows
    public void EliminarItemCarrito(Integer itemId, Usuarios usuarios){
        Optional<Carrito> CarritoOpcional = carritoRepositorio.findById(itemId);
        if(CarritoOpcional.isEmpty()){
            throw new CustomException("El items del carrito no es valido" + itemId);
        }
        Carrito carrito = CarritoOpcional.get();
        if(carrito.getUsuarios() != usuarios){
            throw new CustomException("El items del carrito no pertenece al usuario" + itemId);
        }
        carritoRepositorio.delete(carrito);
    }
}
