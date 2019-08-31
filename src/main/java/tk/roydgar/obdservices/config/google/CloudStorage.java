package tk.roydgar.obdservices.config.google;

import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("gcp")
public class CloudStorage {

    private String storageBucketName;

    public CloudStorage(@Value("${google.storage.bucket-name}") String storageBucketName) {
        this.storageBucketName = storageBucketName;
    }

    @Bean
    public Bucket storageBucket() {
        return StorageOptions.getDefaultInstance().getService().get(storageBucketName);
    }
}
