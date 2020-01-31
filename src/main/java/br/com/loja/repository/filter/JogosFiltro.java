package br.com.loja.repository.filter;

import br.com.loja.model.Plataforma;

public class JogosFiltro {
	private String nome;
	private Plataforma plat;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Plataforma getPlat() {
		return plat;
	}
	public void setPlat(Plataforma plat) {
		this.plat = plat;
	}
}
