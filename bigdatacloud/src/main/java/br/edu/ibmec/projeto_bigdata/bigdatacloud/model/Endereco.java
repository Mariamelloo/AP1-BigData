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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
