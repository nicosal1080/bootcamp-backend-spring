package com.bootcamp.estudiante;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

    boolean existsByEmailAndIdIsNot(String email, Long id);

    boolean existsByEmail(String email);

    // consulta derivada
//    List<Estudiante> findEstudianteByPrimerNombreOrPrimerApellido(String primerNombre, String primerApellido);

    // consulta con JPQL
//    @Query("select e from Estudiante e where e.primerNombre = ?1 or e.primerApellido = ?2")
//    List<Estudiante> findEstudianteByPrimerNombreOrPrimerApellido(String primerNombre, String primerApellido);

    // consulta con SQL
    @Query(value = "SELECT * FROM estudiante WHERE primer_nombre = :primerNombre OR primer_apellido = :primerApellido", nativeQuery = true)
    List<Estudiante> findEstudianteByPrimerNombreOrPrimerApellido(
            @Param("primerNombre") String primerNombre,
            @Param("primerApellido") String primerApellido
    );
}
