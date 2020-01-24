package br.com.loja.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.loja.model.game;
import br.com.loja.repository.repGames;

@Controller
public class siteController {
	@Autowired
	private repGames games;
	
	@RequestMapping("/pesquisar")
	public ModelAndView pesquisar() {
		List<game> todosGames=games.findAll();
		ModelAndView mv=new ModelAndView("pesquisa");
		mv.addObject("list_jogos", todosGames);
		return mv;
	}
}
