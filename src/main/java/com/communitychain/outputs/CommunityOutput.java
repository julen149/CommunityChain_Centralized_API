package com.communitychain.outputs;

import java.util.Date;

import com.communitychain.entity.Community;


public class CommunityOutput extends OutputManager{

    private String selfUri;
    private int id;
    private String name;
    private String profile_pic;
    private int wallet;
    private Date created_at;
    private String usersUri;
    private String contractsUri;
    
    public CommunityOutput() {
        
    }

    public CommunityOutput(Community c) {
        this.id = c.getId();
        this.name = c.getName();
        this.selfUri = getURL() + "/communities/" + this.id;
        this.usersUri =  this.selfUri + "/users";
        this.contractsUri = this.selfUri + "/contracts";
        this.profile_pic = c.getProfilepic();
        this.wallet = c.getWallet();
        this.created_at = c.getCreatedAt();
    }



    public String getSelf() {
        return this.selfUri;
    }

    public void setSelf(String self) {
        this.selfUri = self;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilepic() {
        return this.profile_pic;
    }

    public void setProfilepic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public int getWallet() {
        return this.wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public String getSelfUri() {
        return this.selfUri;
    }

    public void setSelfUri(String selfUri) {
        this.selfUri = selfUri;
    }

    public String getUsersUri() {
        return this.usersUri;
    }

    public void setUsersUri(String usersUri) {
        this.usersUri = usersUri;
    }

    public String getContractsUri() {
        return this.contractsUri;
    }

    public void setContractsUri(String contractsUri) {
        this.contractsUri = contractsUri;
    }

    public Date getCreatedAt() {
        return this.created_at;
    }

    public void setCreatedAt(Date created_at) {
        this.created_at = created_at;
    }
}