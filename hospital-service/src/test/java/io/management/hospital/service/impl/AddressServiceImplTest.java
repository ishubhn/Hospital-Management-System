package io.management.hospital.service.impl;

import io.management.hospital.entities.AddressEntity;
import io.management.hospital.entities.HospitalEntity;
import io.management.hospital.entities.dto.response.HospitalResponse;
import io.management.hospital.repositories.HospitalEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddressServiceImplTest {
    @Mock
    private HospitalEntityRepository repo;

    @InjectMocks
    private AddressServiceImpl addressService;

    @Test
    void getHospitalByPincodeTest() {
        String pincode = "123456";
        // Mocking the repo
        List<HospitalEntity> hospitals = new ArrayList<>();
        hospitals.add(new HospitalEntity("123", "Hospital 1", new AddressEntity("1", "123 Main St", "City1", "State1", "123456", "Country1"), "1234567890", "9876543210",
                "hospital1@example.com", "password1"));
        hospitals.add(new HospitalEntity("456", "Hospital 2", new AddressEntity("1", "123 Main St", "City1", "State1", "123456", "Country1"), "0987654321", "0123456789",
                "hospital2@example.com", "password2"));
        when(repo.findByPincode("123456")).thenReturn(hospitals);

        // calling method under test
        List<HospitalResponse> responses = addressService.getHospitalsByPincode(pincode);

        // Assertions
        assertEquals(2, responses.size());
        assertEquals("Hospital 1", responses.get(0).getName());
        assertEquals("Hospital 2", responses.get(1).getName());
    }
}
