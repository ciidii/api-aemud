package org.aemudapi.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member_Session_PK implements Serializable {
    @Column(name = "session_id")
    Long sessionID;
    @Column(name = "member_id")
    Long memberID;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Member_Session_PK that = (Member_Session_PK) o;
        return Objects.equals(sessionID, that.sessionID) && Objects.equals(memberID, that.memberID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionID, memberID);
    }
}
