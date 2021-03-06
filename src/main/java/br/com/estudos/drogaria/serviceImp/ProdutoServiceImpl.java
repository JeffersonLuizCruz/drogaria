package br.com.estudos.drogaria.serviceImp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estudos.drogaria.Iservice.IprodutoService;
import br.com.estudos.drogaria.exception.ProdutoException;
import br.com.estudos.drogaria.model.Produto;
import br.com.estudos.drogaria.repository.ProdutoRepository;

@Service
public class ProdutoServiceImpl implements IprodutoService{
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	public Optional<Produto> getProdutoById(Short id) throws ProdutoException{
		Optional<Produto> buscarId = produtoRepository.findById(id);
		if(buscarId.isEmpty()) {
			throw new ProdutoException("Produto não encontrado", "Campo id");
		}
		return buscarId;
	}
	@Override
	public Produto salvarProduto(Produto produto) throws ProdutoException {
		Produto existeProduto = produtoRepository.findByNome(produto);
		if(existeProduto != null && !existeProduto.equals(produto)) {
			throw new ProdutoException("Produto já existe no sistema", "Campo Nome");
		}
		return produtoRepository.save(existeProduto);
	}

}
