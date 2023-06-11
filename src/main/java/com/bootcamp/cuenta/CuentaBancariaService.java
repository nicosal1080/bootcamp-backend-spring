package com.bootcamp.cuenta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CuentaBancariaService {

    private CuentaBancariaRepository cuentaBancariaRepository;

    @Autowired
    public CuentaBancariaService(CuentaBancariaRepository cuentaBancariaRepository) {
        this.cuentaBancariaRepository = cuentaBancariaRepository;
    }

    @Transactional(readOnly = true)
    public List<CuentaBancaria> getAllCuentas() {
        return cuentaBancariaRepository.findAll();
    }
}
