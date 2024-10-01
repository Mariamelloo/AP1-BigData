package br.edu.ibmec.projeto_bigdata.bigdatacloud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "Rua é obrigatória.")
    @Size(min = 3, max = 255)
    private String rua;

    @NotBlank(message = "Número é obrigatório.")
    private String numero;

    @NotBlank(message = "Bairro é obrigatório.")
    @Size(min = 3, max = 100, message = "Bairro deve ter entre 3 e 100 caracteres.")
    private String bairro;

    @NotBlank(message = "Cidade é obrigatória.")
    @Size(min = 2, max = 100)
    private String cidade;

    @NotBlank(message = "Estado é obrigatório.")
    @Pattern(regexp = "^[A-Z]{2}$", message = "Estado deve estar no formato de sigla, ex.: SP, RJ.")
    private String estado;

    @NotBlank(message = "CEP é obrigatório.")
    @Pattern(regexp = "\\d{5}\\-\\d{3}", message = "CEP deve estar no formato XXXXX-XXX.")
    private String cep;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


}
