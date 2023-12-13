package mvc.loja.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mvc.loja.dao.ClienteDao;
import mvc.loja.dto.Cliente;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteDao cliDao;

    public List<Cliente> getAll(){
        return cliDao.getAll();
    }
    
    public Cliente getById(Long id){
        return cliDao.getById(id);
    }

    public boolean insert(Cliente clientes){
        clientes.setId_cliente(cliDao.getNextId());
        return cliDao.insert(clientes);
    }

    public boolean update(Cliente clientes){
        return cliDao.update(clientes);
    }

    public boolean delete(Long id){
        return cliDao.delete(id);
    }
}
