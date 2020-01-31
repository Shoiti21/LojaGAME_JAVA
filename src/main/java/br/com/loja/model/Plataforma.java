package br.com.loja.model;

public enum Plataforma {
	VAZIO(""),
	PLAYSTATION_4("Playstation 4"),
	PC("PC"),
	XBOX_ONE("XBOX One"),
	MAC("Mac");
	
	private String tipo;
	
	Plataforma(String tipo) {
		this.tipo=tipo;
	}

	public String getTipo() {
		return tipo;
	}
}
