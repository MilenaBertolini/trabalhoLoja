package mvc.loja.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mvc.loja.dao.CategoriaDao;
import mvc.loja.dto.Categoria;

@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaDao catDao;

    public List<Categoria> getAll(){
        return catDao.getAll();
    }
    
    public Categoria getById(Long id){
        return catDao.getById(id);
    }

    public boolean insert(Categoria categoria){
        categoria.setId_categoria(catDao.getNextId());
        return catDao.insert(categoria);
    }

    public boolean update(Categoria categoria){
        return catDao.update(categoria);
    }

    public boolean delete(Long id){
        return catDao.delete(id);
    }
}
