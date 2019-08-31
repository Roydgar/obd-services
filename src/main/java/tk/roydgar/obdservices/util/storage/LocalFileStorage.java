package tk.roydgar.obdservices.util.storage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import tk.roydgar.obdservices.config.properties.FileStorageProperties;
import tk.roydgar.obdservices.domain.enums.LogFileType;
import tk.roydgar.obdservices.exception.CannotStoreFileException;

import java.io.File;
import java.io.IOException;

@Component
@Profile("dev")
@RequiredArgsConstructor
@Slf4j
public class LocalFileStorage implements FileStorage {

    private final FileStorageProperties properties;

    public void storeFile(LogFileType logFileType, String fileName, byte[] content) {
        FileStorageProperties.FileInfo fileInfo = properties.getLogFileTypeToInfoMap().get(logFileType);
        try {
            String filePath = fileInfo.getPath() + File.separator + fileName;
            File file = new File(filePath);

            if (file.exists()) {
                log.info("File with name {} already exists. Deleting", fileName);
                file.delete();
            }

            log.info("Saving file with name {} to local disk: {}", fileName, filePath);
            FileUtils.writeByteArrayToFile(new File(filePath), content);
        } catch (IOException e) {
            throw new CannotStoreFileException(e);
        }
    }

    public byte[] readFile(LogFileType logFileType, String fileName) {
        FileStorageProperties.FileInfo fileInfo = properties.getLogFileTypeToInfoMap().get(logFileType);
        try {
            String filePath = fileInfo.getPath() + File.separator + fileName;

            log.info("Reading file with name {} from local disk", fileName);
            return FileUtils.readFileToByteArray(new File(filePath));
        } catch (IOException e) {
            throw new CannotStoreFileException(e);
        }
    }

}
