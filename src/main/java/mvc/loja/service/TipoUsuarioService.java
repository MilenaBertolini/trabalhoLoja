package mvc.loja.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mvc.loja.dao.TipoUsuarioDao;
import mvc.loja.dto.TipoUsuario;

@Service
public class TipoUsuarioService {
    
    @Autowired
     private TipoUsuarioDao tipoDao;

    public List<TipoUsuario> getAll(){
        return tipoDao.getAll();
    }
    
    public TipoUsuario getById(Long id){
        return tipoDao.getById(id);
    }

    public boolean insert(TipoUsuario tipoUsuario){
        tipoUsuario.setId_tipoUsuario(tipoDao.getNextId());
        return tipoDao.insert(tipoUsuario);
    }

    public boolean update(TipoUsuario tipoUsuario){
        return tipoDao.update(tipoUsuario);
    }

    public boolean delete(Long id){
        return tipoDao.delete(id);
    }
}
