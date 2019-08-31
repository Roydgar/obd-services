package tk.roydgar.obdservices.util.storage;

import com.google.cloud.storage.Bucket;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import tk.roydgar.obdservices.config.properties.FileStorageProperties;
import tk.roydgar.obdservices.domain.enums.LogFileType;

@Component
@Profile("gcp")
@RequiredArgsConstructor
@Slf4j
public class GoogleCloudFileStorage implements FileStorage {

    private final Bucket storageBucket;

    @Override
    public void storeFile(LogFileType logFileType, String fileName, byte[] content) {
        log.info("Saving file with type {} and name {} to Google Cloud Storage", logFileType, fileName);
        storageBucket.create(fileName, content);
    }

    @Override
    public byte[] readFile(LogFileType logFileType, String fileName) {
        log.info("Fetching file with type {} and name {} from Google Cloud Storage", logFileType, fileName);
        return storageBucket.get(fileName).getContent();
    }
}
