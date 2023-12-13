package mvc.loja.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import mvc.loja.dto.Cliente;
import mvc.loja.dto.ItemPedido;

@Component
public class ItemPedidoDao {
    @Autowired
    private JdbcTemplate bd;

    // seleciona todos dos dados de itemPedido
    public List<ItemPedido> getAll(){
        String query = "select * from itemPedido";

        return bd.query(query, new BeanPropertyRowMapper<>(ItemPedido.class));
    }

    // seleciona todos dos dados de itemPedido
    public List<ItemPedido> getAllByPedido(long pedido){
        String query = "select * from itemPedido where id_pedido = ?";

        return bd.query(query, new BeanPropertyRowMapper<>(ItemPedido.class), new Object[] {pedido});
    }

    // pega um item pedido pelo id
    public ItemPedido getById(Long id){

        String query = "select * from itemPedido where id_itemPedido = ?";

        List<ItemPedido> itemPedidos = bd.query(query, new BeanPropertyRowMapper<>(ItemPedido.class), new Object[] {id});

        if(itemPedidos != null && itemPedidos.size() > 0){
            return itemPedidos.get(0);
        }else{
            return null;
        }
    }

    // adiciona o id em cada inserção
    public long getNextId(){

        //caso já tenha id adicionados
        String query = "select max(id_itemPedido) from itemPedido";

        // se ainda não há id adicionados
        if(bd.queryForObject(query, Long.class) == null){
            query = "select count(id_itemPedido) from itemPedido";
        }

        //pega a resposta da query e adiciona 1 para o novo id
        return bd.queryForObject(query, Long.class) + 1;

    }

    // insere os dados em item pedido
    public boolean insert(ItemPedido itemPedido){

        try {

            String query = "insert into itemPedido(id_itemPedido, id_pedido, id_produto, quantidade, valor_venda) values (?, ?, ?, ?, ?)";

            bd.update(query, new Object[]{
                itemPedido.getId_itemPedido(),
                itemPedido.getId_pedido(),
                itemPedido.getId_produto(),
                itemPedido.getQuantidade(),
                itemPedido.getValor_venda()
            });

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    // dá update em uma Categoria já existente
    public boolean update(ItemPedido itemPedido){

        try {
            
            String query = "update itemPedido set id_pedido = ?, id_produto = ?, quantidade = ?, valor_venda = ? where id_itemPedido = ?";

            bd.update(query, new Object[]{
                itemPedido.getId_pedido(),
                itemPedido.getId_produto(),
                itemPedido.getQuantidade(),
                itemPedido.getValor_venda(),
                itemPedido.getId_itemPedido()
            });

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    // deletar categoria pelo id
    public boolean delete(Long id){

        try {
            String query = "delete from itemPedido where id_itemPedido = ?";
    
            bd.update(query, new Object[]{id});
            
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
