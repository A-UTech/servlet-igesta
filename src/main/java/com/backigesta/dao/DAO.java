package com.backigesta.dao;

import com.backigesta.model.Usuarios;

import java.util.List;


//Usada primariamente para facilitar a diferenciação de tabelas nas Servlets.
public abstract class DAO {
    public abstract Usuarios selecionarPorId(int id);
    public abstract boolean deletar(int id);
    public abstract boolean atualizar(Usuarios usuario);
}
