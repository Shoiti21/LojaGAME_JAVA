package br.com.loja.controller;

import java.util.ArrayList;
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
import br.com.loja.model.carrinho;
import br.com.loja.repository.repGames;

@Controller
public class siteController {
	@Autowired
	private repGames games;

	@RequestMapping
	public String home() {
		return "page_home"; 
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
		ModelAndView mv=new ModelAndView("page_pesquisa");
		mv.addObject("list_jogos", todosGames);
		return mv;
	}
	
	@RequestMapping("/compra/{codigo}")
	public ModelAndView compra(@PathVariable Long codigo) {
		Optional<game> jogo=games.findById(codigo);
		List<game> cart=new ArrayList<game>();
		cart=carrinho.getListcarrinho();
		cart.add(jogo.get());
		
		carrinho.setListcarrinho(cart);
		cart=carrinho.getListcarrinho();
		ModelAndView mv=new ModelAndView("page_carrinho");
		mv.addObject("cart", cart);
		return mv;
		
	}
}
/*
static
List<game> carrinho= new ArrayList<game>();
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
@RequestMapping("/compra/{codigo}")
public String compra(@PathVariable Long codigo) {
	Optional<game> jogo=games.findById(codigo);
	carrinho.add(jogo.get());
	return "carrinho";
}
*/
