package br.com.estudos.transactional.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import br.com.estudos.transactional.dao.ContaDao;
import br.com.estudos.transactional.model.Conta;
import br.com.estudos.transactional.to.TransferenciaTo;

import static java.math.BigDecimal.ZERO;

@Service
public class TransacaoService {

    @Autowired
    private ContaDao contaDao;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public BigDecimal adicionarValor(final Integer contaDestinoKey, final BigDecimal valor) throws Exception {
        final Conta contaDestino = contaDao.findContaByContaKey(contaDestinoKey)
            .orElseThrow(() -> new RuntimeException("Conta de destino não encontrada"));
        contaDestino.adicionarSaldo(valor);
        BigDecimal saldoContaDestinoAposAdicionarDinheiro = contaDao.findSaldoByContaKey(contaDestino.getContaKey()).orElse(ZERO);
        contaDao.save(contaDestino);
        System.out.println("Saldo conta destino após receber valor" + saldoContaDestinoAposAdicionarDinheiro);
        throwHahaha();
        return saldoContaDestinoAposAdicionarDinheiro;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public BigDecimal removerValorEmOutraProxy(final TransferenciaTo transferenciaTo) throws Exception {
        final Conta contaOrigem = contaDao.findContaByContaKey(transferenciaTo.getContaOrigemKey())
            .orElseThrow(() -> new RuntimeException("Conta de origem não encontrada"));
        contaOrigem.subtrairSaldo(BigDecimal.valueOf(transferenciaTo.getValor()));
        contaDao.save(contaOrigem);

        BigDecimal saldoContaOrigemAposTirarDinheiro = contaDao.findSaldoByContaKey(contaOrigem.getContaKey()).orElse(ZERO);
        System.out.println("Saldo conta origem após tirar o valor: " + saldoContaOrigemAposTirarDinheiro);
        return saldoContaOrigemAposTirarDinheiro;
    }

    private static void throwHahaha() throws Exception {
        throw new Exception("hahaha");
    }

}
