package br.com.rv.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.expression.Aggregates;

import br.com.rv.entity.Produto;
import br.com.rv.entity.Usuario;
import br.com.rv.entity.repository.ProdutoRepository;
import br.com.rv.entity.repository.UsuarioRepository;

@Controller

public class UsuarioController {
	
	@Autowired
	UsuarioRepository uRepository;
	
	@Autowired
	ProdutoRepository pRepository;
	
		
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("msg", "Bem vindo ao Sistema");
		modelAndView.addObject("usuarios", uRepository.findAll());
		modelAndView.addObject("produtos2", pRepository.findAll());
		//atenção, código massa! :)
		List<Produto> produtos = new ArrayList<>();
		uRepository.findById(1L).map(usuario -> produtos.addAll(usuario.getProdutos()));
		modelAndView.addObject("produtos", produtos);
		return modelAndView;
	}
	
	//tela do login furado
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(@ModelAttribute("usuario") Usuario usuario) {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String logar(@ModelAttribute("usuario") Usuario usuario, RedirectAttributes attr) {
		Usuario user = uRepository.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha());
				
		if (user==null) {
			return "redirect:login";
		}
		
		attr.addAttribute("usuario",user);
		attr.addFlashAttribute("usuario", user);
		
		return "redirect:home";
	}
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public ModelAndView getHome(@ModelAttribute Usuario usuario, @PageableDefault(size=4,sort="dataCadastro",direction=Direction.DESC)Pageable pageable, Model model) {
		ModelAndView mv = new ModelAndView("home");
		
		System.out.println();
		System.out.println(model);
		
		// paginacão
		Page<Produto> page = pRepository.findProdutoByUsuarioId(1l, pageable);
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

	
	@RequestMapping(value="/cadastrar", method=RequestMethod.GET)
	public String cadastrarGet(@ModelAttribute("usuario") Usuario usuario) {
		return "cadastrar";
	}

	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	public String cadastrarPost(@ModelAttribute Usuario usuario) {
		uRepository.save(usuario);
		return "redirect:/";
	}
	
	
	////////teste paginacao
	@RequestMapping(value = "/teste", method = RequestMethod.GET)
	public String getProdutos(@PageableDefault(size = 2, sort="dataCadastro",direction=Direction.DESC) Pageable pageable, Model model) {
		
				
		
		
		
		return "teste";
	}
}