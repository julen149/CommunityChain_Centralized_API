package com.communitychain.dao;

import java.util.List;

import com.communitychain.entity.Contract;


public interface ContractDAO {

    public List<Contract> findAll();

    public Contract findById(int id);

    public void save(Contract contract);

    public void deleteById(int id);
}
