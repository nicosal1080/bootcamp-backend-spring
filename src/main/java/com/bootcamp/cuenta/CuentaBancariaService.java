package com.bootcamp.cuenta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
public class CuentaBancariaService {

    private CuentaBancariaRepository cuentaBancariaRepository;

    @Autowired
    public CuentaBancariaService(CuentaBancariaRepository cuentaBancariaRepository) {
        this.cuentaBancariaRepository = cuentaBancariaRepository;
    }

    @Transactional(readOnly = true)
    public Page<CuentaBancaria> findAllCuentasBancarias(Pageable pageable) {
        return cuentaBancariaRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public CuentaBancaria getCuentaBancaria(Long cuentaBancariaId) {
        return cuentaBancariaRepository.findById(cuentaBancariaId)
                .orElseThrow(() -> new NoSuchElementException("CuentaBancaria con id " + cuentaBancariaId + " no existe"));
    }

    public Long createCuentaBancaria(CuentaBancaria cuentaBancaria) {
        // check si una CuentaBancaria con el mismo numero de cuenta ya existe
        boolean numeroCuentaExiste = cuentaBancariaRepository.existsByNumeroCuenta(cuentaBancaria.getNumeroCuenta());
        if (numeroCuentaExiste) {
            throw new IllegalArgumentException("Numero de cuenta " + cuentaBancaria.getNumeroCuenta() + " ya existe");
        }
        return cuentaBancariaRepository.save(cuentaBancaria).getId();
    }

    public CuentaBancaria updateCuentaBancaria(Long cuentaBancariaId, CuentaBancaria cuentaBancariaAActualizar) {
        // check si cuentaBancaria con ese id existe, si no botamos un Error
        CuentaBancaria cuentaBancariaExistente = cuentaBancariaRepository.findById(cuentaBancariaId)
                .orElseThrow(() -> new NoSuchElementException("CuentaBancaria con ese id no existe, id: " + cuentaBancariaId));

        // check si el numero de cuenta que se  que se quiere actualizar ya existe
        boolean numeroExiste = cuentaBancariaRepository.existsByNumeroCuentaAndIdIsNot(
                cuentaBancariaAActualizar.getNumeroCuenta(), cuentaBancariaId);
        if (numeroExiste) {
            throw new IllegalArgumentException("Numero de cuenta " + cuentaBancariaAActualizar.getNumeroCuenta() + " ya existe");
        }

        // Actualizar cuentaBancaria
        cuentaBancariaExistente.setNumeroCuenta(cuentaBancariaAActualizar.getNumeroCuenta());
        cuentaBancariaExistente.setBanco(cuentaBancariaAActualizar.getBanco());
        cuentaBancariaExistente.setTitular(cuentaBancariaAActualizar.getTitular());

        return cuentaBancariaExistente;
    }

    public void deleteCuentaBancaria(Long cuentaBancariaId) {
        // check si id existe, si no botamos exception
        boolean cuentaBancariaExiste = cuentaBancariaRepository.existsById(cuentaBancariaId);
        if (!cuentaBancariaExiste) {
            throw new NoSuchElementException("CuentaBancaria con id " + cuentaBancariaId + " no existe");
        }
        cuentaBancariaRepository.deleteById(cuentaBancariaId);
    }
}
