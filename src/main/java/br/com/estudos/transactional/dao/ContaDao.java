package br.com.estudos.transactional.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

import br.com.estudos.transactional.model.Conta;

@Repository
public interface ContaDao extends JpaRepository<Conta, Integer> {

    Optional<Conta> findContaByContaKey(Integer contaKey);

    @Query("SELECT c.saldo FROM Conta c WHERE c.contaKey = :contaKey")
    Optional<BigDecimal> findSaldoByContaKey(int contaKey);
}
