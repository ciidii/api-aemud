package org.aemudapi.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ReligiousKnowledge {
    private Boolean writeReadArabic;
    private Boolean readArabic;
    private CORAN_LEVEL coranLevel;

    @Embedded
    private Knowledge aqida;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "acquired", column = @Column(name = "has_fiqh_knowledge")),
            @AttributeOverride(name = "bookName", column = @Column(name = "fiqh_book")),
            @AttributeOverride(name = "teacherName", column = @Column(name = "fiqh_teacher")),
            @AttributeOverride(name = "learningPlace", column = @Column(name = "fiqh_learning_place"))
    })
    private Knowledge fiqh;
}
