package com.amud.io.aemudapi.services;

import com.amud.io.aemudapi.dto.BourseDTO;
import com.amud.io.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BourseService {
    ResponseEntity<ResponseVO<BourseDTO>> addBourse(BourseDTO bourseDTO);
    ResponseEntity<ResponseVO<List<BourseDTO>>> getAllBourse( );

}
