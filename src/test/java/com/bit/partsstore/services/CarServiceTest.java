package com.bit.partsstore.services;

import com.bit.partsstore.DTO.CarRequest;
import com.bit.partsstore.DTO.CarResponse;
import com.bit.partsstore.models.Brand;
import com.bit.partsstore.models.Car;
import com.bit.partsstore.models.Model;
import com.bit.partsstore.repositories.BrandRepository;
import com.bit.partsstore.repositories.CarRepository;
import com.bit.partsstore.repositories.ModelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {
    @Mock
    private CarRepository carRepository;
    @Mock
    private BrandRepository brandRepository;
    @Mock
    private ModelRepository modelRepository;
    @Mock
    private ImageStorageService imageStorageService;

    @InjectMocks
    private CarService carService;

    private Brand brand;
    private Brand brand2;
    private Model model;
    private Model model2;
    private Car car;
    private Car car2;
    private MockMultipartFile imageFile;
    private CarRequest carRequest; //Toyota

    @BeforeEach
    void setUp() {
        imageFile = new MockMultipartFile("image", "car.jpg", "image/jpeg", new byte[0]);

        brand = new Brand(1, "Toyota");
        model = new Model(1, "Supra");
        car = new Car(model, brand, "Fast car", 2020, LocalDate.now(), "uuid_car.jpg");
        car.setId(1);
        carRequest = new CarRequest("Fast car", 2020, 1, 1, imageFile);

        brand2 = new Brand(2, "Audi");
        model2 = new Model(2, "Q7");
        car2 = new Car(model2, brand2, "Comfort car", 2020, LocalDate.now(), "uuid_car2.jpg");
        car2.setId(2);
    }

    // ==============================
    //          GET CARS
    // ==============================
    @Test
    void getCars_ShouldReturnListOfCars() {
        Mockito.when(carRepository.findAll()).thenReturn(List.of(car, car2));

        List<CarResponse> responses = carService.getCars();

        assertEquals(2, responses.size());
        assertEquals("Toyota", responses.get(0).getBrandName());
        assertEquals("Supra", responses.get(0).getModelName());
        assertEquals("Audi", responses.get(1).getBrandName());
        assertEquals("Q7", responses.get(1).getModelName());

        verify(carRepository, Mockito.times(1)).findAll();
    }

    // ==============================
    //          ADD CAR
    // ==============================
    @Test
    void getCars_ShouldReturnEmptyList() {
        Mockito.when(carRepository.findAll()).thenReturn(List.of());

        List<CarResponse> responses = carService.getCars();

        assertEquals(0, responses.size());
        verify(carRepository, Mockito.times(1)).findAll();
    }

    @Test
    void addCar_ShouldReturnCarResponse() {
        //CarRequest request = new CarRequest("Fast car", 2020, 1, 1, imageFile);

        Mockito.when(brandRepository.findById(1)).thenReturn(Optional.of(brand));
        Mockito.when(modelRepository.findById(1)).thenReturn(Optional.of(model));

        Mockito.when(carRepository.save(any(Car.class))).thenAnswer(invocationOnMock -> {
            Car c = invocationOnMock.getArgument(0);
            c.setId(10);
            return c;
        });

        CarResponse response = carService.addCar(carRequest);

        assertNotNull(response);
        assertEquals("Fast car", response.getDescription());
        assertEquals("Toyota", response.getBrandName());
        assertEquals("Supra", response.getModelName());
        assertNotNull(response.getImageName());

        verify(brandRepository).findById(1);
        verify(modelRepository).findById(1);
        verify(carRepository).save(any(Car.class));
        verify(imageStorageService).saveCarImage(any(), anyString());
    }

    @Test
    void addCar_BrandNotFound() {
        when(brandRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> carService.addCar(carRequest));

        assertEquals("Failed to add car", exception.getMessage());
        assertTrue(exception.getCause().getMessage().contains("Brand not found"));
    }

    @Test
    void addCar_ModelNotFound() {
        Brand brand = new Brand(1, "Toyota");
        when(brandRepository.findById(1)).thenReturn(Optional.of(brand));
        when(modelRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> carService.addCar(carRequest));

        assertEquals("Failed to add car", exception.getMessage());
        assertTrue(exception.getCause().getMessage().contains("Model not found"));
    }
}
