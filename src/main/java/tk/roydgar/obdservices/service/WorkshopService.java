package tk.roydgar.obdservices.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.roydgar.obdservices.domain.Workshop;
import tk.roydgar.obdservices.domain.dto.request.workshop.CreateWorkshopRequest;
import tk.roydgar.obdservices.domain.dto.request.workshop.UpdateWorkshopRequest;
import tk.roydgar.obdservices.exception.ExceptionFactory;
import tk.roydgar.obdservices.mapper.request.WorkshopRequestMapper;
import tk.roydgar.obdservices.repository.WorkshopRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkshopService {

    private final WorkshopRepository workshopRepository;
    private final WorkshopRequestMapper workshopRequestMapper;

    private final PasswordEncoder passwordEncoder;

    public Workshop getById(Long id) {
        return workshopRepository.findById(id)
                .orElseThrow(() -> ExceptionFactory.workshopNotFoundException(id));
    }

    public List<Workshop> getAll() {
        return workshopRepository.findAll();
    }

    public Workshop create(CreateWorkshopRequest request) {
        Workshop workshop = workshopRequestMapper.toEntity(request);
        workshop.setPassword(passwordEncoder.encode(request.getPassword()));
        return workshopRepository.save(workshop);
    }

    public Workshop update(UpdateWorkshopRequest request) {
        Workshop workshop = workshopRequestMapper.toEntity(request);
        workshop.setPassword(passwordEncoder.encode(request.getPassword()));
        return workshopRepository.save(workshop);
    }

    @Transactional
    public Workshop delete(Long id) {
        Workshop workshop = workshopRepository.findById(id)
                .orElseThrow(() -> ExceptionFactory.workshopNotFoundException(id));

        workshopRepository.delete(workshop);
        return workshop;
    }

}
