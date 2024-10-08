package br.edu.ibmec.projeto_bigdata.bigdatacloud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ibmec.projeto_bigdata.bigdatacloud.model.Cliente;
import br.edu.ibmec.projeto_bigdata.bigdatacloud.model.Endereco;
import br.edu.ibmec.projeto_bigdata.bigdatacloud.repository.ClienteRepository;
import br.edu.ibmec.projeto_bigdata.bigdatacloud.repository.EnderecoRepository;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Endereco> findAll() {
        return enderecoRepository.findAll();
    }

    public Endereco save(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public Endereco update(Long id, Endereco enderecoAtualizado) {
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Endereço não encontrado"));
        endereco.setRua(enderecoAtualizado.getRua());
        endereco.setNumero(enderecoAtualizado.getNumero());
        endereco.setBairro(enderecoAtualizado.getBairro());
        endereco.setCidade(enderecoAtualizado.getCidade());
        endereco.setEstado(enderecoAtualizado.getEstado());
        endereco.setCep(enderecoAtualizado.getCep());
        return enderecoRepository.save(endereco);
    }

    public void delete(Long id) {
        enderecoRepository.deleteById(id);
    }

    public Endereco createEndereco(Long clienteId, Endereco endereco) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        endereco.setCliente(cliente);
        return enderecoRepository.save(endereco);
    }
    
}

