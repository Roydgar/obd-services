package tk.roydgar.obdservices.util;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.roydgar.obdservices.config.properties.FileStorageProperties;
import tk.roydgar.obdservices.domain.enums.LogFileType;
import tk.roydgar.obdservices.exception.CannotStoreFileException;

import java.io.File;
import java.io.IOException;

@Component
public class FileStorage {

    @Autowired
   private FileStorageProperties properties;

    public void storeFile(LogFileType logFileType, Long id, byte[] content){
        FileStorageProperties.FileInfo fileInfo = properties.getLogFileTypeToInfoMap().get(logFileType);

        try {
            String filePath = fileInfo.getPath() + File.separator + String.format(fileInfo.getFileNameFormat(), id);
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }

            FileUtils.writeByteArrayToFile(new File(filePath), content);
        } catch (IOException e) {
            throw new CannotStoreFileException(e);
        }
    }

    public byte[] readFile(LogFileType logFileType, Long id) {
        FileStorageProperties.FileInfo fileInfo = properties.getLogFileTypeToInfoMap().get(logFileType);

        try {
            String filePath = fileInfo.getPath() + File.separator + String.format(fileInfo.getFileNameFormat(), id);
            return FileUtils.readFileToByteArray(new File(filePath));
        } catch (IOException e) {
            throw new CannotStoreFileException(e);
        }
    }

}
