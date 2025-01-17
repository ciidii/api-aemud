package org.aemudapi.member.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {
    @Id
    @EmbeddedId
    private Member_Session_PK memberSessionPK;
    @JoinColumn(name = "member_id", insertable = false, updatable = false)
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Member member;
    @ManyToOne
    @JoinColumn(name = "session_id", insertable = false, updatable = false)
    private Session year;
}
