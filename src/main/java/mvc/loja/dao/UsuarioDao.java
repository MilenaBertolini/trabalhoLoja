package mvc.loja.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import mvc.loja.dto.Usuario;

@Component
public class UsuarioDao {
    @Autowired
    private JdbcTemplate bd;

    // seleciona todos dos dados de usuario
    public List<Usuario> getAll(){
        String query = "select * from usuario";

        return bd.query(query, new BeanPropertyRowMapper<>(Usuario.class));
    }

    // pega um item usuario pelo id
    public Usuario getById(Long id){

        String query = "select * from usuario where id_usuario = ?";

        List<Usuario> usuarios = bd.query(query, new BeanPropertyRowMapper<>(Usuario.class), new Object[] {id});

        if(usuarios != null && usuarios.size() > 0){
            return usuarios.get(0);
        }else{
            return null;
        }
    }

    // adiciona o id em cada inserção
    public long getNextId(){

        //caso já tenha id adicionados
        String query = "select max(id_usuario) from usuario";

        // se ainda não há id adicionados
        if(bd.queryForObject(query, Long.class) == null){
            query = "select count(id_usuario) from usuario";
        }

        //pega a resposta da query e adiciona 1 para o novo id
        return bd.queryForObject(query, Long.class) + 1;

    }

    // insere os dados em item usuario
    public boolean insert(Usuario usuario){

        try {

            String query = "insert into usuario(id_usuario,nome,email,senha,id_tipoUsuario) values (?, ?, ?, ?, ?)";

            bd.update(query, new Object[]{
                usuario.getId_usuario(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getId_tipoUsuario(),
            });

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    // dá update em uma Categoria já existente
    public boolean update(Usuario usuario){

        try {
            
            String query = "update usuario set nome = ?,email = ?,id_tipoUsuario = ? where id_usuario = ?";

            bd.update(query, new Object[]{
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getId_tipoUsuario(),
                usuario.getId_usuario()
            });

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    // deletar categoria pelo id
    public boolean delete(Long id){

        try {
            String query = "delete from usuario where id_usuario = ?";
    
            bd.update(query, new Object[]{id});
            
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public Usuario getByEmail(String email){

        String query = "select * from usuario where email = ?";

        List<Usuario> usuarios = bd.query(query, new BeanPropertyRowMapper<>(Usuario.class), new Object[] {email});

        if(usuarios != null && usuarios.size() > 0){
            return usuarios.get(0);
        }else{
            return null;
        }
    }
    
}
