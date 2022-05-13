package com.communitychain.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.communitychain.entity.Community;

@Repository
public class CommunityDAOImpl implements CommunityDAO{

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Community> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Community> theQuery = currentSession.createQuery("from Community", Community.class);

        List<Community> communities = theQuery.getResultList();

        return communities;

    }

    @Override
    public Community findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        Community community = currentSession.get(Community.class, id);

        return community;
    }

    @Override
    public void save(Community community) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(community);  

    }

    @Override
    public void deleteById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Community> theQuery = currentSession.createQuery("delete from Community where id=:idCommunity");

        theQuery.setParameter("idCommunity", id);
        theQuery.executeUpdate();

    }


}