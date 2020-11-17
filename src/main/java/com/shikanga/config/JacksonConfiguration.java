package com.shikanga.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

@Singleton
public final class JacksonConfiguration {

    @Produces
    public XmlMapper xmlMapper() {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule());
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
        xmlMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        xmlMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        return xmlMapper;
    }

    @Produces
    public CsvMapper csvMapper() {
        CsvMapper csvMapper = new CsvMapper();
        csvMapper.registerModule(new JavaTimeModule());
        csvMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        csvMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        return csvMapper;
    }

    @Produces
    public JsonMapper jsonMapper() {
        JsonMapper jsonMapper = new JsonMapper();
        jsonMapper.registerModule(new JavaTimeModule());
        jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        jsonMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        jsonMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        return jsonMapper;
    }
}
