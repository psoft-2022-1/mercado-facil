package com.ufcg.psoft.mercadofacil.service;

import java.util.List;

import com.ufcg.psoft.mercadofacil.dto.ClienteDTO;
import com.ufcg.psoft.mercadofacil.exception.ClienteAlreadyCreatedException;
import com.ufcg.psoft.mercadofacil.exception.ClienteNotFoundException;

public interface ClienteService {

	public ClienteDTO getClienteById(Long id) throws ClienteNotFoundException;
	
	public ClienteDTO getClienteByCpf(Long cpf) throws ClienteNotFoundException;
	
	public void removerClienteCadastrado(Long id) throws ClienteNotFoundException;

	public List<ClienteDTO> listarClientes();
	
	public ClienteDTO criaCliente(ClienteDTO clienteDTO) throws ClienteAlreadyCreatedException;
	
	public ClienteDTO atualizaCliente(Long id, ClienteDTO clienteDTO) throws ClienteNotFoundException;

}
