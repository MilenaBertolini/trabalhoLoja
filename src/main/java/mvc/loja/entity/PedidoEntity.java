package mvc.loja.entity;
import java.util.List;

import lombok.Data;

@Data
public class PedidoEntity {
    private Long cliente;
    private List<Item> itens;
}
