package com.communitychain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.communitychain.dao.CommunityDAO;
import com.communitychain.dao.UserDAO;
import com.communitychain.entity.Community;
import com.communitychain.entity.Pert_com;
import com.communitychain.entity.User;
import com.communitychain.outputs.CommunityOutput;

@Service
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    private CommunityDAO communityDAO;

    @Autowired
    private UserDAO userDAO;

    @Override
    public List<CommunityOutput> findAll() {

        List<CommunityOutput> result = new ArrayList<>();
		
		for (Community c: communityDAO.findAll()) {
			CommunityOutput co = new CommunityOutput(c);
			result.add(co);
		}

        return result;
    }

    @Override
    public CommunityOutput findById(int id) {
        Community community = communityDAO.findById(id);
        CommunityOutput co = new CommunityOutput(community);
        return co;
    }

    @Override
    public Community findByIdNO(int id) {
        return communityDAO.findById(id);
    }

    @Override
    public CommunityOutput postUser(int communityId, int userId, String rol) {

        Community community = communityDAO.findById(communityId);
        User user = userDAO.findById(userId);

        Pert_com pc = new Pert_com(user, community, rol);

        community.addUser(pc);
        user.addCommunity(pc);

        communityDAO.save(community);
        userDAO.save(user);

        return new CommunityOutput(community);
    }

    @Override
    public void save(Community community) {
        communityDAO.save(community);

    }

    @Override
    public void saveUpdate(Community community) {

        Community community2 = communityDAO.findById(community.getId());

        if (community.getName() != community2.getName()) community2.setName(community.getName());
        if (community.getWallet() != community2.getWallet()) community2.setWallet(community.getWallet());
        if (community.getProfilepic() != community2.getProfilepic()) community2.setProfilepic(community.getProfilepic());

        communityDAO.save(community2);

    }

    @Override
    public void deleteById(int id) {
        communityDAO.deleteById(id);
    }

}