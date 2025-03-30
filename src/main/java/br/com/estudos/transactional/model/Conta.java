package br.com.estudos.transactional.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Conta {

    @Id
    private int contaKey;
    private String nomeDono;
    private BigDecimal saldo;

    public int getContaKey() {
        return contaKey;
    }

    public void setContaKey(int contaKey) {
        this.contaKey = contaKey;
    }

    public String getNomeDono() {
        return nomeDono;
    }

    public void setNomeDono(String nomeDono) {
        this.nomeDono = nomeDono;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public void subtrairSaldo(final BigDecimal valor) {
        if (semSaldo() || isSaldoInsuficiente(valor)) {
            throw new RuntimeException("Saldo insuficiente na conta");
        }
        saldo = saldo.subtract(valor);
    }

    public void adicionarSaldo(final BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Valor inválido para adição de saldo");
        }
        saldo = saldo.add(valor);
    }

    public boolean semSaldo() {
        return saldo == null || saldo.compareTo(BigDecimal.ZERO) == 0;
    }

    public boolean isSaldoInsuficiente(BigDecimal valor) {
        return saldo == null || saldo.compareTo(valor) < 0;
    }
}
