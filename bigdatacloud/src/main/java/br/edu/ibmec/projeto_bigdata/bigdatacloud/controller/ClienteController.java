package br.edu.ibmec.projeto_bigdata.bigdatacloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import br.edu.ibmec.projeto_bigdata.bigdatacloud.model.Cliente;
import br.edu.ibmec.projeto_bigdata.bigdatacloud.model.Endereco;
import br.edu.ibmec.projeto_bigdata.bigdatacloud.service.ClienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")

public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> adicionarCliente(@Valid @RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.adicionarCliente(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.atualizarCliente(id, cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerCliente(@PathVariable Long id) {
        clienteService.removerCliente(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{clienteId}/enderecos")
    public ResponseEntity<Cliente> addEndereco(@PathVariable Long clienteId, @Valid @RequestBody Endereco endereco) {
    Cliente cliente = clienteService.addEndereco(clienteId, endereco);  
    return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @GetMapping("/{id}/enderecos")
    public ResponseEntity<List<Endereco>> listarEnderecos(@PathVariable Long id) {
        List<Endereco> enderecos = clienteService.listarEnderecosPorCliente(id);
        if (enderecos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(enderecos);
    }
}
