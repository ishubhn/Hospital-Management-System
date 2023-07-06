package io.management.hospital.service.impl;

import io.management.hospital.entities.OwnerEntity;
import io.management.hospital.entities.dto.mapper.OwnerMapper;
import io.management.hospital.entities.dto.request.OwnerRequest;
import io.management.hospital.entities.dto.response.MessageResponse;
import io.management.hospital.entities.dto.response.OwnerResponse;
import io.management.hospital.exception.NoSuchOwnerEntityException;
import io.management.hospital.repositories.OwnerEntityRepository;
import io.management.hospital.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static io.management.hospital.entities.dto.mapper.OwnerMapper.toOwnerEntity;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerEntityRepository repo;

    private String status = "SUCCESS";

    @Override
    public MessageResponse addOwner(OwnerRequest request) throws NullPointerException {
        MessageResponse message;

        if (request != null) {
            // ID Generation
            String ownerId = UUID.randomUUID().toString();
            OwnerEntity ownerEntity = toOwnerEntity(request);
            ownerEntity.setOwnerId(ownerId);

            repo.save(ownerEntity);

            message = new MessageResponse(String.format("Owner created successfully -> %s", request.getEmailId()),
                    status);
        } else {
            message = new MessageResponse("An error occurred while creating new Owner Entity", "ERROR");
        }
        return message;
    }

    @Override
    public List<OwnerResponse> getAllOwnersByHospitalId(String hospitalId) {
        return repo.findAllByHospitalOwnedId(hospitalId)
                .stream()
                .map(OwnerMapper::toOwnerResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OwnerResponse getOwnerById(String id) {
        return repo.findById(id)
                .stream()
                .findFirst()
                .map(OwnerMapper::toOwnerResponse)
                .orElseThrow(() ->
                        new NoSuchOwnerEntityException(String.format("No such owner exist with id -> %s", id)));
    }

    @Override
    public MessageResponse assignHospitalToOwner(String ownerId, String hospitalId) throws NullPointerException {
        Optional<OwnerEntity> entity = repo.findById(ownerId);
        Set<String> hospitalsId = null;
        MessageResponse response = null;

        if (!entity.isEmpty()) {
            hospitalsId.add(hospitalId);
            entity.get().setHospitalOwnedId(hospitalsId);
            response = new MessageResponse(
                    String.format("Hospital -> %s added successfully for owner -> %s", hospitalId, ownerId),
                    status);
        }

        return response;
    }

    @Override
    public MessageResponse removeOwnerById(String id) {

        MessageResponse messageResponse;

        if (ifOwnerExist(id)) {
            repo.deleteById(id);
            messageResponse = new MessageResponse(String.format("Owner entity deleted" +
                    "successfully -> ", id), status);
        } else {
            throw new NoSuchOwnerEntityException(
                    String.format("No such owner entity exist with id -> %s", id));
        }

        return messageResponse;
    }

    public boolean ifOwnerExist(String id) {
        Optional<OwnerEntity> ownerEntity = repo.findById(id);
        return !ownerEntity.isEmpty();
    }
}
