package com.communitychain.service;

import java.util.List;

import com.communitychain.entity.Community;
import com.communitychain.outputs.CommunityOutput;

public interface CommunityService {

    public List<CommunityOutput> findAll();

    public CommunityOutput findById(int id);

    public Community findByIdNO(int id);

    public CommunityOutput postUser(int communityId, int userId, String rol);

    public void save(Community community);

    public void saveUpdate(Community community);

    public void deleteById(int id);
}