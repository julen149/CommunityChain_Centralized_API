package com.communitychain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="contract")
public class Contract {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="ledger_id", unique = true, nullable = false)
    private String ledgerId = "CONTRACT";

    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;

    @Column(name="cost", nullable = false)
    private int cost;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="most", nullable = false)
    private int most = 0;

    @Column(name="vote", nullable = false)
    private int vote = 0;

    @Column(name="nay", nullable = false)
    private int nay = 0;

    @Column(name="aproved", nullable = false)
    private boolean approved = false;

    @OneToMany(
        mappedBy = "contract",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<User_contract> hasUser = new ArrayList<>();

    public Contract() {}

    public Contract(int Id, int cost, String name) {
        this.id = Id;
        this.cost = cost;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLedgerId() {
        return ledgerId;
    }

    public void setLedgerId(String ledgerId) {
        this.ledgerId = ledgerId;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getMost() {
        return most;
    }

    public void setMost(int most) {
        this.most = most;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public int getNay() {
        return nay;
    }

    public void setNay(int nay) {
        this.nay = nay;
    }

    public boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public List<User_contract> gethasUser() {
        return this.hasUser;
    }

    public void sethasUser(List<User_contract> hasUser) {
        this.hasUser = hasUser;
    }

    public void addUser(User_contract user) {
        this.hasUser.add(user);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
