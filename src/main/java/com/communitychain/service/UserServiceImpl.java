package com.communitychain.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bytebuddy.utility.RandomString;

import com.communitychain.dao.UserDAO;
import com.communitychain.entity.Pert_com;
import com.communitychain.entity.User;
import com.communitychain.exceptions.AlreadyLoginException;
import com.communitychain.inputs.UserInput;
import com.communitychain.inputs.UserLogin;
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
    public UserOutput findByUsername(String username) {
        
        for (User u: userDAO.findAll()) {
			
            if (u.getUsername().equals(username)) {
                return new UserOutput(u);
            }
		}
        return new UserOutput();
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
    public String getUserToken(String username) {

        for (User u: userDAO.findAll()) {
			
            if (u.getUsername().equals(username)) {

                return u.getToken();
            }
		}

        return "ERROR";
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
    public UserOutput saveUpdate2(UserInput user) {

        for (User u: userDAO.findAll()) {
			
            if (u.getToken().equals(user.getToken())) {

                if (u.getEmail() != user.getEmail()) u.setEmail(user.getEmail());
                if (u.getName() != user.getName()) u.setName(user.getName());
                if (u.getUsername() != user.getUsername()) u.setUsername(user.getUsername());
                u.setUpdatedAt(new Date(System.currentTimeMillis()));
                userDAO.save(u);
                return new UserOutput(u);
            }
		}
        return new UserOutput();
    }

    @Override
    public void save(User user) {

        userDAO.save(user);
    }

    public String loginUser(UserLogin userLogin) {
			
        for (User u: userDAO.findAll()) {
			
            if (u.getUsername().equals(userLogin.getUsername())) {

                if (u.getToken() != null && !u.getToken().equals("AUTH")) throw new AlreadyLoginException();
                if (u.getPassword().equals(userLogin.getPassword())) {

                    String token = RandomString.make();
                    u.setToken(token);
                    userDAO.save(u);
                    return token;
                }
                else return "Wrong password";
            }

		}
        return "LoginError";
	}

    public String logoutUser(String username) {
			
        for (User u: userDAO.findAll()) {
			
            if (u.getUsername().equals(username)) {

                if (u.getToken() != null && !u.getToken().equals("AUTH")) {

                    u.setToken("AUTH");
                    userDAO.save(u);
                    return "Logout CORRECTO!";
                }
                else return "User not Loged IN!";
            }
		}
        return "LoginError";
	}

    @Override
    public void deleteById(int id) {
        userDAO.deleteById(id);
    }

}