package com.communitychain.service;

import java.util.List;
import com.communitychain.entity.User;
import com.communitychain.inputs.UserInput;
import com.communitychain.inputs.UserLogin;
import com.communitychain.outputs.CommunityOutput;
import com.communitychain.outputs.UserOutput;

public interface UserService {

    public List<UserOutput> findAll();

    public UserOutput findById(int id);

    public UserOutput findByUsername(String username);

    public List<CommunityOutput> getUserComm(int usedId);

    public String loginUser(UserLogin userLogin);

    public String logoutUser(String username);

    public String getUserToken(String username);

    public void saveUpdate(User user);

    public UserOutput saveUpdate2(UserInput user);

    public void save(User user);

    public void deleteById(int id);
}