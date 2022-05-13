package com.communitychain.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Pert_comId
    implements Serializable {
 
    @Column(name = "user_id")
    private int userId;
 
    @Column(name = "community_id")
    private int communityId;
 
    public Pert_comId() {}

    public Pert_comId(int userId, int communityId) {
        this.userId = userId;
        this.communityId = communityId;
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        Pert_comId that = (Pert_comId) o;
        return Objects.equals(userId, that.userId) &&
               Objects.equals(communityId, that.communityId);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(userId, communityId);
    }
}