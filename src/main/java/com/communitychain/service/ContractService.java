package com.communitychain.service;

import java.util.List;

import com.communitychain.entity.Contract;
import com.communitychain.outputs.CommunityOutput;
import com.communitychain.outputs.ContractOutput;

public interface ContractService {

    public List<ContractOutput> findAll();

    public Contract findById(int id);

    public CommunityOutput getComm(int contractId);

    public void save(Contract contract);

    public void voteContract(int contractId, int userId, boolean vote);

    public ContractOutput saveWithComm(Contract contract, int communityId);

    public void deleteById(int id);
}