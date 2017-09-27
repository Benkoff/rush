package com.javarush.task.task31.task3110;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private Path rootPath;
    private List<Path> fileList;

    public FileManager(Path rootPath) throws IOException {
        this.rootPath = rootPath;
        fileList = new ArrayList<>();
        collectFileList(rootPath);
    }

    public List<Path> getFileList() {
        return fileList;
    }

    private void collectFileList(Path path) {
        if (Files.isRegularFile(path)) {
            fileList.add(rootPath.relativize(path));
        }
        else if (Files.isDirectory(path)) {
            try (DirectoryStream directoryStream = Files.newDirectoryStream(path)) {
                for (Object entry : directoryStream) {
                    collectFileList((Path)entry);
                }
            }
            catch (IOException e) {
            }
        }
    }
}
