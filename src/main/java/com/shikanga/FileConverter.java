package com.shikanga;

import java.io.File;
import java.io.IOException;

public interface FileConverter<E> {

    String supportedFileExtension();

    E readFromFile(File file) throws IOException;

    void writeToFile(String filePath, E valueType) throws IOException;
}
