package br.com.zup.handora.cadastrandocarrosunicos.carro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "carros", uniqueConstraints = {
        @UniqueConstraint(name = "UK_CARRO_PLACA", columnNames = {"placa"}),
        @UniqueConstraint(name = "UK_CARRO_CHASSI", columnNames = {"chassi"})})
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    @Min(2010)
    @Max(2099)
    private Integer ano;

    @Column(nullable = false)
    @Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}")
    private String placa;

    @Column(nullable = false, length = 17)
    @Size(min = 17, max = 17)
    private String chassi;

    /**
     * @deprecated Construtor de uso exclusivo do Hibernate
     */
    @Deprecated
    public Carro() {}

    public Carro(String marca, String modelo, @Min(2010) @Max(2099) Integer ano,
                 @Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}") String placa,
                 @Size(min = 17, max = 17) String chassi) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.placa = placa;
        this.chassi = chassi;
    }

    public Long getId() {
        return id;
    }

}
