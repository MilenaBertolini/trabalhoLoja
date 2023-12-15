package mvc.loja.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mvc.loja.dao.PedidoDao;
import mvc.loja.dto.ItemPedido;
import mvc.loja.dto.Pedido;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoDao cliDao;

    @Autowired
    private ItemPedidoService itemPedidoService;

    public List<Pedido> getAll(){
        return cliDao.getAll();
    }
    
    public Pedido getById(Long id){
        return cliDao.getById(id);
    }

    private Pedido getLast(){
        return cliDao.getLast();
    }

    public Pedido insert(Pedido pedidos){
        pedidos.setId_pedido(cliDao.getNextId());
        if(cliDao.insert(pedidos)){
            return getLast();
        }
        return null;
    }

    public boolean update(Pedido pedidos){
        return cliDao.update(pedidos);
    }

    public boolean delete(Long id){
        List<ItemPedido> itens = itemPedidoService.getAllByPedido(id);
        itens.forEach(i -> itemPedidoService.delete(i.getId_itemPedido()));
        
        return cliDao.delete(id);
    }
}
