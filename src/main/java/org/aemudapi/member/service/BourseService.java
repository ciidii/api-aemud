package org.aemudapi.member.service;

import org.aemudapi.member.dtos.BourseDTO;
import org.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BourseService {
    ResponseEntity<ResponseVO<BourseDTO>> addBourse(BourseDTO bourseDTO);

    ResponseEntity<ResponseVO<List<BourseDTO>>> getAllBourse();

    ResponseEntity<ResponseVO<Void>> deleteBourse(String bourseId);
    public ResponseEntity<ResponseVO<Boolean>>  checkIfCanDeleteBourse(String bourseId);

    ResponseEntity<ResponseVO<BourseDTO>> findBourseById(String bourseId);
}
