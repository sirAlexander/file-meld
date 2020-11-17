package com.shikanga.domain.user.cdi;

import javax.enterprise.util.AnnotationLiteral;

public final class FileTypeNameLiteral extends AnnotationLiteral<FileType> implements FileType {

    private final String name;

    public FileTypeNameLiteral(String name) {
        this.name = name;
    }

    @Override
    public String value() {
        return this.name;
    }
}
