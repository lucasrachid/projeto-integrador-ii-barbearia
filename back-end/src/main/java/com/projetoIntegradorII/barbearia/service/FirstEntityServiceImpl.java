package com.projetoIntegradorII.barbearia.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.projetoIntegradorII.barbearia.dto.FirstEntityDTO;
import com.projetoIntegradorII.barbearia.dto.InfoDTO;
import com.projetoIntegradorII.barbearia.entity.FirstEntity;
import com.projetoIntegradorII.barbearia.exception.InfoException;
import com.projetoIntegradorII.barbearia.repository.FirstEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@EnableScheduling
public class FirstEntityServiceImpl implements FirstEntityService {

    @Autowired
    private FirstEntityRepository firstEntityRepository;

    @Override
    public InfoDTO getData() {
        InfoDTO<FirstEntityDTO> infoDTO = new InfoDTO<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            FirstEntity firstEntity = firstEntityRepository.getById(1L);
            FirstEntityDTO firstEntityDTO = objectMapper.convertValue(firstEntity, new TypeReference<FirstEntityDTO>() {
            });

            infoDTO.setObject(firstEntityDTO);
            infoDTO.setStatus(HttpStatus.OK);
            infoDTO.setSuccess(true);
            infoDTO.setMessage("Operação realizada com sucesso!");


        } catch (Exception e){
            e.printStackTrace();
            infoDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            infoDTO.setSuccess(false);
            infoDTO.setMessage(e.getMessage());
        }

        return infoDTO;
    }

    @Override
    public InfoDTO getListData() {
        InfoDTO<List<FirstEntityDTO>> infoDTO = new InfoDTO<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            List<FirstEntity> firstEntity = firstEntityRepository.findAllByNameIsNotNull();

            List<FirstEntityDTO> firstEntityDTO = objectMapper.convertValue(firstEntity, new TypeReference<List<FirstEntityDTO>>() {
            });

            infoDTO.setObject(firstEntityDTO);
            infoDTO.setStatus(HttpStatus.OK);
            infoDTO.setSuccess(true);
            infoDTO.setMessage("Operação realizada com sucesso!");


        } catch (Exception e){
            e.printStackTrace();
            infoDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            infoDTO.setSuccess(false);
            infoDTO.setMessage(e.getMessage());
        }

        return infoDTO;
    }
}
