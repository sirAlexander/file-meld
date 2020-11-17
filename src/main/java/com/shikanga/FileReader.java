package com.shikanga;

import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Singleton
public final class FileReader {

    public List<File> getAllFiles(String pathToFolder) {
        List<File> results = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get(pathToFolder))) {
            results = paths
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("retrieved the following files {}", results);
        return results;
    }

    public Set<String> getFileExtensions(List<File> listOfFiles) {
        return listOfFiles.stream()
                .map(file -> getFileExtension(file.getName()))
                .collect(Collectors.toSet());
    }

    public String getFileExtension(String fileName) {
        return Optional.ofNullable(fileName)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(fileName.lastIndexOf(".") + 1))
                .orElse("");
    }
}
