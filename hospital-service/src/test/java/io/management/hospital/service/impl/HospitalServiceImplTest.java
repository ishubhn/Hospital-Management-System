package io.management.hospital.service.impl;

import io.management.hospital.entities.AddressEntity;
import io.management.hospital.entities.HospitalEntity;
import io.management.hospital.entities.dto.request.AddressRequest;
import io.management.hospital.entities.dto.request.HospitalRequest;
import io.management.hospital.entities.dto.response.HospitalResponse;
import io.management.hospital.entities.dto.response.MessageResponse;
import io.management.hospital.exception.HospitalAlreadyPresentException;
import io.management.hospital.exception.NoSuchHospitalExistException;
import io.management.hospital.repositories.HospitalEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HospitalServiceImplTest {
    @Mock
    private HospitalEntityRepository repo;

    @InjectMocks
    private HospitalServiceImpl hospitalService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllHospitals_ShouldReturnListOfHospitals() {
        // Mocking the repository
        HospitalEntity hospital1 = new HospitalEntity("123", "Hospital 1", new AddressEntity("1", "123 Main St", "City1", "State1", "123456", "Country1"), "1234567890", "9876543210",
                "hospital1@example.com", "password1");
        HospitalEntity hospital2 = new HospitalEntity("456", "Hospital 2", new AddressEntity("1", "123 Main St", "City1", "State1", "123456", "Country1"), "0987654321", "0123456789",
                "hospital2@example.com", "password2");
        List<HospitalEntity> hospitals = Arrays.asList(hospital1, hospital2);
        when(repo.findAll()).thenReturn(hospitals);

        // Calling method under test
        List<HospitalResponse> result = hospitalService.getAllHospitals();

        // Assertions
        assertEquals(2, result.size());
        assertEquals("Hospital 1", result.get(0).getName());
        assertEquals("Hospital 2", result.get(1).getName());
    }

    @Test
    void getHospitalByEmailId_ShouldReturnHospitalResponse() {
        String emailId = "hospital2@example.com";

        HospitalEntity hospital2 = new HospitalEntity("456", "Hospital 2", new AddressEntity("1", "123 Main St", "City1", "State1", "123456", "Country1"), "0987654321", "0123456789",
                "hospital2@example.com", "password2");

        when(repo.findByEmailId(emailId)).thenReturn(Optional.of(hospital2));

        // Act
        HospitalResponse result = hospitalService.getHospitalByEmailId(emailId);

        // Assert
        assertEquals(emailId, result.getEmailId());
    }

    @Test
    void getHospitalByEmailId_NonExistingHospital_ShouldThrowNoSuchHospitalExistException() {
        String emailId = "error@example.com";

        when(repo.findByEmailId(emailId)).thenReturn(Optional.empty());

        assertThrows(NoSuchHospitalExistException.class, () -> hospitalService.getHospitalByEmailId(emailId));
    }

    @Test
    void createHospital_NewHospital_ShouldReturnSuccessMessageResponse() throws HospitalAlreadyPresentException {
        // Arrange
        HospitalRequest request = new HospitalRequest("Hospital 1", new AddressRequest("abc", "City", "State", "123456"), "1234567890", "1234567891", "email@example.com", "123456@adQ");

        HospitalEntity savedHospitalEntity = new HospitalEntity();
        savedHospitalEntity.setHospitalId(UUID.randomUUID().toString());

        when(repo.findByEmailId(request.getEmailId())).thenReturn(Optional.empty());

        // Act
        MessageResponse response = hospitalService.createHospital(request);

        // Assert
        assertEquals("SUCCESS", response.getStatus());
        assertNotNull(response.getMessage());

        // Verify that hospitalRepo.save() was called
        verify(repo, times(1)).save(any(HospitalEntity.class));
    }

    @Test
    void createHospital_ExistingHospital_ShouldThrowHospitalAlreadyPresentException() {
        // Arrange
        HospitalRequest hospitalRequest = new HospitalRequest();
        hospitalRequest.setEmailId("hospital@example.com");
        when(repo.findByEmailId(hospitalRequest.getEmailId())).thenReturn(Optional.of(new HospitalEntity()));

        // Act and Assert
        assertThrows(HospitalAlreadyPresentException.class, () ->
                hospitalService.createHospital(hospitalRequest)
        );

        // Verify that hospitalRepo.save() was not called
        verify(repo, never()).save(any(HospitalEntity.class));
    }

    @Test
    void deleteHospitalByEmailId_ExistingHospital_Success() {
        String emailId = "hospital@example.com";
        HospitalEntity hospitalEntity = new HospitalEntity();
        when(repo.findByEmailId(emailId)).thenReturn(Optional.of(hospitalEntity));

        MessageResponse response = hospitalService.deleteHospitalByEmailId(emailId);

        assertEquals("SUCCESS", response.getStatus());
        assertEquals(String.format("Hospital '%s' deleted successfully", emailId), response.getMessage());
        verify(repo).deleteByEmailId(emailId);
    }

    @Test
    void deleteHospitalByEmailId_NonExistingHospital_ThrowsException() {
        String emailId = "nonexisting@example.com";
        when(repo.findByEmailId(emailId)).thenReturn(Optional.empty());

        assertThrows(NoSuchHospitalExistException.class, () -> hospitalService.deleteHospitalByEmailId(emailId));
    }

    @Test
    void isHospitalPresent_HospitalDoesNotExist_ReturnsFalse() {
        String emailId = "hospital@example.com";

        // Mocking the repository
        when(repo.findByEmailId(emailId)).thenReturn(Optional.empty());

        // Calling the method under test
        boolean result = hospitalService.isHospitalPresent(emailId);

        // Assertion
        assertFalse(result);
    }

}
