package com.amud.io.aemudapi.entities;

import jakarta.persistence.*;
@Entity
public class LogMemberBackup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Member member;
    private boolean isBackup;
    public LogMemberBackup() {
        //constructor without args
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Member getMember() {
        return member;
    }
    public void setMember(Member members) {
        this.member = members;
    }

    public boolean isBackup() {
        return isBackup;
    }

    public void setBackup(boolean backup) {
        isBackup = backup;
    }
}
