package br.edu.ibmec.projeto_bigdata.bigdatacloud.controller;
import java.util.List;
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


import br.edu.ibmec.projeto_bigdata.bigdatacloud.model.Endereco;
import br.edu.ibmec.projeto_bigdata.bigdatacloud.service.EnderecoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public List<Endereco> getAllEnderecos() {
        return enderecoService.findAll();
    }

    @PostMapping
    public Endereco createEndereco(@Valid @RequestBody Endereco endereco) {
        return enderecoService.save(endereco);
    }

    @PutMapping("/{id}")
    public Endereco updateEndereco(@PathVariable Long id, @Valid @RequestBody Endereco endereco) {
        return enderecoService.update(id, endereco);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEndereco(@PathVariable Long id) {
        enderecoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{clienteId}/enderecos")
    public ResponseEntity<Endereco> incluir(@PathVariable("clienteId") Long clienteId, @RequestBody Endereco endereco) {
        Endereco novoEndereco = enderecoService.createEndereco(clienteId, endereco);
        return new ResponseEntity<>(novoEndereco, HttpStatus.CREATED);
    }
}
