package tk.roydgar.obdservices.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import static java.lang.String.format;

public class WebEntityUtils {

    private final static String CONTENT_DISPOSITION_FORMAT = "attachment; filename=\"%s\"";
    private WebEntityUtils() {
    }

    @SneakyThrows
    public static ResponseEntity<byte[]> createFileResponse(MultipartFile file) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", format(CONTENT_DISPOSITION_FORMAT, file.getName()))
                .body(file.getBytes());
    }

}
