package br.senai.sp.informatica.todolist.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.senai.sp.informatica.todolist.modelo.Usuario;

@Repository
public class UsuarioDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	public void inserir(Usuario usuario){
		manager.persist(usuario);
	}
	
	//@Transactional
	public Usuario obter(Long idUsuario){
		return manager.find(Usuario.class, idUsuario);
	}
}
