package com.communitychain.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class User_contractId
    implements Serializable {
 
    @Column(name = "user_id")
    private int userId;
 
    @Column(name = "contract_id")
    private int contractId;
 
    public User_contractId() {}

    public User_contractId(int userId, int contractId) {
        this.userId = userId;
        this.contractId = contractId;
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        User_contractId that = (User_contractId) o;
        return Objects.equals(userId, that.userId) &&
               Objects.equals(contractId, that.contractId);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(userId, contractId);
    }
}