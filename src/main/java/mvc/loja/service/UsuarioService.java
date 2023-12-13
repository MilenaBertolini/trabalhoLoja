package mvc.loja.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import mvc.loja.dao.UsuarioDao;
import mvc.loja.dto.Usuario;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private TipoUsuarioService tipoService;

    public List<Usuario> getAll(){
        List<Usuario> users = usuarioDao.getAll();

        users.forEach(u -> u.setTipoUsuario(tipoService.getById((u.getId_tipoUsuario()))));
        users.forEach(u -> u.setSenha("************"));

        return users;
    }

    
    public Usuario getById(Long id){
        Usuario user = usuarioDao.getById(id);

        user.setTipoUsuario(tipoService.getById((user.getId_tipoUsuario())));

        return user;
    }

    public Usuario getByEmail(String email){
        Usuario user = usuarioDao.getByEmail(email);
        user.setTipoUsuario(tipoService.getById((user.getId_tipoUsuario())));
        return user;
    }

    public boolean insert(Usuario usuarios){

        usuarios.setSenha(criptografia(usuarios.getSenha()));
        usuarios.setId_usuario(usuarioDao.getNextId());
        return usuarioDao.insert(usuarios);
    }

    private String criptografia(String senha){

        BCryptPasswordEncoder codificador = new BCryptPasswordEncoder();
        return codificador.encode(senha);
    }

    private boolean validaSenhas(String senha1, String senha2){

        BCryptPasswordEncoder codificador = new BCryptPasswordEncoder();

        return codificador.matches(senha1, senha2);
    }

    public boolean update(Usuario usuarios){
        return usuarioDao.update(usuarios);
    }

    public boolean delete(Long id){
        return usuarioDao.delete(id);
    }

    public boolean validaLogin(String email, String senha){

        Usuario usuario = usuarioDao.getByEmail(email);

        if(usuario == null){
            return false;
        }else if (validaSenhas(senha, usuario.getSenha())){
            return true;
        }

        return false;
    }
}
