package tk.roydgar.obdservices.util.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tk.roydgar.obdservices.config.properties.FileStorageProperties;
import tk.roydgar.obdservices.domain.enums.LogFileType;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class UniqueFileNameCreator {

    private final FileStorageProperties fileStorageProperties;

    public String createFileName(LogFileType logFileType, Long fileId) {
        FileStorageProperties.FileInfo fileInfo = fileStorageProperties.getLogFileTypeToInfoMap().get(logFileType);
        return format(fileInfo.getFileNameFormat(), fileId);
    }

}
