package io.management.patient.service.impl;

import io.management.patient.entity.AddressEntity;
import io.management.patient.entity.PatientEntity;
import io.management.patient.entity.dto.mapper.PatientMapper;
import io.management.patient.entity.dto.request.PatientRequest;
import io.management.patient.entity.dto.response.MessageResponse;
import io.management.patient.entity.dto.response.PatientResponse;
import io.management.patient.entity.external.Hospital;
import io.management.patient.exception.UserAlreadyPresentException;
import io.management.patient.exception.UserNotFoundException;
import io.management.patient.repository.PatientEntityRepository;
import io.management.patient.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static io.management.patient.entity.dto.mapper.PatientMapper.toPatientResponse;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private PatientEntityRepository userRepo;

    public PatientResponse createUser(PatientRequest user) {
        if (isUserPresent(user.getEmailId())) {
            throw new UserAlreadyPresentException(String.format("User '%s' already exist", user.getEmailId()));
        } else {
            // User Entity
            String uuid = UUID.randomUUID().toString();
            String addressUuid = UUID.randomUUID().toString();
            PatientEntity userEntity = PatientMapper.toPatientRequest(user);
            userEntity.setUserId(uuid);

            // Address Entity
            AddressEntity addressEntity = userEntity.getAddress();
            addressEntity.setAddressId(addressUuid);
            userRepo.save(userEntity);

            log.info("User %s created successfully", user.getEmailId());
            return toPatientResponse(PatientMapper.toPatientRequest(user));
        }
    }

    public List<PatientResponse> getAllUsers() {
        log.info("Users returned successfully");
        return userRepo.findAll().stream().map(PatientMapper::toPatientResponse).collect(Collectors.toList());
    }

    public PatientResponse getUserByEmailId(String emailId) {
        log.info("User -> {} is called", emailId);
        return userRepo.findByEmailId(emailId)
                .stream()
                .map(PatientMapper::toPatientResponse)
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException(String.format("User '%s' not found", emailId)));
    }

    @Transactional  //No EntityManager with actual transaction available for current thread -
    // cannot reliably process 'remove' call
    public MessageResponse deleteUserById(String emailId) {
        log.info("User -> {} is called for delete", emailId);
        try {
            userRepo.deleteByEmailId(emailId);
            log.info("User {} deleted successfully", emailId);
            String message = "User deleted successfully";
            return new MessageResponse(message, "Success");
        } catch (Exception e) {
            log.error(e.toString());
            throw new UserNotFoundException(String.format("User %s not found", emailId));
        }
    }

    public boolean isUserPresent(String emailId) {
        PatientEntity user = userRepo.findByEmailId(emailId).orElse(null);
        return user != null;  //true = user is not null
    }
}
