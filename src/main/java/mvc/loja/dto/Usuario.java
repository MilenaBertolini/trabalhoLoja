package mvc.loja.dto;

import lombok.Data;

@Data
public class Usuario {
    
    private long id_usuario;
    private String nome;    
    private String email;
    private String senha;
    private long id_tipoUsuario;

    private TipoUsuario tipoUsuario;
}
