package com.querypro.apicomm.adapter.api.regpros;

import com.querypro.apicomm.domain.model.users.Prospect;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/prospect")
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173/", "http://127.0.0.1:5173/"})
public class ProspectController {

    private final ProspectService prospectService;

    @GetMapping("/all")
    public List<Prospect> getAllProspect(){return prospectService.getProspect();}

    @PostMapping("/register")
    public void registrarNuevoProspecto(@RequestBody Prospect prospect){
        prospectService.addNewProspect(prospect);
    }
}
