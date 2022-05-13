package com.communitychain.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity(name = "Pert_com")
@Table(name = "pert_com")
public class Pert_com {
 
    @EmbeddedId
    private Pert_comId id;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("user_id")
    private User user;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("community_id")
    private Community community;
 
    @Column(name = "rol")
    private String rol;
 
    public Pert_com() {}

    public Pert_com(User user, Community community, String rol) {
        this.user = user;
        this.community = community;
        this.rol = rol;
        this.id = new Pert_comId(user.getId(), community.getId());
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        Pert_com that = (Pert_com) o;
        return Objects.equals(user, that.user) &&
               Objects.equals(community, that.community);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(user, community);
    }

    public Community getCommunity() {
        return this.community;
    }

    public User getUser() {
        return this.user;
    }
}
