package br.com.rv.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;

import br.com.rv.entity.Produto;
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

		//List<Produto> produtos = pRepository.findProdutoByCategoriaAndUsuarioId(categoria, usuario.getId());
		//model.addAttribute("produtos", produtos);// monta a tabela
		//////////////////////////////////////////////////////////
		
		List<Produto>lista= pRepository.getInfo(usuario.getId(), categoria);
		lista.forEach(System.err::println);
		
		String string = pRepository.getBro(usuario.getId(), categoria,2);
		Gson gson = new Gson();
		gson.toJson(string);
		
		System.out.println(gson);
		
		
		model.addAttribute("teste",gson);
		return "graficoTwo";
	}

}
