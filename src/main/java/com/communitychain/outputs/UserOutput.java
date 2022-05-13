package com.communitychain.outputs;

import java.util.Date;

import com.communitychain.entity.User;


public class UserOutput extends OutputManager{

    private String selfUri;
    private int id;
    private String email;
    private String name;
    private String username;
    private String profile_pic;
    private Date dob;
    private Date created_at;
    private Date updated_at;
    private String communitiesUri;
    
    public UserOutput() {
        
    }

    public UserOutput(User u) {
        this.id = u.getId();
        this.email = u.getEmail();
        this.name = u.getName();
        this.username = u.getUsername();
        this.selfUri = getURL() + "/users/" + this.id;
        this.communitiesUri =  this.selfUri + "/communities";
        this.profile_pic = u.getProfilepic();
        this.dob = u.getDob();
        this.created_at = u.getCreatedAt();
        this.updated_at = u.getUpdatedAt();
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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilepic() {
        return this.profile_pic;
    }

    public void setProfilepic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getSelfUri() {
        return this.selfUri;
    }

    public void setSelfUri(String selfUri) {
        this.selfUri = selfUri;
    }

    public String getCommunitiesUri() {
        return this.communitiesUri;
    }

    public void setCommunitiesUri(String communitiesUri) {
        this.communitiesUri = communitiesUri;
    }

    public Date getDob() {
        return this.dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getCreatedAt() {
        return this.created_at;
    }

    public void setCreatedAt(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdatedAt() {
        return this.updated_at;
    }

    public void setUpdatedAt(Date updated_at) {
        this.updated_at = updated_at;
    }
}