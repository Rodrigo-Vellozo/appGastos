package br.com.rv.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
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
		
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(@ModelAttribute("usuario") Usuario usuario) {
		return "login";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String registerGet(@ModelAttribute("usuario") Usuario usuario) {
		return "register";
	}

	@RequestMapping(value ="/register", method=RequestMethod.POST)
	public String registerPost(@Valid @ModelAttribute Usuario usuario, BindingResult result, Model model) {
		usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
		if (result.hasErrors()) {
			return "register";
		}else if (uRepository.findByUsername(usuario.getUsername())!=null) {
			System.err.println("candango existente");
			model.addAttribute("msg", "Email não disponível.");
			return "register";
		}
		
		uRepository.save(usuario);
		return "redirect:login";
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView getHome(Principal principal, @PageableDefault(size=4,sort="dataCadastro",direction=Direction.DESC)Pageable pageable, Model model) {
		ModelAndView mv = new ModelAndView("home");
		
		Usuario u = uRepository.findByUsername(principal.getName());
		mv.addObject("nome", u.getNome());
		mv.addObject("id",u.getId());
		
		// paginacão
		Page<Produto> page = pRepository.findProdutoByUsuarioId(u.getId(), pageable);
		mv.addObject("page", page);
	
		// total gasto com alimentação
		List<Produto> totalAlimentacao= pRepository.findProdutoByCategoriaAndUsuarioId("alimentacao", u.getId());
		List<Double>listaAlimentacao = new ArrayList<>();
		for (Produto alimentacao : totalAlimentacao) {
			listaAlimentacao.add(alimentacao.getPreco());
		}
		
		//listagem >>> total gasto com saude
		List<Produto> totalSaude = pRepository.findProdutoByCategoriaAndUsuarioId("saude", u.getId());
		List<Double> listaSaude = new ArrayList<>();
		for (Produto saude : totalSaude) {
			listaSaude.add(saude.getPreco());
		}
		
		// lisatagem >>> total gasto com entretenimento
		List<Produto> totalEntretenimento = pRepository.findProdutoByCategoriaAndUsuarioId("entretenimento", u.getId());
		List<Double> listaEntretenimento = new ArrayList<>();
		for (Produto entretenimento : totalEntretenimento) {
			listaEntretenimento.add(entretenimento	.getPreco());
		}
		
		// listagem total gasto com educação
		List<Produto> totalEducacao = pRepository.findProdutoByCategoriaAndUsuarioId("educacao", u.getId());
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

	@RequestMapping(value = "{usuarioId}/{categoria}/graficoOne", method = RequestMethod.GET)
	public String graficoOne(Model model, @PathVariable("usuarioId")Long usuarioId, @PathVariable("categoria") String categoria,
								Principal principal) {
		
		Usuario u = uRepository.findByUsername(principal.getName());
		model.addAttribute("nome", u.getNome());
		model.addAttribute("id", u.getId());
		
		// total gasto com alimentação
		List<Produto> totalAlimentacao = pRepository.findProdutoByCategoriaAndUsuarioId("alimentacao", usuarioId);
		List<Double> listaAlimentacao = new ArrayList<>();
		for (Produto alimentacao : totalAlimentacao) {
			listaAlimentacao.add(alimentacao.getPreco());
		}
		
		//listagem >>> total gasto com saude
		List<Produto> totalSaude = pRepository.findProdutoByCategoriaAndUsuarioId("saude", usuarioId);
		List<Double> listaSaude = new ArrayList<>();
		for (Produto saude : totalSaude) {
			listaSaude.add(saude.getPreco());
		}
		
		// lisatagem >>> total gasto com entretenimento
		List<Produto> totalEntretenimento = pRepository.findProdutoByCategoriaAndUsuarioId("entretenimento", usuarioId);
		List<Double> listaEntretenimento = new ArrayList<>();
		for (Produto entretenimento : totalEntretenimento) {
			listaEntretenimento.add(entretenimento	.getPreco());
		}
		
		// listagem total gasto com educação
		List<Produto> totalEducacao = pRepository.findProdutoByCategoriaAndUsuarioId("educacao", usuarioId);
		List<Double> listaEducacao = new ArrayList<>();
		for (Produto educacao : totalEducacao) {
			listaEducacao.add(educacao.getPreco());
		}
			
		model.addAttribute("totalAlimentacao", new Aggregates().sum(listaAlimentacao));
		model.addAttribute("totalSaude", new Aggregates().sum(listaSaude));
		model.addAttribute("totalEntretenimento", new Aggregates().sum(listaEntretenimento));
		model.addAttribute("totalEducacao", new Aggregates().sum(listaEducacao));
		
		return "graficoOne";
	}
	
}