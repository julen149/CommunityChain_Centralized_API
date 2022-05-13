package com.communitychain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="community")
public class Community {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="profile_pic")
    private String profile_pic;

    @Column(name="wallet", nullable = false)
    private int wallet;

    @Column(name="created_at", nullable = false)
    @CreationTimestamp
    private Date createdAt;

    @OneToMany(
        mappedBy = "community",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Pert_com> hasUser = new ArrayList<>();

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL, fetch = FetchType.LAZY) 
    private List<Contract> contracts = new ArrayList<>();

    public Community() {}

    public Community(int id, String name, int wallet) {
        this.id = id;
        this.name = name;
        this.wallet = wallet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilepic() {
        return profile_pic;
    }

    public void setProfilepic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<Pert_com> gethasUser() {
        return this.hasUser;
    }

    public void sethasUser(List<Pert_com> hasUser) {
        this.hasUser = hasUser;
    }

    public void addUser(Pert_com user) {
        this.hasUser.add(user);
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void addContract(Contract contract) {
        this.contracts.add(contract);
    }
}
