package br.gov.cesarschool.poo.fidelidade.cartao.entidade;

public enum TipoResgate {
    PRODUTO(1, "Produto"),
    SERVICO(2, "Serviço"),
    VIAGEM(3, "Viagem");

    private int codigo;
    private String descricao;

    TipoResgate(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
}
