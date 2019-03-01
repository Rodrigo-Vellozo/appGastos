package br.com.rv.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import br.com.rv.entity.Produto;
import br.com.rv.entity.Usuario;
import br.com.rv.entity.repository.ProdutoRepository;
import br.com.rv.entity.repository.UsuarioRepository;

@Controller
@ RequestMapping("chart")
public class ChartController {
	
	@Autowired
	ProdutoRepository pRepository;
	
	@Autowired
	UsuarioRepository uRepository;
	
	@RequestMapping(value="/{categoria}/graficoTwo", method=RequestMethod.GET)
	public String chart(@PathVariable("categoria")String categoria, Model model, Principal principal) throws JsonProcessingException {
		Usuario usuario = uRepository.findByUsername(principal.getName());
		model.addAttribute("nome", usuario.getNome());

		List<Produto>produtos = pRepository.findProdutoByCategoriaAndUsuarioId(categoria, usuario.getId());
		
		model.addAttribute("dataPointsList", produtos);//monta a tabela
		
		
		List<Date> datas = new ArrayList<>();
		for (Produto produto : produtos) {
			datas.add(produto.getDataCadastro());
		}
		
		datas.forEach(System.err::println);
		
		
		Produto produto1 = new Produto(1l, "carro", "veiculos", 35000.,	 new Date(), null);
		List<Produto>list= new ArrayList<>();
		list.add(produto1);
		
		//String json = new Gson().toJson(uRepository.findAll());
		//System.err.println(json);
		
		
		
		return "graficoTwo";
	}
	
	
	
	
	
	
	
	
		
}
