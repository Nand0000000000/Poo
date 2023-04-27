package br.gov.cesarschool.poo.fidelidade.cartao.entidade;

import java.time.LocalDateTime;
import java.util.Date;

public class LancamentoExtratoPontuacao extends LancamentoExtrato {

    public LancamentoExtratoPontuacao(long numeroCartao, int quantidadePontos, LocalDateTime dataHoraLancamento) {
        super(numeroCartao, quantidadePontos, dataHoraLancamento);
    }
}
