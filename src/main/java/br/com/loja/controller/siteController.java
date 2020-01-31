package br.com.loja.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.loja.model.Plataforma;
import br.com.loja.model.game;
import br.com.loja.model.carrinho;
import br.com.loja.repository.repGames;
import br.com.loja.repository.filter.JogosFiltro;

@Controller
public class siteController {
	@Autowired
	private repGames games;

	@RequestMapping
	public String home(@ModelAttribute("filtro") JogosFiltro filtro) {
		return "page_home"; 
	}
	@ModelAttribute  //(value="allgames")
	public List<game> TodosJogos(){ 
		return games.findAll();
	}
	@RequestMapping("/listajogo")
	public ModelAndView pesquisar(@ModelAttribute("filtro") JogosFiltro filtro) {
	//public ModelAndView pesquisar(@RequestParam(defaultValue = "%") String nome, @RequestParam(required = false) Plataforma plat) {
		List<game> todosGames;
		String nome=filtro.getNome()==null ? "%":filtro.getNome();
		Plataforma plat=filtro.getPlat()==null ? Plataforma.VAZIO:filtro.getPlat();
		
		if(nome=="%" && plat!=Plataforma.VAZIO) {
			todosGames=games.findByPlat(plat);
		}
		else if(nome!="%" && plat==Plataforma.VAZIO) {
			todosGames=games.findByNomeContaining(nome);
		}
		else if(nome!="%" && plat!=Plataforma.VAZIO) {
			todosGames=games.findByNomeContainingAndPlat(nome,plat);
		}
		else {
			todosGames=TodosJogos();
		}
		System.out.println(nome+" "+plat+" "+todosGames.get(0).getPlat().getTipo());
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
		ModelAndView mv=new ModelAndView("redirect:/carrinho");
		return mv;	
	}
	@RequestMapping("/carrinho")
	public ModelAndView carrinho() {
		ModelAndView mv=new ModelAndView("page_carrinho");
		mv.addObject("cart", carrinho.getListcarrinho());
		return mv;
	}
	@RequestMapping("/finalizar")
	public ModelAndView finalizarcompra(int jogoQtd) {
		List<game> jogocart=carrinho.getListcarrinho();
		for (game cartjogo : jogocart) {
			int estoque=cartjogo.getQtd();
			int qtd=1;
			qtd=qtd*jogoQtd;
			estoque=estoque-qtd;
			cartjogo.setQtd(estoque);
		}
		games.saveAll(jogocart);
		carrinho.listcarrinho.clear();
		ModelAndView mv=new ModelAndView("page_carrinho");
		mv.addObject("list_jogos", jogocart);
		return mv;
	}
}

