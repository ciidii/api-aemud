package org.aemudapi.member.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ReligiousKnowledge {

    // Niveau Arabe
    private ArabicProficiency arabicProficiency;

    // Niveau Coran
    private CORAN_LEVEL coranLevel;

    // Aqida : plusieurs livres possibles
    @ElementCollection
    @CollectionTable(name = "member_aqida_books", joinColumns = @JoinColumn(name = "member_id"))
    private List<Knowledge> aqidaBooks;

    // Fiqh : plusieurs livres possibles
    @ElementCollection
    @CollectionTable(name = "member_fiqh_books", joinColumns = @JoinColumn(name = "member_id"))
    private List<Knowledge> fiqhBooks;
}
