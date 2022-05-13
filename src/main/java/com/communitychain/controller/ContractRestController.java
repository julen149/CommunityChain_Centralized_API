package com.communitychain.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.communitychain.entity.Contract;
import com.communitychain.outputs.CommunityOutput;
import com.communitychain.outputs.ContractOutput;
import com.communitychain.service.ContractService;

@RestController
@RequestMapping("/api") 

public class ContractRestController {

    @Autowired
    private ContractService contractService;

    @GetMapping("/contracts")
    public List<ContractOutput> findAll(){
        //retornará todos los contratos
        return contractService.findAll();
    }

    @GetMapping("/contracts/{contractId}")
    public ContractOutput getContract(@PathVariable int contractId){
        Contract contract = contractService.findById(contractId);

        if(contract == null) {
            throw new RuntimeException("Contract id not found -"+contractId);
        }
        
        return new ContractOutput(contract);
    }

    
    @GetMapping("/contracts/community/{contractId}")
    public CommunityOutput getCommunity(@PathVariable int contractId){
        
        CommunityOutput co= contractService.getComm(contractId);
        
        return co;
    }
    

    @PostMapping("/contracts/{communityId}")
    @Transactional
    public ContractOutput addContract(@RequestBody Contract contract, @PathVariable int communityId) {
        
        contract.setId(0);
        ContractOutput contractO = contractService.saveWithComm(contract, communityId);
        return contractO;
    }

    @PostMapping("/contracts/{contractId}/{userId}/{vote}")
    @Transactional
    public String voteContract(@PathVariable int contractId, @PathVariable int userId, @PathVariable boolean vote) {
        
        contractService.voteContract(contractId, userId, vote);
        return "VOTED!";
    }

    @PutMapping("/contracts")
    @Transactional
    public ContractOutput updateContract(@RequestBody Contract contract) {

        contractService.save(contract);
        return new ContractOutput(contract);
    }

    
    @DeleteMapping("contracts/{contractId}")
    @Transactional
    public String deteteContract(@PathVariable int contractId) {

        Contract contract = contractService.findById(contractId);

        if(contract == null) {
            throw new RuntimeException("Contract id not found -"+contractId);
        }

        contractService.deleteById(contractId);

        return "Deleted contract id - "+contractId;
    }

}