/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.farmacia.service;

import com.example.farmacia.model.Pedido;
import com.example.farmacia.repository.PedidoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired//Cree como un objeto referencial
    private PedidoRepository pedidorepository;

    @Override
    public Pedido crear(Pedido p) {
        return pedidorepository.save(p);
    }

    @Override
    public List<Pedido> findByAll() {
        return pedidorepository.findAll();

    }

    @Override
    public Pedido findById(Integer id) {
        return pedidorepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        pedidorepository.deleteById(id);
    }

}
