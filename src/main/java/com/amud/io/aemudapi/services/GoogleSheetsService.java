package com.amud.io.aemudapi.services;


import com.amud.io.aemudapi.dto.MemberRequestDto;
import com.amud.io.aemudapi.dto.SpreadSheet;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface GoogleSheetsService {
    public void updateSpreadsheetData(String spreadsheetId, MemberRequestDto data) throws IOException, GeneralSecurityException;
    public SpreadSheet createGoogleSheet(String sheetName) throws GeneralSecurityException, IOException;
    public List<Object> readDataFromSheet(String spreadsheetId, String range) throws IOException, GeneralSecurityException;
    public void appendMemberDataToSheet(String spreadsheetId, List<MemberRequestDto> members) throws IOException, GeneralSecurityException;
}