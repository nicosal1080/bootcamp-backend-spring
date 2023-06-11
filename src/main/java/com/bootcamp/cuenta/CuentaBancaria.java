package com.bootcamp.cuenta;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class CuentaBancaria {

    @Id
    @Column(name = "id_cuenta")
    @SequenceGenerator(
            sequenceName = "sequence_cuenta",
            name = "sequence_cuenta"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_cuenta"
    )
    private Long id;

    private Long numeroCuenta;

    private String banco;

    private String titular;

    public CuentaBancaria() {
    }

    public CuentaBancaria(Long id, Long numeroCuenta, String banco, String titular) {
        this.id = id;
        this.numeroCuenta = numeroCuenta;
        this.banco = banco;
        this.titular = titular;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(Long numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CuentaBancaria that = (CuentaBancaria) o;
        return Objects.equals(id, that.id) && Objects.equals(numeroCuenta, that.numeroCuenta) && Objects.equals(banco, that.banco) && Objects.equals(titular, that.titular);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numeroCuenta, banco, titular);
    }

    @Override
    public String toString() {
        return "CuentaBancaria{" +
                "id=" + id +
                ", numeroCuenta=" + numeroCuenta +
                ", banco='" + banco + '\'' +
                ", titular='" + titular + '\'' +
                '}';
    }
}
