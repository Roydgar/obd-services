package tk.roydgar.obdservices.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tk.roydgar.obdservices.domain.Car;
import tk.roydgar.obdservices.domain.CarLog;
import tk.roydgar.obdservices.domain.dto.request.carlog.CreateCarLogRequest;
import tk.roydgar.obdservices.domain.dto.request.carlog.UpdateCarLogRequest;
import tk.roydgar.obdservices.domain.enums.LogFileType;
import tk.roydgar.obdservices.exception.ExceptionFactory;
import tk.roydgar.obdservices.mapper.request.CarLogRequestMapper;
import tk.roydgar.obdservices.repository.CarLogRepository;
import tk.roydgar.obdservices.util.MultipartLogFile;
import tk.roydgar.obdservices.util.storage.UniqueFileNameCreator;
import tk.roydgar.obdservices.util.storage.FileStorage;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarLogService {

    private final CarLogRepository carLogRepository;
    private final CarService carService;
    private final CarLogRequestMapper carLogRequestMapper;
    private final FileStorage fileStorage;
    private final UniqueFileNameCreator uniqueFileNameCreator;

    public CarLog getById(Long id) {
        return carLogRepository.findById(id)
                .orElseThrow(() -> ExceptionFactory.carLogNotFoundException(id));
    }

    public List<CarLog> getAll() {
        return carLogRepository.findAll();
    }

    public List<CarLog> getAllByCarId(Long carId) {
        return carLogRepository.findAllByCarId(carId);
    }

    @Transactional
    public CarLog create(CreateCarLogRequest request, MultipartFile multipartFile) throws IOException {
        return createOrUpdate(request, multipartFile);
    }

    @Transactional
    public CarLog update(UpdateCarLogRequest request, MultipartFile multipartFile) throws IOException {
        return createOrUpdate(request, multipartFile);
    }

    public MultipartFile getCarLogFile(Long id) {
        CarLog carLog = carLogRepository.findById(id)
                .orElseThrow(() -> ExceptionFactory.carLogNotFoundException(id));
        byte[] fileContent = fileStorage.readFile(LogFileType.CAR_LOG, carLog.getFileName());
        return new MultipartLogFile(fileContent, carLog.getFileName());
    }

    @Transactional
    public CarLog delete(Long id) {
        CarLog carLog = carLogRepository.findById(id)
                .orElseThrow(() -> ExceptionFactory.carLogNotFoundException(id));

        carLogRepository.delete(carLog);
        return carLog;
    }

    private CarLog createOrUpdate(CreateCarLogRequest request, MultipartFile multipartFile) throws IOException {
        CarLog carLog = carLogRequestMapper.toEntity(request);
        Car car = carService.getById(request.getCarId());
        carLog.setCar(car);
        CarLog carLogWithId = carLogRepository.save(carLog);

        String fileName = uniqueFileNameCreator.createFileName(LogFileType.CAR_LOG, carLogWithId.getId());
        carLogWithId.setFileName(fileName);
        carLogRepository.save(carLogWithId);

        fileStorage.storeFile(LogFileType.CAR_LOG, fileName, multipartFile.getBytes());
        return carLogWithId;
    }

}
