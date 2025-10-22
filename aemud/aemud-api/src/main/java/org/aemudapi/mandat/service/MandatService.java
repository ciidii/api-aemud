package org.aemudapi.mandat.service;

import org.aemudapi.mandat.dtos.CreateMandatDto;
import org.aemudapi.mandat.dtos.MandatDto;

import java.util.List;

public interface MandatService {
    List<MandatDto> getAllMandats();
    MandatDto createMandat(CreateMandatDto createMandatDto);
    MandatDto getMandatById(String id);
}
