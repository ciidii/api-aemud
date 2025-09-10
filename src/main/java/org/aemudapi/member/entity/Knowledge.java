package org.aemudapi.member.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Knowledge {

    private Boolean acquired;       // a-t-il des connaissances ?
    private String bookName;        // nom du livre appris
    private String teacherName;     // enseignant
    private String learningPlace;   // lieu d’apprentissage
}
