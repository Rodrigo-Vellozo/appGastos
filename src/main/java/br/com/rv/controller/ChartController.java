package br.com.rv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.rv.entity.Produto;
import br.com.rv.entity.repository.ProdutoRepository;

@Controller
@ RequestMapping("chart")
public class ChartController {
	
	@Autowired
	ProdutoRepository pRepository;
	
	@RequestMapping(value="/teste", method=RequestMethod.GET)
	public String chart(Model model) {
		List<Produto> lst = pRepository.findProdutoByCategoriaAndUsuarioId("alimentacao", 1l);
		lst.forEach(System.err::println);
		
		model.addAttribute("listaB", lst);
		
		return "chart";
	}
		
}
