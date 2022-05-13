package com.communitychain.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.communitychain.dao.UserDAO;
import com.communitychain.entity.Pert_com;
import com.communitychain.entity.User;
import com.communitychain.outputs.CommunityOutput;
import com.communitychain.outputs.UserOutput;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public List<UserOutput> findAll() {
        
        List<UserOutput> result = new ArrayList<>();
		
		for (User u: userDAO.findAll()) {
			UserOutput uo = new UserOutput(u);
			result.add(uo);
		}

        return result;
    }

    @Override
    public UserOutput findById(int id) {
        User user = userDAO.findById(id);
        UserOutput uo = new UserOutput(user);
        return uo;
    }

    
    @Override
    public List<CommunityOutput> getUserComm(int userId) {

        User user = userDAO.findById(userId);
        List<CommunityOutput> result = new ArrayList<>();

        for (Pert_com pc: user.getuserCommunities()) {
            result.add(new CommunityOutput(pc.getCommunity()));
        }

        return result;
    }
    

    @Override
    public void saveUpdate(User user) {

        User user2 = userDAO.findById(user.getId());

        if (user.getEmail() != user2.getEmail()) user2.setEmail(user.getEmail());
        if (user.getName() != user2.getName()) user2.setName(user.getName());
        if (user.getUsername() != user2.getUsername()) user2.setUsername(user.getUsername());
        if (user.getPassword() != user2.getPassword()) user2.setPassword(user.getPassword());
        if (user.getDob() != user2.getDob()) user2.setDob(user.getDob());
        if (user.getProfilepic() != user2.getProfilepic()) user2.setProfilepic(user.getProfilepic());
        user2.setUpdatedAt(new Date(System.currentTimeMillis()));

        userDAO.save(user2);

    }

    @Override
    public void save(User user) {

        userDAO.save(user);
    }

    @Override
    public void deleteById(int id) {
        userDAO.deleteById(id);
    }

}