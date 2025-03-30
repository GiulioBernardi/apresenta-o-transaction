package br.com.estudos.transactional.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estudos.transactional.service.BancoService;
import br.com.estudos.transactional.to.TransferenciaTo;

@RestController
@RequestMapping("/banco")
public class BancoController {

    @Autowired
    public BancoService bancoService;

    @PostMapping("/transferir")
    public String transferir(@RequestBody TransferenciaTo transferenciaTo) {
        try {
            return bancoService.transferir(transferenciaTo);
        } catch (Exception e) {
            return "houve um erro";
        }
    }

    @PostMapping("/transferir/requires-new")
    public String transferirRequiresNew(@RequestBody TransferenciaTo transferenciaTo) {
        try {
            return bancoService.transferirRequiresNew(transferenciaTo);
        } catch (Exception e) {
            return "houve um erro";
        }
    }

    @PostMapping("/transferir/requires-new-em-duas-classes")
    public String transferirRequiresNewEmDuasClasses(@RequestBody TransferenciaTo transferenciaTo) {
        try {
            return bancoService.transferirRequiresNewEmDuasClasses(transferenciaTo);
        } catch (Exception e) {
            return "Houve um erro";
        }
    }

}
