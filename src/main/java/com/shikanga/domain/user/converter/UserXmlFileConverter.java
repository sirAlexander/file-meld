package com.shikanga.domain.user.converter;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.shikanga.FileConverter;
import com.shikanga.domain.user.Users;
import com.shikanga.domain.user.cdi.FileType;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.File;
import java.io.IOException;

@Slf4j
@Singleton
@FileType("xml")
public final class UserXmlFileConverter implements FileConverter<Users> {

    public static final String XML_EXTENSION = ".xml";

    private final XmlMapper xmlMapper;

    @Inject
    public UserXmlFileConverter(XmlMapper xmlMapper) {
        this.xmlMapper = xmlMapper;
    }

    @Override
    public String supportedFileExtension() {
        return XML_EXTENSION;
    }

    @Override
    public Users readFromFile(File file) throws IOException {
        log.info("reading from {} file - {}", XML_EXTENSION, file);
        return xmlMapper.readValue(file, Users.class);
    }

    @Override
    public void writeToFile(String filePath, Users users) throws IOException {
        log.info("writing out to an {} file - {}", XML_EXTENSION, filePath);
        xmlMapper.writeValue(new File(filePath), users);
    }
}
