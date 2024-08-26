package com.app.entity;

import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não pode ser nulo ou vazio.")
    @Pattern(regexp = "^[\\p{L} .'-]+$", message = "O nome deve conter apenas caracteres válidos.")
    private String name;

    @NotBlank(message = "O CPF não deve ser nulo ou vazio.")
    @CPF(message = "CPF inválido. O formato deve ser 123.456.789-09.")
    private String cpf;

    @NotNull(message = "A idade não pode ser nula.")
    @Min(value = 0, message = "A idade não deve ser negativa.")
    private Integer age;

    @NotBlank(message = "O telefone não deve ser nulo ou vazio.")
    @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Número de telefone inválido. O formato deve ser (XX) XXXX-XXXX ou (XX) XXXXX-XXXX.")
    private String telephone;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true) // Atualize o CascadeType conforme necessário
    @JsonIgnoreProperties("client")
    private List<Sale> sales;
}
