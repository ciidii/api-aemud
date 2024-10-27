package com.amud.io.aemudapi.DTO;

import com.amud.io.aemudapi.dto.SpreadSheet;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class SpreadSheetTest {

    private SpreadSheet spreadSheet;
    @BeforeEach
    void setUp() {
        spreadSheet = new SpreadSheet();
    }
    @Test
    void testGetterAndSetters(){
        //then
        spreadSheet.setSpreadSheetId("1xQlpZjZkP_02xE9pkvUWTta2DJXRo4ImNj9HkmqKe00");
        spreadSheet.setSpeadSheetUrl("https://www.google.com");
        //
        Assertions.assertThat(spreadSheet.getSpreadSheetId()).isEqualTo("1xQlpZjZkP_02xE9pkvUWTta2DJXRo4ImNj9HkmqKe00");
        assertThat(spreadSheet.getSpeadSheetUrl()).isEqualTo("https://www.google.com");
    }
}