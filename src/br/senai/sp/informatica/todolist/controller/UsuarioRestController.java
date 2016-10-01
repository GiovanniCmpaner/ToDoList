package br.senai.sp.informatica.todolist.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.informatica.todolist.dao.UsuarioDao;
import br.senai.sp.informatica.todolist.modelo.Lista;
import br.senai.sp.informatica.todolist.modelo.Usuario;

@RestController
public class UsuarioRestController {
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@RequestMapping(value = "/usuario", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Usuario> inserir(@RequestBody Usuario usuario) {
		try {
			usuarioDao.inserir(usuario);
			URI location = new URI("/usuario/" + usuario.getId());

			return ResponseEntity.created(location).body(usuario);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/usuario/{idUsuario}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Usuario> obterLista(@PathVariable Long idUsuario) {
		Usuario usuario = usuarioDao.obter(idUsuario);
		if (usuario != null) {
			return ResponseEntity.ok().body(usuario);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}