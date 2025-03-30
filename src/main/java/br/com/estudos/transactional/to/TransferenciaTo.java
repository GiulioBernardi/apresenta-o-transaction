package br.com.estudos.transactional.to;

public class TransferenciaTo {
    private int contaOrigemKey;
    private int contaDestinoKey;
    private double valor;

    public TransferenciaTo(int contaOrigemKey, int contaDestinoKey, double valor) {
            this.contaOrigemKey = contaOrigemKey;
            this.contaDestinoKey = contaDestinoKey;
            this.valor = valor;
    }

    public int getContaOrigemKey() {
            return contaOrigemKey;
    }

    public void setContaOrigemKey(int contaOrigemKey) {
            this.contaOrigemKey = contaOrigemKey;
    }

    public int getContaDestinoKey() {
            return contaDestinoKey;
    }

    public void setContaDestinoKey(int contaDestinoKey) {
            this.contaDestinoKey = contaDestinoKey;
    }

    public double getValor() {
            return valor;
    }

    public void setValor(double valor) {
            this.valor = valor;
    }

}
