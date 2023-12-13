package mvc.loja.dto;

import lombok.Data;

@Data
public class Produto {
    
    private long id_produto;
    private String nome;
    private String descricao;
    private double preco_venda;
    private double preco_compra;
    private int quantidade;
    private long id_categoria;

    private Categoria categoria;
}
