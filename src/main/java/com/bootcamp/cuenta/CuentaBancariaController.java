package com.bootcamp.cuenta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/cuentas")
public class CuentaBancariaController {

    private CuentaBancariaService cuentaBancariaService;

    @Autowired
    public CuentaBancariaController(CuentaBancariaService cuentaBancariaService) {
        this.cuentaBancariaService = cuentaBancariaService;
    }

    @GetMapping
    public Page<CuentaBancaria> getCuentaBancarias(@PageableDefault(size = 3, page = 0) Pageable pageable) {
        return cuentaBancariaService.findAllCuentasBancarias(pageable);
    }

    @GetMapping("{cuentaBancariaId}")
    public CuentaBancaria getCuentaBancaria(@PathVariable Long cuentaBancariaId) {
        return cuentaBancariaService.getCuentaBancaria(cuentaBancariaId);
    }

    @PostMapping
    public ResponseEntity<Long> createCuentaBancaria(@RequestBody CuentaBancaria cuentaBancaria) {
        Long idCuentaBancaria = cuentaBancariaService.createCuentaBancaria(cuentaBancaria);
        return new ResponseEntity<>(idCuentaBancaria, HttpStatus.CREATED);
    }

    @PutMapping("{cuentaBancariaId}")
    public CuentaBancaria updateCuentaBancaria(@PathVariable Long cuentaBancariaId, @RequestBody CuentaBancaria cuentaBancaria ) {
        return cuentaBancariaService.updateCuentaBancaria(cuentaBancariaId, cuentaBancaria);
    }

    @DeleteMapping("{cuentaBancariaId}")
    public ResponseEntity<?> deleteCuentaBancaria(@PathVariable Long cuentaBancariaId) {
        cuentaBancariaService.deleteCuentaBancaria(cuentaBancariaId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
