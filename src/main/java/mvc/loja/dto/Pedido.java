package mvc.loja.dto;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class Pedido {
    
    private long id_pedido;
    private long id_cliente;
    private Date data_pedido;
    private double valor_pedido;
    private String observacao_pedido;

    private Cliente cliente;
    private List<ItemPedido> itensPedidos;
}
