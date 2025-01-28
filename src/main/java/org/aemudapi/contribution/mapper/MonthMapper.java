package org.aemudapi.contribution.mapper;

import org.aemudapi.contribution.dto.MonthDTO;
import org.aemudapi.contribution.entity.Month;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MonthMapper {

    public MonthDTO toDTO(Month month) {
        if (month == null) {
            return null;
        }

        MonthDTO dto = new MonthDTO();
        dto.setId(month.getId());
        dto.setMonthName(month.getMonthName());
        dto.setMonthNumber(month.getMonthNumber());
        return dto;
    }

    public Month toEntity(MonthDTO dto) {
        if (dto == null) {
            return null;
        }

        Month month = new Month();
        if (dto.getId() != null) {
            month.setId(dto.getId());
        }
        month.setMonthName(dto.getMonthName());
        month.setMonthNumber(dto.getMonthNumber());
        return month;
    }

    public List<MonthDTO> toDTOList(List<Month> months) {
        if (months == null || months.isEmpty()) {
            return List.of();
        }
        return months.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<Month> toEntityList(List<MonthDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return List.of();
        }
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
