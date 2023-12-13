package mvc.loja.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import mvc.loja.dto.Pedido;
import mvc.loja.dto.Produto;

@Component
public class ProdutoDao {
    @Autowired
    private JdbcTemplate bd;

    // seleciona todos dos dados de produto
    public List<Produto> getAll(){
        String query = "select * from produto";

        return bd.query(query, new BeanPropertyRowMapper<>(Produto.class));

    }

    // pega um item produto pelo id
    public Produto getById(Long id){

        String query = "select * from produto where id_produto = ?";

        List<Produto> produtos = bd.query(query, new BeanPropertyRowMapper<>(Produto.class), new Object[] {id});

        if(produtos != null && produtos.size() > 0){
            return produtos.get(0);
        }else{
            return null;
        }
    }

    // adiciona o id em cada inserção
    public long getNextId(){

        //caso já tenha id adicionados
        String query = "select max(id_produto) from produto";

        // se ainda não há id adicionados
        if(bd.queryForObject(query, Long.class) == null){
            query = "select count(id_produto) from produto";
        }

        //pega a resposta da query e adiciona 1 para o novo id
        return bd.queryForObject(query, Long.class) + 1;

    }

    // insere os dados em item produto
    public boolean insert(Produto produto){

        try {

            String query = "insert into produto(id_produto,nome,descricao,preco_venda,preco_compra,quantidade,id_categoria) values (?, ?, ?, ?, ?, ?, ?)";

            bd.update(query, new Object[]{
                produto.getId_produto(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco_venda(),
                produto.getPreco_compra(),
                produto.getQuantidade(),
                produto.getId_categoria()
            });

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    // dá update em uma Categoria já existente
    public boolean update(Produto produto){

        try {
            
            String query = "update produto set nome = ?,descricao = ?,preco_venda = ?,preco_compra = ?,quantidade = ?,id_categoria = ? where id_produto = ?";

            bd.update(query, new Object[]{
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco_venda(),
                produto.getPreco_compra(),
                produto.getQuantidade(),
                produto.getId_categoria(),
                produto.getId_produto()
            });

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    // deletar categoria pelo id
    public boolean delete(Long id){

        try {
            String query = "delete from produto where id_produto = ?";
    
            bd.update(query, new Object[]{id});
            
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
