package com.tatto.bot.validation;

import com.tatto.bot.dto.interfaces.StyleInterface;
import com.tatto.bot.exeptions.WrongModelException;
import org.springframework.stereotype.Service;

@Service
public class StyleValidation implements ValidatorInterface<StyleInterface> {
    private static final Integer MAX = 12;
    private static final Integer MIN = 2;

    @Override
    public void validate(StyleInterface styleRequest) {
        checkFieldNotNull(styleRequest);
        checkFieldsNotBlank(styleRequest);
        checkFieldsLength(styleRequest);
        checkCorrectFields(styleRequest);
    }

   public void checkFieldNotNull(StyleInterface styleRequest) {
        if (styleRequest.getStyle() == null) {
            throw new WrongModelException("Related with null style field");
        }
    }

    public void checkFieldsNotBlank(StyleInterface styleRequest) {
        if (styleRequest.getStyle().isBlank()) {
            throw new WrongModelException("Related with blank style field");
        }
    }

    public void checkFieldsLength(StyleInterface styleRequest) {
        if (styleRequest.getStyle().length() > MAX || styleRequest.getStyle().length() < MIN) {
            throw new WrongModelException(String.format("Length [%s] doesnt match the range: ", styleRequest.getStyle()));
        }
    }

    public void checkCorrectFields(StyleInterface styleRequest) {
        if (!isOnlyLetter(styleRequest.getStyle())) {
            throw new WrongModelException(String.format("Unacceptable characters: %s", styleRequest.getStyle()));
        }
    }

    @Override
    public boolean isOnlyLetter(String name) {
        return name.matches("[a-zA-Z]+");
    }

    @Override
    public void checkPositiveId(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id has to be positive");
        }
    }
}
