package br.com.loja.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	@ModelAttribute(value="allgames")
	public List<game> TodosJogos(){ 
		return games.findAll();
	}
	@RequestMapping("/listajogo")
	public ModelAndView pesquisar(@RequestParam(defaultValue = "%") String nome, Plataforma plat) {
		List<game> todosGames;
		//System.out.println(jogo.getPlat()+" "+jogo.getPlat());
		
		//cria array com os dados de pesquisa
		if(nome.isEmpty() || nome=="%") {
			todosGames=games.findByPlat(plat);
		}
		else if(plat==Plataforma.VAZIO && nome!="%") {
			todosGames=games.findByNomeContaining(nome);
		}
		else {
			todosGames=TodosJogos();
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
	@RequestMapping("/finalizar")
	public ModelAndView finalizarcompra() {
		List<game> jogocart=carrinho.getListcarrinho();
		for (game cartjogo : jogocart) {
			int estoque=cartjogo.getQtd();
			estoque--;
			cartjogo.setQtd(estoque);
		}
		games.saveAll(jogocart);
		carrinho.listcarrinho.clear();
		ModelAndView mv=new ModelAndView("page_carrinho");
		mv.addObject("list_jogos", jogocart);
		return mv;
	}
}

