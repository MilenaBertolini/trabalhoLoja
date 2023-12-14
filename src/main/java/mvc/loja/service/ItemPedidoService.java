package mvc.loja.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mvc.loja.dao.ItemPedidoDao;
import mvc.loja.dto.ItemPedido;

@Service
public class ItemPedidoService {
    
    @Autowired
    private ItemPedidoDao cliDao;

    @Autowired
    private ProdutoService produtoService;

    public List<ItemPedido> getAll(){
        List<ItemPedido> itens = cliDao.getAll();

        itens.forEach(i -> i.setProduto(produtoService.getById(i.getId_produto())));

        return itens;
    }

    public List<ItemPedido> getAllByPedido(long pedido){
        List<ItemPedido> itens = cliDao.getAllByPedido(pedido);

        itens.forEach(i -> i.setProduto(produtoService.getById(i.getId_produto())));

        return itens;
    }
    
    public ItemPedido getById(Long id){
        ItemPedido item = cliDao.getById(id);

        item.setProduto(produtoService.getById(item.getId_produto()));

        return item;
    }

    public boolean insert(ItemPedido itemPedidos){
        itemPedidos.setId_itemPedido(cliDao.getNextId());
        return cliDao.insert(itemPedidos);
    }

    public boolean update(ItemPedido itemPedidos){
        return cliDao.update(itemPedidos);
    }

    public boolean delete(Long id){
        return cliDao.delete(id);
    }
}
