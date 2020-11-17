package com.shikanga.domain.user.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.shikanga.FileConverter;
import com.shikanga.domain.user.User;
import com.shikanga.domain.user.Users;
import com.shikanga.domain.user.cdi.FileType;
import com.shikanga.config.JsonMapper;
import com.shikanga.domain.user.mixin.UserJsonMixin;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@Singleton
@FileType("json")
public final class UserJsonFileConverter implements FileConverter<Users> {

    public static final String JSON_EXTENSION = ".json";

    private final JsonMapper jsonMapper;

    @Inject
    public UserJsonFileConverter(JsonMapper jsonMapper) {
        this.jsonMapper = (JsonMapper) jsonMapper.addMixIn(User.class, UserJsonMixin.class);
    }

    @Override
    public String supportedFileExtension() {
        return JSON_EXTENSION;
    }

    @Override
    public Users readFromFile(File file) throws IOException {
        log.info("reading from {} file - {}", JSON_EXTENSION, file);
        List<User> userList = jsonMapper.readValue(file, new TypeReference<>() {
        });
        return Users.builder()
                .users(userList)
                .build();
    }

    @Override
    public void writeToFile(String filePath, Users users) throws IOException {
        log.info("writing out to an {} file - {}", JSON_EXTENSION, filePath);
        jsonMapper.writeValue(new File(filePath), users.getUsers());
    }
}
