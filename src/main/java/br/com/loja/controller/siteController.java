package br.com.loja.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.loja.model.Plataforma;
import br.com.loja.model.game;
import br.com.loja.repository.repGames;

@Controller
public class siteController {
	@Autowired
	private repGames games;
	
	@RequestMapping
	public String home() {
		return "home"; 
	}
	
	@RequestMapping(value="/pesquisar", method=RequestMethod.POST)
	public ModelAndView pesquisar(game jogo) {
		List<game> todosGames;
		//System.out.println(jogo.getPlat()+" "+jogo.getPlat());
		
		//cria array com os dados de pesquisa
		if(jogo.getNome().isEmpty()) {
			todosGames=games.findByPlat(jogo.getPlat());
		}
		else if(jogo.getPlat()==Plataforma.VAZIO) {
			todosGames=games.findByNome(jogo.getNome());
		}
		else {
			todosGames=games.findByNomeAndPlat(jogo.getNome(),jogo.getPlat());
		}
		ModelAndView mv=new ModelAndView("pesquisa");
		mv.addObject("list_jogos", todosGames);
		return mv;
	}
	@RequestMapping("/pesquisar/{codigo}")
	public ModelAndView compra(@PathVariable Long codigo) {
		Optional<game> jogo=games.findById(codigo);
		ModelAndView mv=new ModelAndView("pesquisa");
		mv.addObject(jogo.get());
		return mv;
	}
}
