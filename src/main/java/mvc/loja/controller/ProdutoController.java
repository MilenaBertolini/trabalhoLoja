package mvc.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import mvc.loja.dto.Produto;
import mvc.loja.service.CategoriaService;
import mvc.loja.service.ProdutoService;


@Controller
public class ProdutoController {
    
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private CategoriaService categoriaService;
    private static final String baseUrl = "produto";   
    private static final String baseAtributo = "produto";

    @GetMapping(baseUrl + "/getAll")
    public String getAll(Model model){
        
        model.addAttribute(baseAtributo, produtoService.getAll());
        model.addAttribute("categoria", categoriaService.getAll());

        return "listaProduto";
    }

    @GetMapping(baseUrl + "/getById")
    public String getById(Long id, Model model){
        
        model.addAttribute(baseAtributo, produtoService.getById(id));
        model.addAttribute("editando", true);
        model.addAttribute("categoria", categoriaService.getAll());

        return "produto";
    }

    @GetMapping(baseUrl + "/add")
    public String add(Model model){
        model.addAttribute("editando", false);
        model.addAttribute("categoria", categoriaService.getAll());

        return "produto";
    }

    @PostMapping(baseUrl + "/insert")
    public String insert(Produto produto, Model model){
        produtoService.insert(produto);

        return getAll(model);
    }
    
    @PostMapping(baseUrl + "/update")
    public String update(Produto produto, Model model){
        produtoService.update(produto);

        return getAll(model);
    }

    @RequestMapping(baseUrl + "/delete")
    public String delete(Long id, Model model){
        produtoService.delete(id);

        return getAll(model);
    }
    
}
