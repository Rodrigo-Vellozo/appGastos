package br.com.rv.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
	public String logar(@ModelAttribute("usuario") Usuario usuario) {
		ModelAndView mv = new ModelAndView("home");
		
		Usuario user = uRepository.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha());
		mv.addObject("usuario", user);
				
		if (user==null) {
			return "";
		}
	
	
		return "redirect:home";
	}
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public ModelAndView getHome(@ModelAttribute Usuario usuario, @PageableDefault(size=4,sort="dataCadastro",direction=Direction.DESC)Pageable pageable, @PathVariable Long idUsuario) {
		ModelAndView mv = new ModelAndView("home");
		
		Page<Produto> page = pRepository.findProdutoByUsuarioId(1l, pageable);
		page.forEach(System.err::println);
		mv.addObject("page", page);
		
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
		
		//Page<Produto> page = pRepository.findAll(pageable);
		//model.addAttribute("page", page);
		
		
		// Optional<Produto> produto = pRepository.findByCategoriaAndUsuarioId("alimentacao", 1l);
		
		//List<Produto> produtos = pRepository.findByCategoriaAndUsuarioId("alimentacao", 1l);
		//produtos.forEach(System.err::println);
		
		//	funcionando!!! 
		//List<Produto>produtos = pRepository.findProdutoByUsuarioId(1l);
		//produtos.forEach(System.err::println);
		
		
		Page<Produto>page = pRepository.findProdutoByUsuarioId(1l, pageable);
		//page.forEach(System.err::println);
		model.addAttribute("page", page);
		
		
		
		
		return "teste";
	}
}