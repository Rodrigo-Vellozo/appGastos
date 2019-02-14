package br.com.rv.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
//	@RequestMapping(value="/login", method=RequestMethod.POST)
//	public String logar(@ModelAttribute Usuario usuario, @ModelAttribute("produto")Produto produto) {
//		ModelMap map = new ModelMap();
//		
//		Usuario u = uRepository.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha());
//		System.err.println(">>>>>>>>>> U cheio: "+u);
//		
//		map.addAttribute("logado", u);
//		
//		
//		List<Produto> produtos = new ArrayList<>();
//		uRepository.findById(1L).map(usu -> produtos.addAll(usu.getProdutos()));
//		map.addAttribute("produtos", produtos);
//		
//		produtos.forEach(System.out::println);
//		
//		
//		return "home";
//		
//	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView logar(@ModelAttribute Usuario usuario) {
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("produto", new Produto());
		
		Usuario u = uRepository.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha());
		mv.addObject("usuario", u);
		
		if (u==null) {
			return new ModelAndView("index");
		}
		
		List<Produto> produtos= new ArrayList<>();
		uRepository.findById(u.getId()).map(user-> produtos.addAll(user.getProdutos()));
		mv.addObject("produtos", produtos);
		
		produtos.forEach(System.out::println);
		
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

	@RequestMapping(value = "/gravarVenda", method = RequestMethod.GET)
	public String gravarVendaGet(@ModelAttribute("produto") Produto produto) {
		return "gravarVenda";
	}
	@RequestMapping(value = "/gravarVenda", method = RequestMethod.POST)
	public ModelAndView gravarVenda(@ModelAttribute Produto produto) {
		ModelAndView mv = new ModelAndView("home");
		pRepository.save(produto);
		return mv;
	}
	 
}
