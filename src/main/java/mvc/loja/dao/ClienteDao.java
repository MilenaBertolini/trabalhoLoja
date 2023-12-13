package mvc.loja.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import mvc.loja.dto.Cliente;

@Component
public class ClienteDao {
    
    @Autowired
    private JdbcTemplate bd;

    // seleciona todos dos dados da Categoria
    public List<Cliente> getAll(){
        String query = "select * from cliente";

        return bd.query(query, new BeanPropertyRowMapper<>(Cliente.class));
    }

    // pega uma Categoria pelo id
    public Cliente getById(Long id){

        String query = "select * from cliente where id_cliente = ?";

        List<Cliente> clientes = bd.query(query, new BeanPropertyRowMapper<>(Cliente.class), new Object[] {id});

        if(clientes != null && clientes.size() > 0){
            return clientes.get(0);
        }else{
            return null;
        }
    }

    // adiciona o id em cada inserção
    public long getNextId(){

        //caso já tenha id adicionados
        String query = "select max(id_cliente) from cliente";

        // se ainda não há id adicionados
        if(bd.queryForObject(query, Long.class) == null){
            query = "select count(id_cliente) from cliente";
        }

        //pega a resposta da query e adiciona 1 para o novo id
        return bd.queryForObject(query, Long.class) + 1;

    }

    // insere os dados em Categoria
    public boolean insert(Cliente clientes){

        try {

            String query = "insert into cliente(id_cliente, nome, email, endereco, telefone, obervacao_cliente) values (?, ?, ?, ?, ?, ?)";

            bd.update(query, new Object[]{
                clientes.getId_cliente(),
                clientes.getNome(),
                clientes.getEmail(),
                clientes.getEndereco(),
                clientes.getTelefone(),
                clientes.getObervacao_cliente()
            });

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    // dá update em uma Categoria já existente
    public boolean update(Cliente clientes){

        try {
            
            String query = "update cliente set nome = ?, email = ?, endereco = ?, telefone = ?, obervacao_cliente = ? where id_cliente = ?";

            bd.update(query, new Object[]{
                clientes.getNome(),
                clientes.getEmail(),
                clientes.getEndereco(),
                clientes.getTelefone(),
                clientes.getObervacao_cliente(),
                clientes.getId_cliente()
            });

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    // deletar categoria pelo id
    public boolean delete(Long id){

        try {
            String query = "delete from cliente where id_cliente = ?";
    
            bd.update(query, new Object[]{id});
            
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
