package com.communitychain.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity(name = "User_contract")
@Table(name = "user_contract")
public class User_contract {
 
    @EmbeddedId
    private User_contractId id;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("user_id")
    private User user;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("contract_id")
    private Contract contract;
 
    @Column(name = "voted")
    private boolean voted;
 
    public User_contract() {}

    public User_contract(User user, Contract contract, Boolean voted) {
        this.user = user;
        this.contract = contract;
        this.voted = voted;
        this.id = new User_contractId(user.getId(), contract.getId());
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        User_contract that = (User_contract) o;
        return Objects.equals(user, that.user) &&
               Objects.equals(contract, that.contract);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(user, contract);
    }

    public Contract getContract() {
        return this.contract;
    }

    public User getUser() {
        return this.user;
    }

    public boolean getVoted() {
        return this.voted;
    }

    public void setVoted(boolean voted) {
        this.voted = voted;
    }
}
