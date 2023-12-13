package mvc.loja.dto;

import lombok.Data;

@Data
public class Cliente {
    
    private long id_cliente;
    private String nome;
    private String email;
    private String endereco;
    private String telefone;
    private String obervacao_cliente;
    
}
