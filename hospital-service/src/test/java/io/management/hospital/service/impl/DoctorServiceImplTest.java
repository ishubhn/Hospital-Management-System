package io.management.hospital.service.impl;

import io.management.hospital.entities.DoctorEntity;
import io.management.hospital.entities.dto.response.DoctorResponse;
import io.management.hospital.exception.NoSuchDoctorEntityException;
import io.management.hospital.external.dto.Ratings;
import io.management.hospital.external.services.DoctorRatingService;
import io.management.hospital.repositories.DoctorEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceImplTest {
    @Mock
    private DoctorEntityRepository repo;

    @Mock
    private DoctorRatingService ratingService;

    @InjectMocks
    private DoctorServiceImpl doctorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllDoctors_ShouldReturnDoctorResponseList() {
        // Mock the repo response
        Map<String, String> eduMap = new HashMap<>();
        eduMap.put("abc", "123");
        eduMap.put("xyz", "456");

        Set<String> hospitals = new HashSet<>();
        hospitals.add("hospital 1");
        hospitals.add("hospital 2");

        DoctorEntity doctor1 = new DoctorEntity("1", "John", "Doe",
                "example@email.com", "1234567891", eduMap, hospitals);
        DoctorEntity doctor2 = new DoctorEntity("2", "Jane", "Smith",
                "example@email.com", "1239577891", eduMap, hospitals);
        List<DoctorEntity> mockDoctors = Arrays.asList(doctor1, doctor2);

        when(repo.findAll()).thenReturn(mockDoctors);

        // Mock the service response
        when(ratingService.getAllRatingsForDoctor("1")).thenReturn(createMockRatings("1"));
        when(ratingService.getAllRatingsForDoctor("2")).thenReturn(createMockRatings("2"));

        // Call the method
        List<DoctorResponse> result = doctorService.getAllDoctors();

        // verify that repo method was called
        verify(repo).findAll();

        // verify is service method was called for each doctor
        verify(ratingService).getAllRatingsForDoctor("1");
        verify(ratingService).getAllRatingsForDoctor("2");

        // Assertions
        assertEquals(2, result.size());

        DoctorResponse doctorResponse1 = result.get(0);
        assertEquals("1", doctorResponse1.getDoctorId());
        assertEquals("John", doctorResponse1.getFirstName());
        assertEquals(createMockRatings("1"), doctorResponse1.getRatings());

        DoctorResponse doctorResponse2 = result.get(1);
        assertEquals("2", doctorResponse2.getDoctorId());
        assertEquals("Jane", doctorResponse2.getFirstName());
        assertEquals(createMockRatings("2"), doctorResponse2.getRatings());
    }

    private List<Ratings> createMockRatings(String doctorId) {
        // Create and return mock ratings for the doctor
        // Here you can define your own test ratings
        Ratings ratings = new Ratings();
        ratings.setRatings(Integer.parseInt(doctorId) + 1);
        List<Ratings> list = new ArrayList<>();
        list.add(ratings);
        return new ArrayList<>();
    }

    @Test
    void getDoctorById_DoctorExist_ShouldReturnDoctorResponse() {
        DoctorEntity mockDoctor = new DoctorEntity("1", "John", "Doe",
                "example@email.com", "1234567891", null, null);
        when(repo.findById("1")).thenReturn(Optional.of(mockDoctor));

        // Mock the service response
        when(ratingService.getAllRatingsForDoctor("1")).thenReturn(createMockRatings("1"));

        // Call the method
        DoctorResponse doctorResponse = doctorService.getDoctorById("1");

        // Verify calls
        verify(repo).findById("1");
        verify(ratingService).getAllRatingsForDoctor("1");

        //Assertions
        assertEquals("1", doctorResponse.getDoctorId());
        assertEquals("John", doctorResponse.getFirstName());
        assertEquals(createMockRatings("1"), doctorResponse.getRatings());
    }

    @Test
    void getDoctorById_DoctorNotExist_ShouldThrowNoSuchDoctorEntityException() {
        when(repo.findById("2")).thenReturn(Optional.empty());

        // call the method and assert the exception
        assertThrows(NoSuchDoctorEntityException.class, () -> doctorService.getDoctorById("2"),
                "No doctor entity found with id -> 2");

        // verify the repo
        verify(repo).findById("2");
        verifyNoInteractions(ratingService);
    }
}
