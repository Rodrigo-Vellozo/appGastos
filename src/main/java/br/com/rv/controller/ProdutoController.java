package br.com.rv.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.expression.Aggregates;

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
	
	@RequestMapping(value="/listar", method=RequestMethod.GET)
	public ModelAndView listar(@PathVariable("usuarioId")Long usuarioId, @PageableDefault(size=4,sort="dataCadastro",direction=Direction.DESC)Pageable pageable) {
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("usuario", uRepository.getOne(usuarioId));
		
		Page<Produto> page = pRepository.findProdutoByUsuarioId(usuarioId, pageable);
		mv.addObject("page", page);
		
		// total gasto com alimentação
		List<Produto> totalAlimentacao= pRepository.findProdutoByCategoriaAndUsuarioId("alimentacao", 1l);
		List<Double>listaAlimentacao = new ArrayList<>();
		for (Produto alimentacao : totalAlimentacao) {
			listaAlimentacao.add(alimentacao.getPreco());
		}
		
		//listagem >>> total gasto com saude
		List<Produto> totalSaude = pRepository.findProdutoByCategoriaAndUsuarioId("saude", 1l);
		List<Double> listaSaude = new ArrayList<>();
		for (Produto saude : totalSaude) {
			listaSaude.add(saude.getPreco());
		}
		
		// lisatagem >>> total gasto com entretenimento
		List<Produto> totalEntretenimento = pRepository.findProdutoByCategoriaAndUsuarioId("entretenimento", 1l);
		List<Double> listaEntretenimento = new ArrayList<>();
		for (Produto entretenimento : totalEntretenimento) {
			listaEntretenimento.add(entretenimento	.getPreco());
		}
		
		// listagem total gasto com educação
		List<Produto> totalEducacao = pRepository.findProdutoByCategoriaAndUsuarioId("educacao", 1l);
		List<Double> listaEducacao = new ArrayList<>();
		for (Produto educacao : totalEducacao) {
			listaEducacao.add(educacao.getPreco());
		}
			
		mv.addObject("totalAlimentacao", new Aggregates().sum(listaAlimentacao));
		mv.addObject("totalSaude", new Aggregates().sum(listaSaude));
		mv.addObject("totalEntretenimento", new Aggregates().sum(listaEntretenimento));
		mv.addObject("totalEducacao", new Aggregates().sum(listaEducacao));
		
		return mv;	
	}
			
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
		return "redirect:/usuario/"+usuarioId+"/produto/listar";
	}
	
	@RequestMapping(value="/{produtoId}/excluir", method=RequestMethod.GET)
	public String excluir(@PathVariable("usuarioId")Long usuarioId,@PathVariable("produtoId")Long produtoId) {
		Produto produto = pRepository.getOne(produtoId);
		pRepository.delete(produto);
		return "redirect:/usuario/"+usuarioId+"/produto/listar";
	}
	
}
