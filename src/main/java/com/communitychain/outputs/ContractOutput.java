package com.communitychain.outputs;

import com.communitychain.entity.Contract;


public class ContractOutput extends OutputManager{

    private String selfUri;
    private int id;
    private String ledgerId;
    private String communityUri;
    private int cost;
    private int most;
    private int vote;
    private int nay;
    private boolean approved;
    
    public ContractOutput() {
        
    }

    public ContractOutput(Contract c) {
        this.id = c.getId();
        this.ledgerId = c.getLedgerId();
        this.selfUri = getURL() + "/contracts/" + this.id;
        this.communityUri =  getURL() + "/contracts/community/" + c.getCommunity().getId();
        this.cost = c.getCost();
        this.most = c.getMost();
        this.vote = c.getVote();
        this.nay = c.getNay();
        this.approved = c.getApproved();
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

    public String getLedgerId() {
        return this.ledgerId;
    }

    public void setLedgerId(String ledgerId) {
        this.ledgerId = ledgerId;
    }

    public String getCommunityUri() {
        return this.communityUri;
    }

    public void setCommunityUri(String communityUri) {
        this.communityUri = communityUri;
    }

    public int getCost() {
        return this.cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getMost() {
        return this.most;
    }

    public void setMost(int most) {
        this.most = most;
    }

    public int getVote() {
        return this.vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public int getNay() {
        return this.nay;
    }

    public void setNay(int nay) {
        this.nay = nay;
    }

    public boolean getApproved() {
        return this.approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}