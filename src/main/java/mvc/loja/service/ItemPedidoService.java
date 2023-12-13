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

    public List<ItemPedido> getAll(){
        return cliDao.getAll();
    }

    public List<ItemPedido> getAllByPedido(long pedido){
        return cliDao.getAllByPedido(pedido);
    }
    
    public ItemPedido getById(Long id){
        ItemPedido item = cliDao.getById(id);
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
