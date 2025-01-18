package org.aemudapi.member.mapper;

import org.aemudapi.member.dtos.BourseDTO;
import org.aemudapi.member.entity.Bourse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BourseMapper {
    public BourseDTO toDTO(Bourse bourse) {
        if (bourse == null) {
            return null;
        }

        BourseDTO dto = new BourseDTO();
      //  dto.setBourseId(bourse.getIdBourse());
        dto.setLebelle(bourse.getLebelle());
        dto.setMontant(bourse.getMontant());

        return dto;
    }

    public List<BourseDTO> toDTO(List<Bourse> bourses) {
        if (bourses == null) {
            return null;
        }

        return bourses.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Bourse toEntity(BourseDTO dto) {
        if (dto == null) {
            return null;
        }

        Bourse bourse = new Bourse();
       // bourse.setIdBourse(dto.getBourseId());
        bourse.setLebelle(dto.getLebelle());
        bourse.setMontant(dto.getMontant());

        return bourse;
    }

    public List<Bourse> toEntity(List<BourseDTO> dtos) {
        if (dtos == null) {
            return null;
        }

        List<Bourse> list = new ArrayList<>();
        for (BourseDTO dto : dtos) {
            Bourse entity = toEntity(dto);
            list.add(entity);
        }
        return list;
    }
}
