package com.bootcamp.materia;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "materia")
public class Materia {

    @Id
    @Column(name = "id_materia")
    @SequenceGenerator(
            sequenceName = "sequence_materia",
            name = "sequence_materia"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_materia"
    )
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "creditos")
    private Integer creditos;

    public Materia() {
    }

    public Materia(Long id, String nombre, Integer creditos) {
        this.id = id;
        this.nombre = nombre;
        this.creditos = creditos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Materia materia = (Materia) o;
        return Objects.equals(id, materia.id) && Objects.equals(nombre, materia.nombre) && Objects.equals(creditos, materia.creditos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, creditos);
    }

    @Override
    public String toString() {
        return "Materia{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", creditos=" + creditos +
                '}';
    }
}
