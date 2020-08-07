package com.tatto.bot.validation;

import com.tatto.bot.dto.StyleDto;
import com.tatto.bot.exeptions.WrongModelException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class StyleValidationTest {

    private static final String JAPAN = "JAPAN";
    private static final StyleDto JAPAN_DTO = new StyleDto(1L, JAPAN);
    private static final StyleDto JAPAN_DTO_NULL = new StyleDto(1L, null);
    private static final StyleDto JAPAN_DTO_BLANK = new StyleDto(1L, " ");
    private static final StyleDto JAPAN_DTO_WRONG_FIELD = new StyleDto(1L, "J1P2N");
    private static final StyleDto JAPAN_DTO_WRONG_LENGTH = new StyleDto(1L, "J");
    private static final Long NEGATIVE_ID = -2L;

    @InjectMocks
    StyleValidation validation;

    @Test
    void checkFieldNotNull() {
        assertThatCode(() -> validation.checkFieldNotNull(JAPAN_DTO))
                .doesNotThrowAnyException();
    }

    @Test
    void checkFieldsNotBlank() {
        assertThatCode(() -> validation.checkFieldsNotBlank(JAPAN_DTO))
                .doesNotThrowAnyException();
    }

    @Test
    void checkFieldsLength() {
        assertThatCode(() -> validation.checkFieldsLength(JAPAN_DTO))
                .doesNotThrowAnyException();
    }

    @Test
    void checkCorrectFields() {
        assertThatCode(() -> validation.checkCorrectFields(JAPAN_DTO))
                .doesNotThrowAnyException();
    }

    @Test
    void isOnlyLetter() {
        assertTrue(validation.isOnlyLetter(JAPAN));
    }

    @Test
    void throwWrongModelExceptionIfFieldIsNull() {
        assertThrows(WrongModelException.class, () -> validation.checkFieldNotNull(JAPAN_DTO_NULL));
    }

    @Test
    void throwWrongModelExceptionIfFieldIsBlank() {
        assertThrows(WrongModelException.class, () -> validation.checkFieldsNotBlank(JAPAN_DTO_BLANK));
    }

    @Test
    void throwWrongModelExceptionIfFieldIsWrong() {
        assertThrows(WrongModelException.class, () -> validation.checkCorrectFields(JAPAN_DTO_WRONG_FIELD));
    }

    @Test
    void throwWrongModelExceptionIfFieldIsWrongLength() {
        assertThrows(WrongModelException.class, () -> validation.checkFieldsLength(JAPAN_DTO_WRONG_LENGTH));
    }

    @Test
    void throwIllegalArgumentExceptionIfNegativeId() {
        assertThrows(IllegalArgumentException.class, () -> validation.checkPositiveId(NEGATIVE_ID));
    }
}
