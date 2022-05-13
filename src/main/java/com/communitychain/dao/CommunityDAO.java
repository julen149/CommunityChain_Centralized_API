package com.communitychain.dao;

import java.util.List;

import com.communitychain.entity.Community;


public interface CommunityDAO {

    public List<Community> findAll();

    public Community findById(int id);

    public void save(Community community);

    public void deleteById(int id);
}
