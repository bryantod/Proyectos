package com.backend.curso.Controlador;

import com.backend.curso.Configuracion.ApiResponse;
import com.backend.curso.Entidades.Usuarios;
import com.backend.curso.Excepciones.AuthenticationFailException;
import com.backend.curso.Excepciones.CustomException;
import com.backend.curso.Servicio.CarritoServicio;
import com.backend.curso.Servicio.TokenServicio;
import com.backend.curso.dto.carrito.AgregarcarritoDto;
import com.backend.curso.dto.carrito.CarritoDto;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/carrito")
public class CarritoControl {

    @Autowired
    private CarritoServicio carritoServicio;

    @Autowired
    private TokenServicio tokenServicio;

    @SneakyThrows
    @PostMapping("/agregar")
    public ResponseEntity<ApiResponse> agregarCarrito(@RequestBody AgregarcarritoDto agregarcarritoDto,
                                                      @RequestParam("token") String token) {
        tokenServicio.autenticar(token);
        Usuarios usuarios = tokenServicio.getUsuario(token);
        carritoServicio.agregarCarrito(agregarcarritoDto, usuarios);
        return new ResponseEntity<>(new ApiResponse(true, "Agregado al Carrito"), HttpStatus.OK);
    }

    @SneakyThrows
    @GetMapping("/")
    public ResponseEntity<CarritoDto> MostarItemsCarrito(@RequestParam("token")String token){
        tokenServicio.autenticar(token);
        Usuarios usuarios = tokenServicio.getUsuario(token);

        CarritoDto carritoDto = carritoServicio.ListaCarritoItems(usuarios);
        return new ResponseEntity<>(carritoDto, HttpStatus.OK);
    }
    @SneakyThrows
    @DeleteMapping("Eliminar/{carritoItemsId}")
    public ResponseEntity<ApiResponse> EliminarItemCarrito(@PathVariable("carritoItemsId")Integer itemId,
                                                           @RequestParam("token")String token){
        tokenServicio.autenticar(token);
        Usuarios usuarios = tokenServicio.getUsuario(token);

        carritoServicio.EliminarItemCarrito(itemId, usuarios);

        return new ResponseEntity<>(new ApiResponse(true, "Item Eliminado"), HttpStatus.OK);
    }
}
