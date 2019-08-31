package tk.roydgar.obdservices.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tk.roydgar.obdservices.domain.Car;
import tk.roydgar.obdservices.domain.CarErrorLog;
import tk.roydgar.obdservices.domain.dto.request.carerrorlog.CreateCarErrorLogRequest;
import tk.roydgar.obdservices.domain.dto.request.carerrorlog.UpdateCarErrorLogRequest;
import tk.roydgar.obdservices.domain.enums.LogFileType;
import tk.roydgar.obdservices.exception.ExceptionFactory;
import tk.roydgar.obdservices.mapper.request.CarErrorLogRequestMapper;
import tk.roydgar.obdservices.repository.CarErrorLogRepository;
import tk.roydgar.obdservices.util.storage.UniqueFileNameCreator;
import tk.roydgar.obdservices.util.storage.FileStorage;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarErrorLogService {

    private final CarErrorLogRepository carErrorLogRepository;
    private final CarService carService;
    private final CarErrorLogRequestMapper carErrorLogRequestMapper;
    private final FileStorage fileStorage;
    private final UniqueFileNameCreator uniqueFileNameCreator;

    public CarErrorLog getById(Long id) {
        return carErrorLogRepository.findById(id)
                .orElseThrow(() -> ExceptionFactory.carErrorLogNotFoundException(id));
    }

    public List<CarErrorLog> getAll() {
        return carErrorLogRepository.findAll();
    }

    public List<CarErrorLog> getAllByCarId(Long carId) {
        return carErrorLogRepository.findAllByCarId(carId);
    }

    public CarErrorLog create(CreateCarErrorLogRequest createCarErrorLogRequest, MultipartFile multipartFile)
            throws IOException {
        return createOrUpdate(createCarErrorLogRequest, multipartFile);
    }

    public CarErrorLog update(UpdateCarErrorLogRequest updateCarErrorLogRequest, MultipartFile multipartFile)
            throws IOException {
        return createOrUpdate(updateCarErrorLogRequest, multipartFile);
    }

    public byte[] getCarLogFileAsByteArray(Long id) {
        CarErrorLog carErrorLog = carErrorLogRepository.findById(id)
                .orElseThrow(() -> ExceptionFactory.carErrorLogNotFoundException(id));
        return fileStorage.readFile(LogFileType.CAR_ERROR_LOG, carErrorLog.getFileName());
    }

    @Transactional
    public CarErrorLog delete(Long id) {
        CarErrorLog carErrorLog = carErrorLogRepository.findById(id)
                .orElseThrow(() -> ExceptionFactory.carErrorLogNotFoundException(id));

        carErrorLogRepository.delete(carErrorLog);
        return carErrorLog;
    }

    private CarErrorLog createOrUpdate(CreateCarErrorLogRequest request, MultipartFile file) throws IOException {
        CarErrorLog carErrorLog = carErrorLogRequestMapper.toEntity(request);
        Car car = carService.getById(request.getCarId());
        carErrorLog.setCar(car);
        CarErrorLog carErrorLogWithId =  carErrorLogRepository.save(carErrorLog);

        String fileName = uniqueFileNameCreator.createFileName(LogFileType.CAR_ERROR_LOG, carErrorLogWithId.getId());
        carErrorLogWithId.setFileName(fileName);
        carErrorLogRepository.save(carErrorLogWithId);

        fileStorage.storeFile(LogFileType.CAR_ERROR_LOG, fileName, file.getBytes());
        return carErrorLogWithId;
    }

}
