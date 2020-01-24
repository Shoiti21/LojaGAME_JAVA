package br.com.loja.model;

public enum Plataforma {
	PLAYSTATION_4("Playstation 4"),
	PC("PC"),
	XBOX_ONE("XBOX One"),
	MAC("Mac");
	
	String tipo;
	
	Plataforma(String tipo) {
		this.tipo=tipo;
	}

	public String getTipo() {
		return tipo;
	}
}
