package com.nortic.pos.es.user.util;

import com.nortic.pos.es.user.grpc.gen.*;
import com.nortic.pos.models.dto.UserDTO;
import com.nortic.pos.models.enums.AccountTypeEnum;
import com.nortic.pos.models.enums.DocumentTypeEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GrpcMapperTest {

    private GrpcMapper grpcMapper;
    private UserDTO testUserDTO;
    private User testProtoUser;
    private UUID testUserId;

    @BeforeEach
    void setUp() {
        grpcMapper = Mappers.getMapper(GrpcMapper.class);
        testUserId = UUID.randomUUID();

        testUserDTO = UserDTO.builder()
                .id(testUserId)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .userName("johndoe")
                .password("password123")
                .documentType(DocumentTypeEnum.CC)
                .documentNumber("12345678")
                .phone("+1234567890")
                .address("123 Main St")
                .photo("/images/users/johndoe.jpg")
                .storeId(UUID.randomUUID())
                .branchId(UUID.randomUUID())
                .roleId(UUID.randomUUID())
                .accountType(AccountTypeEnum.EMPLOYEE)
                .isActive(true)
                .isPrincipalAdmin(false)
                .birthDate(LocalDate.of(1990, 1, 15))
                .createdAt(LocalDateTime.of(2023, 1, 15, 10, 30, 0))
                .updatedAt(LocalDateTime.of(2023, 1, 15, 11, 30, 0))
                .build();

        testProtoUser = User.newBuilder()
                .setId(testUserId.toString())
                .setFirstName("John")
                .setLastName("Doe")
                .setEmail("john.doe@example.com")
                .setUserName("johndoe")
                .setPassword("password123")
                .setDocumentType(DocumentType.CC)
                .setDocumentNumber("12345678")
                .setPhone("+1234567890")
                .setAddress("123 Main St")
                .setStoreId(testUserDTO.getStoreId().toString())
                .setBranchId(testUserDTO.getBranchId().toString())
                .setRoleId(testUserDTO.getRoleId().toString())
                .setAccountType(AccountType.EMPLOYEE)
                .setIsActive(true)
                .setIsPrincipalAdmin(false)
                .setBirthDate("1990-01-15")
                .setCreatedAt("2023-01-15T10:30:00")
                .setUpdatedAt("2023-01-15T11:30:00")
                .build();
    }

    @Test
    void protoUserToDTO_Success() {
        // When
        UserDTO result = grpcMapper.protoUserToDTO(testProtoUser);

        // Then
        assertNotNull(result);
        assertEquals(testUserId, result.getId());
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("john.doe@example.com", result.getEmail());
        assertEquals("johndoe", result.getUserName());
        assertEquals("password123", result.getPassword());
        assertEquals(DocumentTypeEnum.CC, result.getDocumentType());
        assertEquals("12345678", result.getDocumentNumber());
        assertEquals("+1234567890", result.getPhone());
        assertEquals("123 Main St", result.getAddress());
        assertEquals(AccountTypeEnum.EMPLOYEE, result.getAccountType());
        assertTrue(result.isActive());
        assertFalse(result.isPrincipalAdmin());
        assertEquals(LocalDate.of(1990, 1, 15), result.getBirthDate());
    }

    @Test
    void dtoToProtoUser_Success() {
        // When
        User result = grpcMapper.dtoToProtoUser(testUserDTO);

        // Then
        assertNotNull(result);
        assertEquals(testUserId.toString(), result.getId());
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("john.doe@example.com", result.getEmail());
        assertEquals("johndoe", result.getUserName());
        assertEquals("password123", result.getPassword());
        assertEquals(DocumentType.CC, result.getDocumentType());
        assertEquals("12345678", result.getDocumentNumber());
        assertEquals("+1234567890", result.getPhone());
        assertEquals("123 Main St", result.getAddress());
        assertEquals(AccountType.EMPLOYEE, result.getAccountType());
        assertFalse(result.getIsActive());
        assertFalse(result.getIsPrincipalAdmin());
        assertEquals("1990-01-15", result.getBirthDate());
    }

    @Test
    void getUsersRequestToPageable_WithAllParameters() {
        // Given
        GetUsersRequest request = GetUsersRequest.newBuilder()
                .setPage(2)
                .setSize(15)
                .setSort("firstName,desc")
                .build();

        // When
        Pageable result = grpcMapper.getUsersRequestToPageable(request);

        // Then
        assertNotNull(result);
        assertEquals(2, result.getPageNumber() + 1);
        assertEquals(15, result.getPageSize());
        assertEquals(Sort.by(Sort.Direction.DESC, "firstName"), result.getSort());
    }

    @Test
    void getUsersRequestToPageable_WithDefaults() {
        // Given
        GetUsersRequest request = GetUsersRequest.newBuilder().build();

        // When
        Pageable result = grpcMapper.getUsersRequestToPageable(request);

        // Then
        assertNotNull(result);
        assertEquals(0, result.getPageNumber());
        assertEquals(20, result.getPageSize());
        assertEquals(Sort.unsorted(), result.getSort());
    }

    @Test
    void getUsersRequestToPageable_WithAscendingSort() {
        // Given
        GetUsersRequest request = GetUsersRequest.newBuilder()
                .setSort("lastName,asc")
                .build();

        // When
        Pageable result = grpcMapper.getUsersRequestToPageable(request);

        // Then
        assertNotNull(result);
        assertEquals(Sort.by(Sort.Direction.ASC, "lastName"), result.getSort());
    }

    @Test
    void pageToUserListResponse_Success() {
        // Given
        Pageable pageable = PageRequest.of(0, 10);
        Page<UserDTO> userPage = new PageImpl<>(List.of(testUserDTO), pageable, 25);

        // When
        UserListResponse result = grpcMapper.pageToUserListResponse(userPage);

        // Then
        assertNotNull(result);
        assertEquals(25, result.getTotalElements());
        assertEquals(3, result.getTotalPages()); // 25 / 10 = 3 pages
        assertEquals(1, result.getCurrentPage() + 1);
        assertEquals(10, result.getPageSize());
        assertTrue(result.getHasNext());
        assertFalse(result.getHasPrevious());
        assertEquals(1, result.getUsersCount());

        User userInResponse = result.getUsers(0);
        assertEquals(testUserId.toString(), userInResponse.getId());
        assertEquals("John", userInResponse.getFirstName());
    }

    @Test
    void stringToUUID_ValidUUID() {
        // Given
        String validUUID = testUserId.toString();

        // When
        UUID result = grpcMapper.stringToUUID(validUUID);

        // Then
        assertEquals(testUserId, result);
    }

    @Test
    void stringToUUID_InvalidUUID() {
        // Given
        String invalidUUID = "invalid-uuid";

        // When
        UUID result = grpcMapper.stringToUUID(invalidUUID);

        // Then
        assertNull(result);
    }

    @Test
    void stringToUUID_NullOrEmpty() {
        // When & Then
        assertNull(grpcMapper.stringToUUID(null));
        assertNull(grpcMapper.stringToUUID(""));
    }

    @Test
    void uuidToString_ValidUUID() {
        // When
        String result = grpcMapper.uuidToString(testUserId);

        // Then
        assertEquals(testUserId.toString(), result);
    }

    @Test
    void uuidToString_NullUUID() {
        // When
        String result = grpcMapper.uuidToString(null);

        // Then
        assertEquals("", result);
    }

    @Test
    void protoDocumentTypeToEnum_AllTypes() {
        // Test all document types
        assertEquals(DocumentTypeEnum.CC, grpcMapper.protoDocumentTypeToEnum(DocumentType.CC));
        assertEquals(DocumentTypeEnum.TI, grpcMapper.protoDocumentTypeToEnum(DocumentType.TI));
        assertEquals(DocumentTypeEnum.PP, grpcMapper.protoDocumentTypeToEnum(DocumentType.PP));
        assertEquals(DocumentTypeEnum.CE, grpcMapper.protoDocumentTypeToEnum(DocumentType.CE));
        assertNull(grpcMapper.protoDocumentTypeToEnum(null));
    }

    @Test
    void enumToProtoDocumentType_AllTypes() {
        // Test all document types
        assertEquals(DocumentType.CC, grpcMapper.enumToProtoDocumentType(DocumentTypeEnum.CC));
        assertEquals(DocumentType.TI, grpcMapper.enumToProtoDocumentType(DocumentTypeEnum.TI));
        assertEquals(DocumentType.PP, grpcMapper.enumToProtoDocumentType(DocumentTypeEnum.PP));
        assertEquals(DocumentType.CE, grpcMapper.enumToProtoDocumentType(DocumentTypeEnum.CE));
        assertEquals(DocumentType.CC, grpcMapper.enumToProtoDocumentType(null)); // default
    }

    @Test
    void protoAccountTypeToEnum_AllTypes() {
        // Test all account types
        assertEquals(AccountTypeEnum.INTERNAL, grpcMapper.protoAccountTypeToEnum(AccountType.INTERNAL));
        assertEquals(AccountTypeEnum.STORE_OWNER, grpcMapper.protoAccountTypeToEnum(AccountType.STORE_OWNER));
        assertEquals(AccountTypeEnum.EMPLOYEE, grpcMapper.protoAccountTypeToEnum(AccountType.EMPLOYEE));
        assertNull(grpcMapper.protoAccountTypeToEnum(null));
    }

    @Test
    void enumToProtoAccountType_AllTypes() {
        // Test all account types
        assertEquals(AccountType.INTERNAL, grpcMapper.enumToProtoAccountType(AccountTypeEnum.INTERNAL));
        assertEquals(AccountType.STORE_OWNER, grpcMapper.enumToProtoAccountType(AccountTypeEnum.STORE_OWNER));
        assertEquals(AccountType.EMPLOYEE, grpcMapper.enumToProtoAccountType(AccountTypeEnum.EMPLOYEE));
        assertEquals(AccountType.EMPLOYEE, grpcMapper.enumToProtoAccountType(null)); // default
    }

    @Test
    void stringToLocalDate_ValidDate() {
        // Given
        String dateString = "1990-01-15";

        // When
        LocalDate result = grpcMapper.stringToLocalDate(dateString);

        // Then
        assertEquals(LocalDate.of(1990, 1, 15), result);
    }

    @Test
    void stringToLocalDate_InvalidDate() {
        // Given
        String invalidDate = "invalid-date";

        // When
        LocalDate result = grpcMapper.stringToLocalDate(invalidDate);

        // Then
        assertNull(result);
    }

    @Test
    void stringToLocalDate_NullOrEmpty() {
        // When & Then
        assertNull(grpcMapper.stringToLocalDate(null));
        assertNull(grpcMapper.stringToLocalDate(""));
    }

    @Test
    void localDateToString_ValidDate() {
        // Given
        LocalDate date = LocalDate.of(1990, 1, 15);

        // When
        String result = grpcMapper.localDateToString(date);

        // Then
        assertEquals("1990-01-15", result);
    }

    @Test
    void localDateToString_NullDate() {
        // When
        String result = grpcMapper.localDateToString(null);

        // Then
        assertEquals("", result);
    }

    @Test
    void stringToLocalDateTime_ValidDateTime() {
        // Given
        String dateTimeString = "2023-01-15T10:30:00";

        // When
        LocalDateTime result = grpcMapper.stringToLocalDateTime(dateTimeString);

        // Then
        assertEquals(LocalDateTime.of(2023, 1, 15, 10, 30, 0), result);
    }

    @Test
    void stringToLocalDateTime_InvalidDateTime() {
        // Given
        String invalidDateTime = "invalid-datetime";

        // When
        LocalDateTime result = grpcMapper.stringToLocalDateTime(invalidDateTime);

        // Then
        assertNull(result);
    }

    @Test
    void localDateTimeToString_ValidDateTime() {
        // Given
        LocalDateTime dateTime = LocalDateTime.of(2023, 1, 15, 10, 30, 0);

        // When
        String result = grpcMapper.localDateTimeToString(dateTime);

        // Then
        assertEquals("2023-01-15T10:30:00", result);
    }

    @Test
    void localDateTimeToString_NullDateTime() {
        // When
        String result = grpcMapper.localDateTimeToString(null);

        // Then
        assertEquals("", result);
    }
}
