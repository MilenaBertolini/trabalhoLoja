package mvc.loja.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import mvc.loja.dto.TipoUsuario;

@Component
public class TipoUsuarioDao {

    @Autowired
    private JdbcTemplate bd;

    // seleciona todos dos dados do tipoUsuario
    public List<TipoUsuario> getAll(){
        String query = "select * from tipoUsuario";

        return  bd.query(query, new BeanPropertyRowMapper<>(TipoUsuario.class));
    }

    // pega um tipoUsuario pelo id
    public TipoUsuario getById(Long id){

        String query = "select * from tipoUsuario where id_tipoUsuario = ?";

        List<TipoUsuario> tipoUsuarios = bd.query(query, new BeanPropertyRowMapper<>(TipoUsuario.class), new Object[] {id});

        if(tipoUsuarios != null && tipoUsuarios.size() > 0){
            return tipoUsuarios.get(0);
        }else{
            return null;
        }
    }

    // adiciona o id em cada inserção
    public long getNextId(){

        //caso já tenha id adicionados
        String query = "select max(id_tipoUsuario) from tipoUsuario";

        // se ainda não há id adicionados
        if(bd.queryForObject(query, Long.class) == null){
            query = "select count(id_tipoUsuario) from tipoUsuario";
        }

        //pega a resposta da query e adiciona 1 para o novo id
        return bd.queryForObject(query, Long.class) + 1;

    }

    // insere os dados em Tipo usuario
    public boolean insert(TipoUsuario tipoUsuario){

        try {

            String query = "insert into tipoUsuario(id_tipoUsuario, nome) values (?, ?)";

            bd.update(query, new Object[]{
                tipoUsuario.getId_tipoUsuario(),
                tipoUsuario.getNome()
            });

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    // dá update em um Tipo usuario já existente
    public boolean update(TipoUsuario tipoUsuario){

        try {
            
            String query = "update tipoUsuario set nome = ? where id_tipoUsuario = ?";

            bd.update(query, new Object[]{
                tipoUsuario.getNome(),
                tipoUsuario.getId_tipoUsuario()
            });

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    // deletar categoria pelo id
    public boolean delete(Long id){

        try {
            String query = "delete from tipoUsuario where id_tipoUsuario = ?";
    
            bd.update(query, new Object[]{id});
            
        } catch (Exception e) {
            return false;
        }

        return true;
    }
    
}
