package tk.roydgar.obdservices.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import tk.roydgar.obdservices.domain.enums.LogFileType;

import java.util.Map;

@ConfigurationProperties(prefix = "file-storage")
@Component
@Getter
@Setter
public class FileStorageProperties {

    private Map<LogFileType, FileInfo> logFileTypeToInfoMap;

    @Getter
    @Setter
    public static class FileInfo {
        private String fileNameFormat;
        private String path;
    }
}
