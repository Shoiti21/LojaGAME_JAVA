package br.com.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.loja.model.game;

public interface repGames extends JpaRepository<game, Long>{

}
