package mvc.loja.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import mvc.loja.dto.Pedido;

@Component
public class PedidoDao {
    @Autowired
    private JdbcTemplate bd;

    // seleciona todos dos dados de pedido
    public List<Pedido> getAll(){
        String query = "select * from pedido";

        return bd.query(query, new BeanPropertyRowMapper<>(Pedido.class));
    }

    // pega um item pedido pelo id
    public Pedido getById(Long id){

        String query = "select * from pedido where id_pedido = ?";

        List<Pedido> pedidos = bd.query(query, new BeanPropertyRowMapper<>(Pedido.class), new Object[] {id});

        if(pedidos != null && pedidos.size() > 0){
            return pedidos.get(0);
        }else{
            return null;
        }
    }

    public Pedido getLast(){

        String query = "select * from pedido order by id_pedido desc";

        List<Pedido> pedidos = bd.query(query, new BeanPropertyRowMapper<>(Pedido.class));

        if(pedidos != null && pedidos.size() > 0){
            return pedidos.get(0);
        }else{
            return null;
        }
    }

    // adiciona o id em cada inserção
    public long getNextId(){

        //caso já tenha id adicionados
        String query = "select max(id_pedido) from pedido";

        // se ainda não há id adicionados
        if(bd.queryForObject(query, Long.class) == null){
            query = "select count(id_pedido) from pedido";
        }

        //pega a resposta da query e adiciona 1 para o novo id
        return bd.queryForObject(query, Long.class) + 1;

    }

    // insere os dados em item pedido
    public boolean insert(Pedido pedido){

        try {

            String query = "insert into pedido(id_pedido, id_cliente, valor_pedido, data_pedido, observacao_pedido) values (?, ?, ?, ?, ?)";

            bd.update(query, new Object[]{
                pedido.getId_pedido(),
                pedido.getId_cliente(),
                pedido.getValor_pedido(),
                pedido.getData_pedido(),
                pedido.getObservacao_pedido(),
            });

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    // dá update em uma Categoria já existente
    public boolean update(Pedido pedido){

        try {
            
            String query = "update pedido set id_cliente = ?, data_pedido = ?, observacao_pedido = ?, valor_pedido = ? where id_pedido = ?";

            bd.update(query, new Object[]{
                pedido.getId_cliente(),
                pedido.getData_pedido(),
                pedido.getObservacao_pedido(),
                pedido.getValor_pedido(),
                pedido.getId_pedido()
            });

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    // deletar categoria pelo id
    public boolean delete(Long id){

        try {
            String query = "delete from pedido where id_pedido = ?";
    
            bd.update(query, new Object[]{id});
            
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
