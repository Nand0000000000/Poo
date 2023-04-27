package br.gov.cesarschool.poo.fidelidade.cartao.negocio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import br.gov.cesarschool.poo.fidelidade.cartao.dao.CartaoFidelidadeDAO;
import br.gov.cesarschool.poo.fidelidade.cartao.dao.LancamentoExtratoDAO;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.CartaoFidelidade;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.LancamentoExtratoPontuacao;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.TipoResgate;
import br.gov.cesarschool.poo.fidelidade.cliente.entidade.Cliente;

public class CartaoFidelidadeMediator {
	
	private static CartaoFidelidadeMediator instancia;
	
	private final String QUANTIDADE_MENOR_QUE_ZERO = "Quantidade de pontos incorreta";
	private final String CARTAO_NAO_ENCONTRADO = "Cartão não encontrado";
	private final String SALDO_MENOR_QUE_PONTOS = "O saldo é menor que a pontuação";
	private CartaoFidelidadeDAO repositorioCartao;
	private LancamentoExtratoDAO repositorioLancamento;
	

	public CartaoFidelidadeMediator() {
		this.repositorioCartao = new CartaoFidelidadeDAO();
		this.repositorioLancamento = new LancamentoExtratoDAO();
	}
	
	public static synchronized CartaoFidelidadeMediator getInstancia() {
	    if (instancia == null) {
	        instancia = new CartaoFidelidadeMediator();
	    }
	    return instancia;
	}

	

	public long gerarCartao (Cliente cliente) {
		LocalDate dataAtual = LocalDate.now();
		String dataFormatada = dataAtual.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		Long numeroCartao = Long.parseLong(cliente.getCpf() + dataFormatada);
		CartaoFidelidade cartao = new CartaoFidelidade(numeroCartao);
		boolean cartaoIncluido = repositorioCartao.incluir(cartao);
		
		if(cartaoIncluido) {
			return numeroCartao;
		}else {
			return 0;
		}
	}

	public String pontuar(long numeroCartao, double quantidadePontos) {
		if(quantidadePontos <= 0) {
			return QUANTIDADE_MENOR_QUE_ZERO;
		}
		
		CartaoFidelidade cartao = repositorioCartao.buscar(numeroCartao);
		
		if(cartao == null) {
			return CARTAO_NAO_ENCONTRADO;
		}
		
		cartao.creditar(quantidadePontos);
		repositorioCartao.alterar(cartao);
		
		repositorioLancamento.incluir(new LancamentoExtratoPontuacao(numeroCartao, (int)quantidadePontos, LocalDateTime.now()));
		
		return null;
	}

	public String resgatar(long numeroCartao, double quantidadePontos, TipoResgate tipo) {
		if(quantidadePontos <= 0) {
			return QUANTIDADE_MENOR_QUE_ZERO;
		}
		
		CartaoFidelidade cartao = repositorioCartao.buscar(numeroCartao);
		
		if(cartao == null) {
			return CARTAO_NAO_ENCONTRADO;
		}
		
		if(cartao.getSaldo() < quantidadePontos) {
			return SALDO_MENOR_QUE_PONTOS;
		}
		
		cartao.debitar(quantidadePontos);
		repositorioCartao.alterar(cartao);
		
		repositorioLancamento.incluir(new LancamentoExtratoPontuacao(numeroCartao, (int)quantidadePontos, LocalDateTime.now()));
		
		return null;
	}	
}
