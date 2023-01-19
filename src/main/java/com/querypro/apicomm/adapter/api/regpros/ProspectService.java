package com.querypro.apicomm.adapter.api.regpros;

import com.querypro.apicomm.adapter.repository.ProspectRepository;
import com.querypro.apicomm.domain.model.users.Prospect;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor //Esta anotación es para no tener que escribir los constructores de las clases instanciadas.
public class ProspectService {

    private final ProspectRepository prospectRepository;

    public List<Prospect> getProspect(){return prospectRepository.findAll();}

    public void addNewProspect(Prospect prospect){

        Optional<Prospect> prospectOptional = prospectRepository.findByEmail(prospect.getEmail());

        if(prospectOptional.isPresent()){
            throw  new IllegalStateException("Ya Existe un Prospecto con este correo");
        }
        prospectRepository.save(prospect);
    }
}
