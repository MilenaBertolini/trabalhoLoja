package mvc.loja.dto;

import lombok.Data;

@Data
public class ItemPedido {

    public ItemPedido(){}
    public ItemPedido(long id_pedido, long id_produto, int quantidade, double valor_venda, Produto produto){
        this.id_pedido = id_pedido;
        this.id_produto = id_produto;
        this.quantidade = quantidade;
        this.valor_venda = valor_venda;
        this.produto = produto;
    }

    
    public long id_itemPedido;
    public long id_pedido;
    public long id_produto;
    public int quantidade;
    public double valor_venda;

    private Produto produto;
}
