package com.infosupport.mockito;

import com.infosupport.utjava.mockito.PasswordService;
import com.infosupport.utjava.mockito.User;
import com.infosupport.utjava.mockito.UserRepo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // activate when using @Mock and @InjectMocks
class PasswordServiceTest {

    @Mock
    private UserRepo mockedUserRepo;

    @InjectMocks
    private PasswordService target;

    // // Replaced by @Mock and @InjectMocks
    // @BeforeEach
    // void setUp() {
    //     // Mock is automatically reset for each test
    //     mockedUserRepo = mock(UserRepo.class);
    //
    //     sut = new PasswordService(mockedUserRepo);
    // }

    // test data
    int id = 1;
    String username = "bram";
    String oldPassword = "old";
    String newPassword = "new";
    User userOld = new User(id, username, oldPassword);
    User userNew = new User(id, username, newPassword);

    @BeforeAll
    static void beforeAll() {

    }

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }

    @AfterAll
    static void afterAll() {

    }

    @Test // basic setup and verify of mocks
    void changePassword_userBramAndOldPasswordCorrect_changesPassword() {
        // Arrange
        when(mockedUserRepo.find("bram")).thenReturn(userOld);
        when(mockedUserRepo.update("bram", "new")).thenReturn(userNew);

        // Act
        boolean changePassword = target.changePassword("bram", "old", "new");

        // Assert
        assertTrue(changePassword);
        verify(mockedUserRepo).find("bram");
        verify(mockedUserRepo).update("bram", "new");
    }

    @ParameterizedTest
    @ValueSource(strings = {"bram", "joshua", ""})
    void changePassword_userBramAndOldPasswordCorrect_changesPassword_PT(String username) {
        // Arrange
        when(mockedUserRepo.find(username)).thenReturn(userOld);
        when(mockedUserRepo.update(username, "new")).thenReturn(userNew);

        // Act
        boolean changePassword = target.changePassword(username, "old", "new");

        // Assert
        assertTrue(changePassword);
        verify(mockedUserRepo).find(username);
        verify(mockedUserRepo).update(username, "new");
    }

    @Test // don't care about exact values, use matchers
    void changePassword_userExistsAndOldPasswordCorrect_changesPasswordToo() {
        // Arrange
        when(mockedUserRepo.find(anyString())).thenReturn(userOld);
        when(mockedUserRepo.update(any(), eq("new"))).thenReturn(userNew);

        // Act
        boolean changePassword = target.changePassword("bram", "old", "new");

        // Assert
        assertTrue(changePassword);
        verify(mockedUserRepo).find(anyString());
        verify(mockedUserRepo).update(any(), eq(newPassword));
    }

    @Test // verify exact number of times
    void changePassword_userDoesntExist_doesntChangePassword() {
        // Arrange
        String usernameWrong = "brammmm";
        when(mockedUserRepo.find(usernameWrong)).thenReturn(null);

        // Act
        boolean changePassword = target.changePassword(usernameWrong, "old", "new");

        // Assert
        assertFalse(changePassword);
        verify(mockedUserRepo, times(1)).find(anyString());
        verify(mockedUserRepo, never()).update(any(), any());
    }

    @Test // mock an exception
    void changePassword_usernameIsNull_fails() {
        // Arrange
        when(mockedUserRepo.find(isNull())).thenThrow(IllegalArgumentException.class);

        // Act ............................................v
        // Assert
        assertThrows(IllegalArgumentException.class, () -> target.changePassword(null, oldPassword, newPassword));
        verify(mockedUserRepo, times(1)).find(null);
        verify(mockedUserRepo, never()).update(any(), any());
    }

    @Test // using argument captor(s)
    void changePassword_correctUsername_passesNewPasswordToUpdate() {
        // Arrange
        ArgumentCaptor<String> usernameCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> newPasswordCaptor = ArgumentCaptor.forClass(String.class);
        when(mockedUserRepo.find(username)).thenReturn(userOld);
        when(mockedUserRepo.update(usernameCaptor.capture(), newPasswordCaptor.capture())).thenReturn(userNew);

        // Act
        boolean changePassword = target.changePassword(username, oldPassword, newPassword);

        // Assert
        assertTrue(changePassword);
        assertEquals(username, usernameCaptor.getValue());
        assertEquals(newPassword, newPasswordCaptor.getValue());
    }
}
