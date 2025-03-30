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
public class BancoService {

    @Autowired
    private ContaDao contaDao;

    @Autowired
    private TransacaoService transacaoService;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String transferir(final TransferenciaTo transferenciaTo) throws Exception {
        BigDecimal saldoOrigemAposTransferencia = retirarValorDaContaOrigem(transferenciaTo);
        BigDecimal saldoDestinoAposTransferencia = adicionarValor(transferenciaTo);

//        throwHahaha();
        return "Saldo conta origem " + saldoOrigemAposTransferencia + " e saldo conta destino " + saldoDestinoAposTransferencia;
    }

    @Transactional(rollbackFor = Exception.class)
    public String transferirRequiresNew(final TransferenciaTo transferenciaTo) throws Exception{
        final BigDecimal saldoOrigemAposTransferencia = retirarValorDaContaOrigem(transferenciaTo);
        final BigDecimal saldoDestinoAposTransferencia = adicionarValorNaContaDestinoRequiresNewNaMesmaProxy(transferenciaTo);

//        throwHahaha();
        return "Saldo conta origem " + saldoOrigemAposTransferencia + " e saldo conta destino " + saldoDestinoAposTransferencia;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public BigDecimal adicionarValorNaContaDestinoRequiresNewNaMesmaProxy(TransferenciaTo transferenciaTo) {
        final Conta contaDestino = contaDao.findContaByContaKey(transferenciaTo.getContaDestinoKey())
            .orElseThrow(() -> new RuntimeException("Conta de destino não encontrada"));
        contaDestino.adicionarSaldo(BigDecimal.valueOf(transferenciaTo.getValor()));

        BigDecimal saldoContaDestinoAposTirarDinheiro = contaDao.findSaldoByContaKey(contaDestino.getContaKey()).orElse(ZERO);
        contaDao.save(contaDestino);
        System.out.println("Saldo conta destino após receber valor" + saldoContaDestinoAposTirarDinheiro);
        return saldoContaDestinoAposTirarDinheiro;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String transferirRequiresNewEmDuasClasses(final TransferenciaTo transferenciaTo) throws Exception {
        final BigDecimal saldoOrigemAposTransferencia = transacaoService.removerValorEmOutraProxy(transferenciaTo);
        final BigDecimal saldoDestinoAposTransferencia = adicionarValor(transferenciaTo);

        return "Saldo conta origem " + saldoOrigemAposTransferencia + " e saldo conta destino " + saldoDestinoAposTransferencia;
    }

    private BigDecimal adicionarValor(final TransferenciaTo transferenciaTo) throws Exception {
        final Conta contaDestino = contaDao.findContaByContaKey(transferenciaTo.getContaDestinoKey())
            .orElseThrow(() -> new RuntimeException("Conta de destino não encontrada"));
        contaDestino.adicionarSaldo(BigDecimal.valueOf(transferenciaTo.getValor()));

        BigDecimal saldoDestinoAposTransferencia = contaDao.findSaldoByContaKey(contaDestino.getContaKey()).orElse(ZERO);
        contaDao.save(contaDestino);
        System.out.println("Saldo conta destino após receber valor" + saldoDestinoAposTransferencia);
        throwHahaha();
        return saldoDestinoAposTransferencia;
    }


    private BigDecimal retirarValorDaContaOrigem(final TransferenciaTo transferenciaTo) {
        final Conta contaOrigem = contaDao.findContaByContaKey(transferenciaTo.getContaOrigemKey())
            .orElseThrow(() -> new RuntimeException("Conta de origem não encontrada"));
        contaOrigem.subtrairSaldo(BigDecimal.valueOf(transferenciaTo.getValor()));
        contaDao.save(contaOrigem);

        BigDecimal saldoContaOrigemAposAdicionarDinheiro = contaDao.findSaldoByContaKey(contaOrigem.getContaKey()).orElse(ZERO);
        System.out.println("Saldo conta origem após tirar o valor: " + saldoContaOrigemAposAdicionarDinheiro);
        return saldoContaOrigemAposAdicionarDinheiro;
    }

    private void throwHahaha() throws Exception {
        throw new Exception("hahaha");
    }
}