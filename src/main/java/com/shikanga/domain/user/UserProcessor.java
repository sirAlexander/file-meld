package com.shikanga.domain.user;

import com.shikanga.FileConverter;
import com.shikanga.FileReader;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Dependent
public final class UserProcessor {

    private final FileReader fileReader;
    private final UserFileConverterFactory userFileConverterFactory;

    @Inject
    public UserProcessor(FileReader fileReader,
                         UserFileConverterFactory userFileConverterFactory) {
        this.fileReader = fileReader;
        this.userFileConverterFactory = userFileConverterFactory;
    }

    public void mergeDataFiles(String pathToDataFolder, String pathToResultsFolder) {
        List<File> allFiles = fileReader.getAllFiles(pathToDataFolder);
        Users users = getSortedUsers(allFiles);
        fileReader.getFileExtensions(allFiles)
                .forEach(fileExtension -> writeToResultsFolder(users, pathToResultsFolder, fileExtension));
    }

    private Users getSortedUsers(List<File> allFiles) {
        List<User> userList = new ArrayList<>();
        allFiles.forEach(file -> userList.addAll(buildUserList(file)));
        userList.sort(Comparator.comparing(User::getUserId));
        return Users.builder()
                .users(userList)
                .build();
    }

    private List<User> buildUserList(File file) {
        String fileExtension = fileReader.getFileExtension(file.getName());
        FileConverter<Users> fileConverter = userFileConverterFactory.createFileConverter(fileExtension);
        Users users = getConvertedFileUsers(fileConverter, file);
        return users.getUsers();
    }

    private Users getConvertedFileUsers(FileConverter<Users> fileConverter, File file) {
        Users result = null;
        try {
            result = fileConverter.readFromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void writeToResultsFolder(Users users,
                                      String pathToResultsFolder,
                                      String fileExtension) {
        FileConverter<Users> fileConverter = userFileConverterFactory.createFileConverter(fileExtension);
        try {
            fileConverter.writeToFile(pathToResultsFolder.concat(fileConverter.supportedFileExtension()), users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
