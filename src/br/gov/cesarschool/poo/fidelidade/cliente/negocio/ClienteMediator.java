package br.gov.cesarschool.poo.fidelidade.cliente.negocio;
import br.gov.cesarschool.poo.fidelidade.cartao.negocio.CartaoFidelidadeMediator;
import br.gov.cesarschool.poo.fidelidade.cliente.dao.ClienteDAO;
import br.gov.cesarschool.poo.fidelidade.cliente.entidade.Cliente;
import br.gov.cesarschool.poo.fidelidade.geral.entidade.Endereco;
import br.gov.cesarschool.poo.fidelidade.util.ValidadorCPF;
import br.gov.cesarschool.poo.fidelidade.util.StringUtil;

public class ClienteMediator {
	


	    private static ClienteMediator instancia;

	    private ClienteDAO repositorioCliente;
	    private CartaoFidelidadeMediator cartaoMediator;

	    private ClienteMediator() {
	        this.repositorioCliente = new ClienteDAO();
	        this.cartaoMediator = CartaoFidelidadeMediator.getInstancia();
	    }

	    public static synchronized ClienteMediator getInstancia() {
	        if (instancia == null) {
	            instancia = new ClienteMediator();
	        }
	        return instancia;
	    }

	    public ResultadoInclusaoCliente incluir(Cliente cliente) {
	        String erroValidacao = validar(cliente);
	        if (erroValidacao == null) {
	            repositorioCliente.incluir(cliente);
	            long cartaoFidelidade = cartaoMediator.gerarCartao(cliente);
	            return new ResultadoInclusaoCliente(cartaoFidelidade, null);
	        }else{
	        	return new ResultadoInclusaoCliente(0, erroValidacao);
	        }
	    }

	    public String alterar(Cliente cliente) {
	        String erroValidacao = validar(cliente);
	        if (erroValidacao == null) {
	        	repositorioCliente.alterar(cliente);
	            long cartaoFidelidade = cartaoMediator.gerarCartao(cliente);
	            return null;
	        }else {
	        	return erroValidacao;
	        }

	    }

	    private String validar(Cliente cliente) {
	        if (cliente == null) {
	            return "Cliente não pode ser nulo";
	        }

	        if (!ValidadorCPF.ehCpfValido(cliente.getCpf())) {
	            return "CPF inválido";
	        }

	        if (StringUtil.ehNuloOuBranco(cliente.getNomeCompleto())) {
	            return "Nome completo é obrigatório";
	        }

	        if (cliente.getSexo() == null) {
	            return "Sexo é obrigatório";
	        }

	        if (cliente.obterIdade() < 18) {
	            return "Cliente deve ter pelo menos 18 anos";
	        }

	        if (cliente.getRenda() < 0) {
	            return "Renda não pode ser negativa";
	        }

	        Endereco endereco = cliente.getEndereco();
	        if (endereco == null) {
	            return "Endereço é obrigatório";
	        }

	        if (endereco.getLogradouro().length() < 4) {
	            return "Logradouro deve ter pelo menos 4 caracteres";
	        }

	        if (endereco.getNumero() < 0) {
	            return "Número não pode ser negativo";
	        }

	        if (StringUtil.ehNuloOuBranco(endereco.getCidade())) {
	            return "Cidade é obrigatória";
	        }

	        if (StringUtil.ehNuloOuBranco(endereco.getEstado())) {
	            return "Estado é obrigatório";
	        }

	        if (StringUtil.ehNuloOuBranco(endereco.getPais())) {
	            return "País é obrigatório";
	        }

	        return null;
	    }
}
