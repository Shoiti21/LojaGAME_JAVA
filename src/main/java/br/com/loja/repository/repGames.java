package br.com.loja.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.loja.model.Plataforma;
import br.com.loja.model.game;

public interface repGames extends JpaRepository<game, Long>{
	public List<game> findByNomeAndPlatContaining(String nome, Plataforma plat);
	public List<game> findByNomeContaining(String nome);
	public List<game> findByPlat(Plataforma plat);
}
