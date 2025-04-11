package com.nortic.pos.es.store;

import com.nortic.pos.es.store.rest.controller.RestControllerImpl;
import com.nortic.pos.es.store.rest.gen.model.RestModelStore;
import com.nortic.pos.es.store.service.StoreSvcImpl;
import com.nortic.pos.es.store.util.RestMapper;
import com.nortic.pos.models.dto.StoreDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestControllerImpl.class)
class RestControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StoreSvcImpl storeSvc;

    @MockitoBean
    private RestMapper restMapper;

    @Test
    void testCreateStore() throws Exception {
        UUID id = UUID.randomUUID();

        String requestJson = """
        {
          "taxId": "123456789",
          "name": "Tienda Norte",
          "email": "tienda@norte.com",
          "phoneNumber": "123456789",
          "address": "Calle Falsa 123",
          "stateProvince": "Provincia",
          "city": "Ciudad",
          "isActive": true
        }
        """;

        StoreDTO storeDto = new StoreDTO();
        storeDto.setId(id);
        storeDto.setTaxId("123456789");
        storeDto.setName("Tienda Norte");
        storeDto.setEmail("tienda@norte.com");
        storeDto.setPhoneNumber("123456789");
        storeDto.setAddress("Calle Falsa 123");
        storeDto.setStateProvince("Provincia");
        storeDto.setCity("Ciudad");
        storeDto.setIsActive(true);

        RestModelStore restResponse = new RestModelStore();
        restResponse.setId(id);
        restResponse.setTaxId("123456789");
        restResponse.setName("Tienda Norte");
        restResponse.setEmail("tienda@norte.com");
        restResponse.setPhoneNumber("123456789");
        restResponse.setAddress("Calle Falsa 123");
        restResponse.setStateProvince("Provincia");
        restResponse.setCity("Ciudad");
        restResponse.setIsActive(true);

        when(restMapper.restModelToDTO(any(RestModelStore.class))).thenReturn(storeDto);
        when(storeSvc.createStore(any(StoreDTO.class))).thenReturn(storeDto);
        when(restMapper.dtoToRestModel(any(StoreDTO.class))).thenReturn(restResponse);

        mockMvc.perform(post("/api/v1/es/store")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Tienda Norte"))
                .andExpect(jsonPath("$.email").value("tienda@norte.com"))
                .andExpect(jsonPath("$.city").value("Ciudad"));
    }
}
