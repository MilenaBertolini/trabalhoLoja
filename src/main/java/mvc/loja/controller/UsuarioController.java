package mvc.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import mvc.loja.dto.Usuario;
import mvc.loja.entity.Login;
import mvc.loja.service.UsuarioService;


@Controller
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;
    private static final String baseUrl = "usuario";   
    private static final String baseAtributo = "usuario";

    @GetMapping()
    public String login(){
        return "login";
    }

    @GetMapping("home")
    public String home(){
        return "home";
    }

    @PostMapping(baseUrl + "/login")
    public String login(Login login, Model model){

        if(usuarioService.validaLogin(login.getEmail(), login.getSenha())){
            model.addAttribute("usuario", usuarioService.getByEmail(login.getEmail()));
            
            return home();
        }else{
            return "loginErro";
        }
    }

    @GetMapping(baseUrl + "/add")
    public String add(Model model){
        model.addAttribute("editando", false);

        return "usuario";
    }

    @GetMapping(baseUrl + "/getAll")
    public String getAll(Model model){
        
        model.addAttribute(baseAtributo, usuarioService.getAll());

        return "listaUsuario";
    }

    @GetMapping(baseUrl + "/getById")
    public String getById(Long id, Model model){
        
        model.addAttribute(baseAtributo, usuarioService.getById(id));
        model.addAttribute("editando", true);

        return "usuario";
    }

    @PostMapping(baseUrl + "/insert")
    public String insert(Usuario usuario, Model model){
        usuarioService.insert(usuario);

        return getAll(model);
    }
    
    @PostMapping(baseUrl + "/update")
    public String update(Usuario usuario, Model model){
        usuarioService.update(usuario);

        return getAll(model);
    }

    @RequestMapping(baseUrl + "/delete")
    public String delete(Long id, Model model){
        usuarioService.delete(id);

        return getAll(model);
    }
    
}
