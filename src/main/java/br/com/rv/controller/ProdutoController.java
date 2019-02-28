package br.com.rv.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.rv.entity.Produto;
import br.com.rv.entity.repository.ProdutoRepository;
import br.com.rv.entity.repository.UsuarioRepository;

@Controller
@RequestMapping("usuario/{usuarioId}/produto")
public class ProdutoController {
	
	@Autowired
	ProdutoRepository pRepository;
	
	@Autowired
	UsuarioRepository uRepository;
			
	@RequestMapping(value="gravarVenda", method=RequestMethod.GET)
	public String gravarGet(@ModelAttribute("produto")Produto produto, @PathVariable("usuarioId")long usuarioId) {
		return "gravarVenda";
	}
	
	@RequestMapping(value="gravarVenda", method=RequestMethod.POST)
	public String gravarPost(@PathVariable("usuarioId")long usuarioId,@Valid @ModelAttribute("produto")Produto produto, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "gravarVenda";
		}
		produto.getUsuario().setId(usuarioId);
		pRepository.save(produto);
		return "redirect:/";
	}
	
	@RequestMapping(value="/{produtoId}/excluir", method=RequestMethod.GET)
	public String excluir(@PathVariable("usuarioId")Long usuarioId,@PathVariable("produtoId")Long produtoId) {
		Produto produto = pRepository.getOne(produtoId);
		pRepository.delete(produto);
		return "redirect:/";
	}
	
}
