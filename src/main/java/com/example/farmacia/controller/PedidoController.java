/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.farmacia.controller;

import com.example.farmacia.model.Pedido;
import com.example.farmacia.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ASUS
 */
@RestController
@Controller
@RequestMapping("/api/pedido")
public class PedidoController {

    String direccion;
    
    @Autowired
    PedidoService pedidosservice;

    @GetMapping("/lista")
    public ResponseEntity<List<Pedido>> listar() {

        return new ResponseEntity<>(pedidosservice.findByAll(), HttpStatus.OK);

    }

    @Operation(summary = "Crear un pedido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Pedido enviado correctamente.", content = {
            @Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "400", description = "No se puede crear el pedido", content = {
            @Content(mediaType = "application/json")})
    })
    @PostMapping("/crear")
    public ResponseEntity<String> crear(@RequestBody Pedido p) {
        if (p.getCantidadProducto() > 0) {
            if (p.getSucursal().equalsIgnoreCase("principal") || p.getSucursal().equalsIgnoreCase("secundaria")) {
                
                if (p.getSucursal().equalsIgnoreCase("principal")) {
                    direccion = "Octavio Chacón Moscoso";
                } else {
                    direccion = "AV de la Independencia";
                }
                
                if (p.getTipoMedicamento().equalsIgnoreCase("analgesico") || p.getTipoMedicamento().equalsIgnoreCase("analeptico") || p.getTipoMedicamento().equalsIgnoreCase("anestesico")) {
                    if (p.getDistribuidor().equalsIgnoreCase("cofarma") || p.getDistribuidor().equalsIgnoreCase("empsephar") || p.getDistribuidor().equalsIgnoreCase("cemefar")) {
                        pedidosservice.crear(p);
                        return new ResponseEntity<>("Direccion: " + direccion + " Cantidad: " + p.getCantidadProducto() + " Tipo de Medicamento: " + p.getTipoMedicamento().toString() + " Nombre del Medicamento " + p.getNombreMedicamento(), HttpStatus.CREATED);

                    } else {
                        return new ResponseEntity<>("Distribuidor no Valido", HttpStatus.BAD_REQUEST);

                    }
                } else {
                    return new ResponseEntity<>("Tipo de medicamento no Valido", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("Sucursal no Valido", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Cantidad no Valida", HttpStatus.BAD_REQUEST);
        }

    }
    @Operation(summary = "Eliminar un pedido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Pedido Eliminado correctamente.", content = {
            @Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "400", description = "No se puede Eliminar el pedido", content = {
            @Content(mediaType = "application/json")})
    })
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Pedido> elimnar(@PathVariable Integer id) {
        pedidosservice.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
    
    @Operation(summary = "Buscar un pedido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Pedido enviado correctamente.", content = {
            @Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "400", description = "No se puede crear el pedido", content = {
            @Content(mediaType = "application/json")})
    })
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Pedido> buscar(@PathVariable Integer id) {

        return new ResponseEntity<>(pedidosservice.findById(id), HttpStatus.OK);

    }
}
