package com.amud.io.aemudapi.services.impl;

import com.amud.io.aemudapi.dto.MemberRequestDto;
import com.amud.io.aemudapi.dto.SpreadSheet;
import com.amud.io.aemudapi.entities.Club;
import com.amud.io.aemudapi.prodivers.GoogleCredentialsProvider;
import com.amud.io.aemudapi.services.GoogleSheetsService;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Service
public class GoogleSheetsServiceImpl implements GoogleSheetsService {

    private static final String APPLICATION_NAME = "AEMUD API";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String DEFAULT_RANGE = "A1";
    private static final String DEFAULT_SHEET_NAME = "Test Reel Aemud App";
    Logger logger = Logger.getLogger(getClass().getName());

    private final GoogleCredentialsProvider credentialsProvider;

    public GoogleSheetsServiceImpl(GoogleCredentialsProvider credentialsProvider) {
        this.credentialsProvider = credentialsProvider;
    }

    /**
     * Initialise le service Google Sheets avec les informations d'authentification.
     */
    private Sheets getSheetService() throws IOException, GeneralSecurityException {
        try {
            final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            return new Sheets.Builder(httpTransport, JSON_FACTORY, credentialsProvider.getCredentials())
                    .setApplicationName(APPLICATION_NAME)
                    .build();
        } catch (java.net.UnknownHostException e) {
            // Gérer les problèmes de réseau
            throw new IOException("Problème de réseau", e);
        } catch (java.io.FileNotFoundException e) {
            // Gérer les problèmes de configuration
            logger.severe("Problème de configuration : " + e.getMessage());
            throw new IOException("Problème de configuration", e);
        } catch (com.google.api.client.auth.oauth2.TokenResponseException e) {
            // Gérer les problèmes d'authentification
            logger.severe("Problème d'authentification : " + e.getMessage());
            throw new IOException("Problème d'authentification", e);
        } catch (GeneralSecurityException e) {
            // Gérer les autres problèmes de sécurité
            logger.severe("Erreur de sécurité générale : " + e.getMessage());
            throw new GeneralSecurityException("Erreur de sécurité générale", e);
        }
    }

    /**
     * Crée une nouvelle feuille Google Sheets et y insère des données.
     *
     * @param sheetName Nom de la nouvelle feuille.
     * @return Un objet contenant l'ID et l'URL de la feuille créée.
     */
    @Override
    public SpreadSheet createGoogleSheet(String sheetName) throws GeneralSecurityException, IOException {
        Sheets service = getSheetService();
        Spreadsheet createdSpreadsheet = createSpreadsheet(service, sheetName);
        return buildGoogleSheetResponseDTO(createdSpreadsheet);
    }

    /**
     * Crée une nouvelle feuille de calcul Google Sheets avec un titre spécifique.
     *
     * @param service   Instance du service Google Sheets.
     * @param sheetName Nom de la nouvelle feuille.
     * @return La feuille de calcul créée.
     */
    private Spreadsheet createSpreadsheet(Sheets service, String sheetName) throws IOException {
        SpreadsheetProperties spreadsheetProperties = new SpreadsheetProperties().setTitle(sheetName);
        SheetProperties sheetProperties = new SheetProperties().setTitle(sheetName);
        Sheet sheet = new Sheet().setProperties(sheetProperties);
        Spreadsheet spreadsheet = new Spreadsheet().setProperties(spreadsheetProperties)
                .setSheets(Collections.singletonList(sheet));
        return service.spreadsheets().create(spreadsheet).execute();
    }

    /**
     * Met à jour une feuille de calcul avec des données.
     *
     * @param spreadsheetId  ID de la feuille de calcul à mettre à jour.
     * @param memberToUpdate Données à insérer dans la feuille de calcul.
     */
    @Override
    public void updateSpreadsheetData(String spreadsheetId, MemberRequestDto memberToUpdate) throws IOException, GeneralSecurityException {
        Sheets service = getSheetService();
        String range = DEFAULT_SHEET_NAME; // Nom de votre feuille

        ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
        List<List<Object>> values = response.getValues();

        if (values == null || values.isEmpty()) {
            logger.warning("No data found.");
            return;
        }

       int rowIndex = 1;

        if (rowIndex == -1) {
            logger.warning("Member ID not found.");
            return;
        }

        List<Object> updatedRow = convertMemberDtoToList(memberToUpdate);
        String updateRange = String.format("%s!A%d:Y%d", DEFAULT_SHEET_NAME, rowIndex + 1, rowIndex + 1);

        ValueRange body = new ValueRange().setValues(Collections.singletonList(updatedRow));
        service.spreadsheets().values()
                .update(spreadsheetId, updateRange, body)
                .setValueInputOption("RAW")
                .execute();
    }

