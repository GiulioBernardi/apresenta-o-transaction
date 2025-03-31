package br.com.estudos.transactional.service;

import br.com.estudos.transactional.to.TransferenciaTo;

public class BancoServiceProxy {

    private BancoService delegate;

    //... outros campos

    public String transferir(final TransferenciaTo transferenciaTo) {
        try {
            //start transaction
            String result = delegate.transferir(transferenciaTo);
            //commit transaction
            return result;
        } catch (Exception e) {
            //rollback transaction
            throw new RuntimeException(e.getMessage());
        } finally {
            //close transaction
        }
    }

    //... outros métodos @Transaction

}
