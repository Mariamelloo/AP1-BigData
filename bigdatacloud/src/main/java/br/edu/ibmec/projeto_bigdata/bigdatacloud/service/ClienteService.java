package br.edu.ibmec.projeto_bigdata.bigdatacloud.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import br.edu.ibmec.projeto_bigdata.bigdatacloud.exception.ResourceNotFoundException;
import br.edu.ibmec.projeto_bigdata.bigdatacloud.model.Cliente;
import br.edu.ibmec.projeto_bigdata.bigdatacloud.model.Endereco;
import br.edu.ibmec.projeto_bigdata.bigdatacloud.repository.ClienteRepository;



@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente adicionarCliente(Cliente cliente) {
        if (clienteRepository.findByEmail(cliente.getEmail()).isPresent()) {
            throw new RuntimeException("E-mail já cadastrado.");
        }

        if (clienteRepository.findByCpf(cliente.getCpf()).isPresent()) {
            throw new RuntimeException("CPF já cadastrado.");
        }

        return clienteRepository.save(cliente);
    }

    public Cliente atualizarCliente(Long id, Cliente clienteAtualizado) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));

        cliente.setNome(clienteAtualizado.getNome());
        cliente.setEmail(clienteAtualizado.getEmail());
        cliente.setDataNascimento(clienteAtualizado.getDataNascimento());
        cliente.setTelefone(clienteAtualizado.getTelefone());

        return clienteRepository.save(cliente);
    }

    public void removerCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    public Cliente addEndereco(Long clienteId, Endereco endereco) {
    Cliente cliente = clienteRepository.findById(clienteId)
            .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
    
    endereco.setCliente(cliente);  
    cliente.getEnderecos().add(endereco);  
    clienteRepository.save(cliente);  
    return cliente;
    }

    public List<Endereco> listarEnderecosPorCliente(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com id " + clienteId));
        return cliente.getEnderecos(); 
    }
}
