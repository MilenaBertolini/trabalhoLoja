package mvc.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import mvc.loja.dto.TipoUsuario;
import mvc.loja.service.TipoUsuarioService;


@Controller
public class TipoUsuarioController {
    
    @Autowired
    private TipoUsuarioService tipoUsuarioService;
    private static final String baseUrl = "tipoUsuario";   
    private static final String baseAtributo = "tipoUsuario";

    @GetMapping(baseUrl + "/getAll")
    public String getAll(Model model){
        
        model.addAttribute(baseAtributo, tipoUsuarioService.getAll());

        return "listaTipoUsuario";
    }

    @GetMapping(baseUrl + "/getById")
    public String getById(Long id, Model model){
        
        model.addAttribute(baseAtributo, tipoUsuarioService.getById(id));
        model.addAttribute("editando", true);

        return "tipoUsuario";
    }

    @GetMapping(baseUrl + "/add")
    public String add(Model model){
        model.addAttribute("editando", false);

        return "tipoUsuario";
    }

    @PostMapping(baseUrl + "/insert")
    public String insert(TipoUsuario tipoUsuario, Model model){
        tipoUsuarioService.insert(tipoUsuario);

        return getAll(model);
    }
    
    @PostMapping(baseUrl + "/update")
    public String update(TipoUsuario tipoUsuario, Model model){
        tipoUsuarioService.update(tipoUsuario);

        return getAll(model);
    }

    @RequestMapping(baseUrl + "/delete")
    public String delete(Long id, Model model){
        tipoUsuarioService.delete(id);

        return getAll(model);
    }
    
}
