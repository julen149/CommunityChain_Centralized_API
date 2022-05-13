package com.communitychain.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.communitychain.entity.Contract;

@Repository
public class ContractDAOImpl implements ContractDAO{

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Contract> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Contract> theQuery = currentSession.createQuery("from Contract", Contract.class);

        List<Contract> contracts = theQuery.getResultList();

        return contracts;

    }

    @Override
    public Contract findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        Contract contract = currentSession.get(Contract.class, id);

        return contract;
    }

    @Override
    public void save(Contract contract) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(contract);  
    }

    @Override
    public void deleteById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Contract> theQuery = currentSession.createQuery("delete from Contract where id=:idContract");

        theQuery.setParameter("idContract", id);
        theQuery.executeUpdate();

    }


}