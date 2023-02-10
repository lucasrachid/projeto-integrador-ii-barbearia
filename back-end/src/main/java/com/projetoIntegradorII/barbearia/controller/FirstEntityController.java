package com.projetoIntegradorII.barbearia.controller;

import com.projetoIntegradorII.barbearia.dto.InfoDTO;
import com.projetoIntegradorII.barbearia.service.FirstEntityService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/firstEntityEndPoint")
@NoArgsConstructor
@Slf4j
public class FirstEntityController {

    @Autowired
    private FirstEntityService firstEntityService;

    @RequestMapping(value = "/getData", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<InfoDTO> getData() {
        InfoDTO infoDTO = firstEntityService.getData();
        return ResponseEntity.status(infoDTO.getStatus()).body(infoDTO);
    }

    @RequestMapping(value = "/getListData", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<InfoDTO> getListData() {
        InfoDTO infoDTO = firstEntityService.getListData();
        return ResponseEntity.status(infoDTO.getStatus()).body(infoDTO);
    }
}
