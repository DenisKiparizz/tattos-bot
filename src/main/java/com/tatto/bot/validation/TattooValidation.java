package com.tatto.bot.validation;

import com.tatto.bot.dto.interfaces.TattooInterface;
import com.tatto.bot.exeptions.WrongModelException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TattooValidation implements ValidatorInterface<TattooInterface> {
    private static final Integer MIN = 2;

    @Override
    public void validate(TattooInterface tattooDto) {
        List<String> list = Arrays.asList(
                tattooDto.getPicture(),
                tattooDto.getDescription(),
                tattooDto.getUrl()
        );

        list.forEach(this::checkFieldsNotNull);
        list.forEach(this::checkFieldsNotBlank);
        list.forEach(this::checkFieldsLength);
        list.forEach(this::checkCorrectFields);
        checkPositiveId(tattooDto.getStyle().getId());
    }

    private void checkFieldsNotNull(String tattoo) {
        if (tattoo == null) {
            throw new WrongModelException("Related with null field in the Tattoo");
        }
    }

    private void checkFieldsNotBlank(String tattoo) {
        if (tattoo.isBlank()) {
            throw new WrongModelException("Related with blank field");
        }
    }

    private void checkFieldsLength(String parameter) {
        if (parameter.length() < MIN) {
            throw new WrongModelException(String.format("Length [%s] doesnt match the range: ", parameter));
        }
    }

    private void checkCorrectFields(String tattoo) {
        if (!isOnlyLetter(tattoo)) {
            throw new WrongModelException(String.format("Unacceptable characters: %s", tattoo));
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
