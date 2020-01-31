package br.com.loja.model;

import java.util.ArrayList;
import java.util.List;

public class carrinho extends game{
	public static List<game> listcarrinho= new ArrayList<game>();

	public static List<game> getListcarrinho() {
		return listcarrinho;
	}

	public static void setListcarrinho(List<game> listcarrinho) {
		carrinho.listcarrinho = listcarrinho;
	}
}
