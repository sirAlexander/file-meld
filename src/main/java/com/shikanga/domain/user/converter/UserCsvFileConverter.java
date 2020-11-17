package com.shikanga.domain.user.converter;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.shikanga.FileConverter;
import com.shikanga.domain.user.User;
import com.shikanga.domain.user.Users;
import com.shikanga.domain.user.cdi.FileType;
import com.shikanga.domain.user.mixin.UserCsvMixin;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Singleton
@FileType("csv")
public final class UserCsvFileConverter implements FileConverter<Users> {

    public static final String CSV_EXTENSION = ".csv";

    private final CsvMapper csvMapper;

    @Inject
    public UserCsvFileConverter(CsvMapper csvMapper) {
        this.csvMapper = (CsvMapper) csvMapper.addMixIn(User.class, UserCsvMixin.class);

    }

    @Override
    public String supportedFileExtension() {
        return CSV_EXTENSION;
    }

    @Override
    public Users readFromFile(File file) throws IOException {
        log.info("reading from {} file - {}", CSV_EXTENSION, file);
        MappingIterator<User> mappingIterator = getObjectReader().readValues(file);
        List<User> userList = new ArrayList<>();
        while (mappingIterator.hasNext()) {
            userList.add(mappingIterator.next());
        }
        return Users.builder()
                .users(userList)
                .build();
    }

    @Override
    public void writeToFile(String filePath, Users users) throws IOException {
        log.info("writing out to an {} file - {}", CSV_EXTENSION, filePath);
        getObjectWriter().writeValue(new File(filePath), users.getUsers());
    }

    public ObjectReader getObjectReader() {
        return csvMapper.readerFor(User.class).with(getCsvSchema());
    }

    public ObjectWriter getObjectWriter() {
        return csvMapper.writer(getCsvSchema());
    }

    private CsvSchema getCsvSchema() {
        return csvMapper.schemaFor(User.class)
                .withHeader()
                .withoutQuoteChar();
    }


}
