package mvc.loja.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import mvc.loja.dto.ItemPedido;
import mvc.loja.dto.Pedido;
import mvc.loja.dto.Produto;
import mvc.loja.entity.PedidoEntity;
import mvc.loja.service.ClienteService;
import mvc.loja.service.ItemPedidoService;
import mvc.loja.service.PedidoService;
import mvc.loja.service.ProdutoService;


@Controller
public class PedidoController {
    
    @Autowired
    private PedidoService pedidoService;
    
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ItemPedidoService itemService;

    @Autowired
    private ProdutoService produtoService;

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
        model.addAttribute("produto", produtoService.getAll());
        model.addAttribute("cliente", clienteService.getAll());


        return "criarPedido";
    }

    @PostMapping(baseUrl + "/insert")
    public ResponseEntity insert(@RequestBody PedidoEntity pedido, Model model){
        Date currentDate = new Date(System.currentTimeMillis());
        Pedido ped = new Pedido();
        ped.setData_pedido(currentDate);
        ped.setId_cliente(pedido.getCliente());
        ped.setObservacao_pedido("Pedido feito com sucesso!");

        Pedido pedInserted = pedidoService.insert(ped);

        List<ItemPedido> itens = new ArrayList<>();
        pedido.getItens().forEach(i -> {
            Produto prod = produtoService.getById(i.getItem());
            itens.add(new ItemPedido(pedInserted.getId_pedido(), i.getItem(), i.getQuantidade(), prod.getPreco_venda(), prod));
        });

        double valorPedido = 0;

        for (ItemPedido i : itens) {
            itemService.insert(i);
            i.getProduto().setQuantidade(i.getProduto().getQuantidade()-i.getQuantidade());
            produtoService.update(i.getProduto());
            valorPedido += i.getProduto().getPreco_venda() * i.getQuantidade();
        }

        pedInserted.setValor_pedido(valorPedido);
        pedidoService.update(pedInserted);

        return new ResponseEntity(HttpStatus.OK);
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
