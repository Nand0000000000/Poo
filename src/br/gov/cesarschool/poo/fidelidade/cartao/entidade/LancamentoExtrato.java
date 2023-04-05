package br.gov.cesarschool.poo.fidelidade.cartao.entidade;

import java.time.LocalDateTime;
import java.util.Date;

public class LancamentoExtrato {
    private long numeroCartao;
    private int quantidadePontos;
    private java.time.LocalDateTime dataHoraLancamento;

    public LancamentoExtrato(long numeroCartao, int quantidadePontos, LocalDateTime dataHoraLancamento) {
        this.numeroCartao = numeroCartao;
        this.quantidadePontos = quantidadePontos;
        this.dataHoraLancamento = dataHoraLancamento;
    }

    public long getNumeroCartao() {
        return numeroCartao;
    }

    public int getQuantidadePontos() {
        return quantidadePontos;
    }

	public java.time.LocalDateTime getDataHoraLancamento() {
		return dataHoraLancamento;
	}

   
}
