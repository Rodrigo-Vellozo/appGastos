package br.com.rv.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.rv.entity.Usuario;
import br.com.rv.entity.repository.ProdutoRepository;
import br.com.rv.entity.repository.UsuarioRepository;

@Controller
@RequestMapping("chart")
public class ChartController {

	@Autowired
	ProdutoRepository pRepository;

	@Autowired
	UsuarioRepository uRepository;

	@RequestMapping(value = "/{categoria}/graficoTwo", method = RequestMethod.GET)
	public String chart(@PathVariable("categoria") String categoria, Model model, Principal principal)
			throws JsonProcessingException {
		Usuario usuario = uRepository.findByUsername(principal.getName());
		model.addAttribute("nome", usuario.getNome());
		model.addAttribute("categoria",categoria);
		return "graficoTwo";
	}

}
