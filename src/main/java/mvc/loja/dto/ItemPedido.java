package mvc.loja.dto;

import lombok.Data;

@Data
public class ItemPedido {
    
    public long id_itemPedido;
    public long id_pedido;
    public long id_produto;
    public int quantidade;
    public double valor_venda;

    private Produto produto;
}
