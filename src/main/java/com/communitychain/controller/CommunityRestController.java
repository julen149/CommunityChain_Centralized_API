package com.communitychain.controller;

import java.util.ArrayList;
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

import com.communitychain.entity.Community;
import com.communitychain.entity.Contract;
import com.communitychain.outputs.CommunityOutput;
import com.communitychain.outputs.ContractOutput;
import com.communitychain.service.CommunityService;

@RestController
@RequestMapping("/api") 

public class CommunityRestController {

    @Autowired
    private CommunityService communityService;

    @GetMapping("/communities")
    public List<CommunityOutput> findAll(){
        //retornará todas las comunidades
        return communityService.findAll();
    }

    @GetMapping("/communities/{communityId}")
    public CommunityOutput getCommunity(@PathVariable int communityId){
        CommunityOutput community = communityService.findById(communityId);

        if(community == null) {
            throw new RuntimeException("Community id not found -"+communityId);
        }
        
        return community;
    }

    @GetMapping("/communities/{communityId}/contracts")
    public List<ContractOutput> getCommContracts(@PathVariable int communityId){
        Community community = communityService.findByIdNO(communityId);

        if(community == null) {
            throw new RuntimeException("Community id not found -"+communityId);
        }

        List<ContractOutput> result = new ArrayList<>();
		
		for (Contract c: community.getContracts()) {
			ContractOutput co = new ContractOutput(c);
			result.add(co);
		}
        
        return result;
    }

    @PostMapping("/communities")
    public CommunityOutput addCommunity(@RequestBody Community community) {
        
        community.setId(0);

        communityService.save(community);
        CommunityOutput co = new CommunityOutput(community);

        return co;

    }

    @PostMapping("/communities/{communityId}/{userId}")
    @Transactional
    public CommunityOutput addUser(@PathVariable int communityId, @PathVariable int userId, @RequestBody String rol) {
        
        CommunityOutput co = communityService.postUser(communityId, userId, rol);

        return co;

    }

    @PutMapping("/communities")
    @Transactional
    public CommunityOutput updateCommunity(@RequestBody Community community) {

        communityService.saveUpdate(community);
        CommunityOutput co = new CommunityOutput(community);

        return co;
    }

    @DeleteMapping("communities/{communityId}")
    @Transactional
    public String deteteCommunity(@PathVariable int communityId) {

        CommunityOutput community = communityService.findById(communityId);

        if(community == null) {
            throw new RuntimeException("Community id not found -"+communityId);
        }

        communityService.deleteById(communityId);

        return "Deleted community id - "+communityId;
    }

}