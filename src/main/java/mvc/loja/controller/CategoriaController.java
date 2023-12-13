package mvc.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import mvc.loja.dto.Categoria;
import mvc.loja.service.CategoriaService;


@Controller
public class CategoriaController {
    private static final String baseUrl = "categoria";   
    private static final String baseAtributo = "categoria";
    
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping(baseUrl + "/getAll")
    public String getAll(Model model){
        
        model.addAttribute(baseAtributo, categoriaService.getAll());

        return "listaCategoria";
    }

    @GetMapping(baseUrl + "/getById")
    public String getById(Long id, Model model){
        
        model.addAttribute(baseAtributo, categoriaService.getById(id));
        model.addAttribute("editando", true);

        return "categoria";
    }

    @GetMapping(baseUrl + "/add")
    public String add(Model model){
        model.addAttribute("editando", false);

        return "categoria";
    }

    @PostMapping(baseUrl + "/insert")
    public String insert(Categoria categoria, Model model){
        categoriaService.insert(categoria);

        return getAll(model);
    }
    
    @PostMapping(baseUrl + "/update")
    public String update(Categoria categoria, Model model){
        categoriaService.update(categoria);

        return getAll(model);
    }

    @RequestMapping(baseUrl + "/delete")
    public String delete(Long id, Model model){
        categoriaService.delete(id);

        return getAll(model);
    }
    
}
