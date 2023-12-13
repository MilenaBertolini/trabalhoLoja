package mvc.loja.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mvc.loja.dao.ProdutoDao;
import mvc.loja.dto.Produto;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoDao cliDao;

    @Autowired
    private CategoriaService categoriaService;

    public List<Produto> getAll(){
        List<Produto> produtos = cliDao.getAll();


        produtos.forEach(p -> p.setCategoria(categoriaService.getById(p.getId_categoria())));

        return produtos;
    }
    
    public Produto getById(Long id){
        Produto produto = cliDao.getById(id);

        produto.setCategoria(categoriaService.getById(produto.getId_categoria()));

        return produto;
    }

    public boolean insert(Produto produtos){
        produtos.setId_produto(cliDao.getNextId());

        return cliDao.insert(produtos);
    }

    public boolean update(Produto produtos){
        return cliDao.update(produtos);
    }

    public boolean delete(Long id){
        return cliDao.delete(id);
    }
}
