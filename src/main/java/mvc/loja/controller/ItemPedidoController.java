package mvc.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import mvc.loja.dto.ItemPedido;
import mvc.loja.service.ItemPedidoService;


@Controller
public class ItemPedidoController {
    
    @Autowired
    private ItemPedidoService itemPedidoService;
    private static final String baseUrl = "itemPedido";   
    private static final String baseAtributo = "itemPedido";

    @GetMapping(baseUrl + "/getAll")
    public String getAll(Model model){
        
        model.addAttribute(baseAtributo, itemPedidoService.getAll());

        return "listaItemPedido";
    }

    @GetMapping(baseUrl + "/getById")
    public String getById(Long id, Model model){
        
        model.addAttribute(baseAtributo, itemPedidoService.getById(id));
        model.addAttribute("editando", true);

        return "itemPedido";
    }

    @GetMapping(baseUrl + "/add")
    public String add(Model model){
        model.addAttribute("editando", false);

        return "cliente";
    }

    @PostMapping(baseUrl + "/insert")
    public String insert(ItemPedido itemPedido, Model model){
        itemPedidoService.insert(itemPedido);

        return getAll(model);
    }
    
    @PostMapping(baseUrl + "/update")
    public String update(ItemPedido itemPedido, Model model){
        itemPedidoService.update(itemPedido);

        return getAll(model);
    }

    @RequestMapping(baseUrl + "/delete")
    public String delete(Long id, Model model){
        itemPedidoService.delete(id);

        return getAll(model);
    }
    
}