    /**
     * Trouve l'index de la ligne correspondant à l'ID du membre.
     *
     * @param values   Les données de la feuille.
     * @param memberId ID du membre à rechercher.
     * @return L'index de la ligne si trouvé, sinon -1.
     */
    private int findRowIndex(List<List<Object>> values, Long memberId) {
        for (int i = 0; i < values.size(); i++) {
            List<Object> row = values.get(i);
            if (!row.isEmpty() && row.get(0).toString().equals(String.valueOf(memberId))) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Convertit un MemberRequestDto en une liste d'objets.
     *
     * @param member Le MemberRequestDto à convertir.
     * @return La liste d'objets représentant les données du membre.
     */
    private List<Object> convertMemberDtoToList(MemberRequestDto member) {
        return Arrays.asList(
        );
    }

    /**
     * Construit un objet de réponse contenant l'ID et l'URL de la feuille de calcul créée.
     *
     * @param spreadsheet La feuille de calcul créée.
     * @return Un objet de réponse contenant les informations sur la feuille de calcul.
     */
    private SpreadSheet buildGoogleSheetResponseDTO(Spreadsheet spreadsheet) {
        SpreadSheet spreadSheet = new SpreadSheet();
        spreadSheet.setSpreadSheetId(spreadsheet.getSpreadsheetId());
        spreadSheet.setSpeadSheetUrl(spreadsheet.getSpreadsheetUrl());
        return spreadSheet;
    }

    /**
     * Lit des données à partir d'une feuille de calcul Google Sheets.
     *
     * @param spreadsheetId ID de la feuille de calcul à lire.
     * @param range         Plage de cellules à lire.
     * @return Les données lues à partir de la feuille de calcul.
     */
    @Override
    public List<Object> readDataFromSheet(String spreadsheetId, String range) throws IOException, GeneralSecurityException {
        ValueRange response = getSheetService().spreadsheets().values().get(spreadsheetId, range).execute();
        List<List<Object>> values = response.getValues();
        if (values == null || values.isEmpty()) {
            logger.warning("No data found.");
            return Collections.emptyList();
        } else {
            return List.of(values);
        }
    }

    /**
     * Ajoute des données à une feuille de calcul Google Sheets.
     *
     * @param spreadsheetId ID de la feuille de calcul à mettre à jour.
     * @param range         Plage de cellules où les données seront ajoutées.
     * @param values        Données à ajouter à la feuille de calcul.
     */
    public void appendDataToSheet(String spreadsheetId, String range, List<List<Object>> values) throws IOException, GeneralSecurityException {
        Sheets service = getSheetService();
        ValueRange body = new ValueRange().setValues(values);
        AppendValuesResponse result = service.spreadsheets().values()
                .append(spreadsheetId, range, body)
                .setValueInputOption("RAW")
                .setInsertDataOption("INSERT_ROWS")
                .execute();
        logger.info("%d cells appended." + result.getUpdates().getUpdatedCells());
    }

    /**
     * Convertit une liste de MemberRequestDto en une liste de listes d'objets compatibles avec Google Sheets.
     *
     * @param members Liste de MemberRequestDto à convertir.
     * @return Liste de listes d'objets.
     */
    private List<List<Object>> convertMemberDtoToSheetData(List<MemberRequestDto> members) {
        List<List<Object>> data = new ArrayList<>();
        for (MemberRequestDto member : members) {
            List<Object> row = convertMemberDtoToList(member);
            data.add(row);
        }
        return data;
    }

    /**
     * Convertit une liste de Club en une chaîne de caractères séparée par des virgules.
     *
     * @param clubs Liste de clubs à convertir.
     * @return Chaîne de caractères représentant les clubs.
     */
    private String convertClubsToString(List<Club> clubs) {
        StringBuilder clubsString = new StringBuilder();
        for (Club club : clubs) {
            if (clubsString.length() > 0) {
                clubsString.append(", ");
            }
            clubsString.append(club.getName());
        }
        return clubsString.toString();
    }

    /**
     * Ajoute des données de membre à une feuille de calcul Google Sheets.
     *
     * @param spreadsheetId ID de la feuille de calcul à mettre à jour.
     * @param members       Liste des membres à ajouter à la feuille de calcul.
     */
    @Override
    public void appendMemberDataToSheet(String spreadsheetId, List<MemberRequestDto> members) throws IOException, GeneralSecurityException {
        List<List<Object>> data = convertMemberDtoToSheetData(members);
        appendDataToSheet(spreadsheetId, DEFAULT_RANGE, data);
    }
}
