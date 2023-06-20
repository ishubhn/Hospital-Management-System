package io.management.hospital.service.impl;

import io.management.hospital.repositories.OwnerEntityRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OwnerServiceImplTest {
    @Mock
    private OwnerEntityRepository repo;

    @Test
    void addOwner_OwnerExists_ShouldReturnMessageResponse() {

    }
}
