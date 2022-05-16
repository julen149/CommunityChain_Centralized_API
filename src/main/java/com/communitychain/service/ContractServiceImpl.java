package com.communitychain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.stereotype.Service;

import com.communitychain.dao.CommunityDAO;
import com.communitychain.dao.ContractDAO;
import com.communitychain.dao.UserDAO;
import com.communitychain.entity.Community;
import com.communitychain.entity.Contract;
import com.communitychain.entity.Pert_com;
import com.communitychain.entity.User;
import com.communitychain.entity.User_contract;
import com.communitychain.outputs.CommunityOutput;
import com.communitychain.outputs.ContractOutput;

@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractDAO contractDAO;

    @Autowired
    private CommunityDAO communityDAO;

    @Autowired
    private UserDAO userDAO;

    @Override
    public List<ContractOutput> findAll() {
        
        List<ContractOutput> result = new ArrayList<>();
		
		for (Contract c: contractDAO.findAll()) {
			result.add(new ContractOutput(c));
		}

        return result;
    }

    @Override
    public Contract findById(int id) {
        Contract contract = contractDAO.findById(id);
        return contract;
    }

    
    @Override
    public CommunityOutput getComm(int contractId) {

        Contract contract = contractDAO.findById(contractId);
        return new CommunityOutput(contract.getCommunity());
    }
    
    @Override
    public void save(Contract contract) {

        contractDAO.save(contract);
    }

    @Override
    public String setLedgerId(int contractId, String ledgerId) {

        Contract contract = contractDAO.findById(contractId);
        contract.setLedgerId(ledgerId);
        contractDAO.save(contract);
        return ledgerId;
    }

    @Override
    public void voteContract(int contractId, int userId, boolean vote) {

        User u = userDAO.findById(userId);
        Contract c = contractDAO.findById(contractId);
        Community co = c.getCommunity();
        List<User> result = new ArrayList<>();

        for (Pert_com pc: co.gethasUser()) {
            User u1 = pc.getUser();
            result.add(u1);
		}

        boolean re = false;

        if (result.contains(u)) {
            for (User_contract uc: u.getuserContracts()) {
                Contract c1 = uc.getContract();
                
                if ((uc.getVoted() == false) && (c1.equals(c))) {
                    if (vote == true) {
                        c1.setVote(c1.getVote() + 1);
                        if ((c1.getMost()/2) <= c1.getVote()) c1.setApproved(true);
                    } 
                    if (vote == false) c1.setNay(c1.getNay() + 1);
                    re = true;
                    uc.setVoted(true);
                }

                contractDAO.save(c1);
            }
        }
        else throw new HttpMessageConversionException("Error");

        if (re == false) throw new HttpMessageConversionException("Error");

        userDAO.save(u);
    }

    @Override
    public ContractOutput saveWithComm(Contract contract, int communityId) {

        Community community = communityDAO.findById(communityId);
        contract.setCommunity(community);
        contract.setMost(community.gethasUser().size());
        community.addContract(contract);
        contractDAO.save(contract);
        for (Pert_com pc: community.gethasUser()) {
            User u = pc.getUser();
            User_contract uc = new User_contract(u, contract, false);
            u.addContract(uc);
            contract.addUser(uc);
            userDAO.save(u);
		}

        communityDAO.save(community);
        contractDAO.save(contract);

        return new ContractOutput(contract);
    }

    @Override
    public void deleteById(int id) {
        contractDAO.deleteById(id);
    }

}