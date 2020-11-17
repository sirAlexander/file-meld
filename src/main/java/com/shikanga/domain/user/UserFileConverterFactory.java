package com.shikanga.domain.user;

import com.shikanga.FileConverter;
import com.shikanga.domain.user.cdi.FileTypeNameLiteral;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class UserFileConverterFactory {

    @Inject
    @Any
    Instance<FileConverter<Users>> fileConverterInstance;

    public FileConverter<Users> createFileConverter(String _fileType) {
        Instance<FileConverter<Users>> instance = this.fileConverterInstance.select(new FileTypeNameLiteral(_fileType));

        if (!instance.isResolvable()) {
            throw new IllegalArgumentException("file type " + _fileType + " not supported");
        }

        return instance.get();
    }
}
