package mvc.loja.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import mvc.loja.dto.Categoria;



@Component
public class CategoriaDao {

    @Autowired
    private JdbcTemplate bd;

    // seleciona todos dos dados da Categoria
    public List<Categoria> getAll(){
        String query = "select * from categoria";

        return bd.query(query, new BeanPropertyRowMapper<>(Categoria.class));
    }

    // pega uma Categoria pelo id
    public Categoria getById(Long id){

        String query = "select * from categoria where id_categoria = ?";

        List<Categoria> categorias = bd.query(query, new BeanPropertyRowMapper<>(Categoria.class), new Object[] {id});

        if(categorias != null && categorias.size() > 0){
            return categorias.get(0);
        }else{
            return null;
        }
    }

    // adiciona o id em cada inserção
    public long getNextId(){

        //caso já tenha id adicionados
        String query = "select max(id_categoria) from categoria";

        // se ainda não há id adicionados
        if(bd.queryForObject(query, Long.class) == null){
            query = "select count(id_categoria) from categoria";
        }

        //pega a resposta da query e adiciona 1 para o novo id
        return bd.queryForObject(query, Long.class) + 1;

    }

    // insere os dados em Categoria
    public boolean insert(Categoria categoria){

        try {

            String query = "insert into categoria(id_categoria, nome) values (?, ?)";

            bd.update(query, new Object[]{
                categoria.getId_categoria(),
                categoria.getNome()
            });

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    // dá update em uma Categoria já existente
    public boolean update(Categoria categoria){

        try {
            
            String query = "update categoria set nome = ? where id_categoria = ?";

            bd.update(query, new Object[]{
                categoria.getNome(),
                categoria.getId_categoria()
            });

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    // deletar categoria pelo id
    public boolean delete(Long id){

        try {
            String query = "delete from categoria where id_categoria = ?";
    
            bd.update(query, new Object[]{id});
            
        } catch (Exception e) {
            return false;
        }

        return true;
    }
    
}
