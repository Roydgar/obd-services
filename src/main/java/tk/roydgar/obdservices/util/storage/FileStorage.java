package tk.roydgar.obdservices.util.storage;

import tk.roydgar.obdservices.domain.enums.LogFileType;

public interface FileStorage {
    void storeFile(LogFileType logFileType, String fileName, byte[] content);
    byte[] readFile(LogFileType logFileType, String fileName);
}
