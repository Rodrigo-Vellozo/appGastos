package br.com.rv.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.rv.entity.Chart;
import br.com.rv.entity.Usuario;
import br.com.rv.entity.repository.ProdutoRepository;
import br.com.rv.entity.repository.UsuarioRepository;

@RestController
@RequestMapping(value="rest")
public class ChartRestController {
	
	@Autowired
	ProdutoRepository pRepository;
	
	@Autowired
	UsuarioRepository uRepository;
	
	@RequestMapping(value="chartTwo/{categoria}")
	public String getDataPoints(Principal principal, @PathVariable("categoria")String categoria) {
		Usuario usuario = uRepository.findByUsername(principal.getName()) ;
		List<Chart>retorno = new ArrayList<>();
		String[][] lista= pRepository.getChartTwo(usuario.getId(), categoria);
		
		for (String[] strings : lista) {
			
			retorno.add(new Chart(strings[1], Double.parseDouble(strings[2])));
		}

		return new Gson().toJson(retorno);
	}

}
