/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.farmacia.service;

import com.example.farmacia.model.Pedido;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface PedidoService {
    
        public Pedido crear(Pedido c);

    public List<Pedido> findByAll();

    public Pedido findById(Integer id);

    public void delete(Integer id);
}
