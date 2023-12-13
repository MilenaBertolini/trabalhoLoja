package mvc.loja.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import mvc.loja.dto.Pedido;
import mvc.loja.service.ClienteService;
import mvc.loja.service.ItemPedidoService;
import mvc.loja.service.PedidoService;


@Controller
public class PedidoController {
    
    @Autowired
    private PedidoService pedidoService;
    
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ItemPedidoService itemService;

    private static final String baseUrl = "pedido";   
    private static final String baseAtributo = "pedido";

    @GetMapping(baseUrl + "/getAll")
    public String getAll(Model model){
        
        List<Pedido> pedidos = pedidoService.getAll();
        pedidos.forEach(p -> p.setCliente(clienteService.getById(p.getId_cliente())));
        pedidos.forEach(p -> p.setItensPedidos(itemService.getAllByPedido(p.getId_pedido())));

        model.addAttribute(baseAtributo, pedidos);

        return "listaPedido";
    }

    @GetMapping(baseUrl + "/getById")
    public String getById(Long id, Model model){
        
        Pedido pedido = pedidoService.getById(id);

        pedido.setCliente(clienteService.getById(pedido.getId_cliente()));
        pedido.setItensPedidos(itemService.getAllByPedido(pedido.getId_pedido()));
        
        model.addAttribute(baseAtributo, pedido);

        return "pedido";
    }

    @GetMapping(baseUrl + "/add")
    public String add(Model model){
        model.addAttribute("editando", false);

        return "pedido";
    }

    @PostMapping(baseUrl + "/insert")
    public String insert(Pedido pedido, Model model){
        pedidoService.insert(pedido);

        return getAll(model);
    }
    
    @PostMapping(baseUrl + "/update")
    public String update(Pedido pedido, Model model){
        pedidoService.update(pedido);

        return getAll(model);
    }

    @RequestMapping(baseUrl + "/delete")
    public String delete(Long id, Model model){
        pedidoService.delete(id);

        return getAll(model);
    }
    
}
