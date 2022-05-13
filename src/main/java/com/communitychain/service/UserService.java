package com.communitychain.service;

import java.util.List;

import com.communitychain.entity.User;
import com.communitychain.outputs.CommunityOutput;
import com.communitychain.outputs.UserOutput;

public interface UserService {

    public List<UserOutput> findAll();

    public UserOutput findById(int id);

    public List<CommunityOutput> getUserComm(int usedId);

    public void saveUpdate(User user);

    public void save(User user);

    public void deleteById(int id);
}