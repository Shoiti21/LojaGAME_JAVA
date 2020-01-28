package br.com.loja.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.loja.model.Plataforma;
import br.com.loja.model.game;

public interface repGames extends JpaRepository<game, Long>{
	List<game> findByNomeAndPlat(String nome, Plataforma plat);
	List<game> findByNome(String nome);
	List<game> findByPlat(Plataforma plat);
	List<game> findByCodigo(Long codigo);
}
