package com.idCardEmployee.entity;

import javax.persistence.*;

@Entity
@Table(name = "card_access")
public class CardAccess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_access_id")
    private int id;
    @Enumerated(EnumType.STRING)
    @Column(name = "level_access")
    private LevelAccess levelAccess;

    public CardAccess() {
    }

    public CardAccess(LevelAccess levelAccess) {
        this.levelAccess = levelAccess;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LevelAccess getLevelAccess() {
        return levelAccess;
    }

    public void setLevelAccess(LevelAccess levelAccess) {
        this.levelAccess = levelAccess;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", levelAccess=" + levelAccess +
                '}';
    }
}
