// package com.listadecompras.listacompras;

// import com.listadecompras.listacompras.controller.LoginController;
// import com.listadecompras.listacompras.entity.User;
// import com.listadecompras.listacompras.repository.UserRepository;
// import com.listadecompras.listacompras.service.TokenService;
// import org.springframework.boot.test.context.SpringBootTest;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.test.context.ActiveProfiles;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;

// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.ArgumentMatchers.anyString;
// import static org.mockito.Mockito.mock;
// import static org.mockito.Mockito.when;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @SpringBootTest
// @ActiveProfiles("test")
// public class LoginControllerTest {

//     private MockMvc mockMvc;

//     @Mock
//     private AuthenticationManager authenticationManager;

//     @Mock
//     private TokenService tokenService;

//     @Mock
//     private UserRepository userRepository;

//     @InjectMocks
//     private LoginController loginController;

//     @BeforeEach
//     void setUp() {
//         mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
//     }

//     @Test
//     void testRegisterUserAlreadyExists() throws Exception {
//         when(userRepository.findByEmail(anyString())).thenReturn(new User());

//         mockMvc.perform(post("/auth/register")
//                         .contentType("application/json")
//                         .content("{\"username\":\"jorgetenison\",\"email\":\"jorgetenison@gmail.com\",\"password\":\"password123\",\"role\":\"USER\"}"))
//                 .andExpect(status().isBadRequest());
//     }

//     @Test
//     void testRegisterUserSuccess() throws Exception {
//         when(userRepository.findByEmail(anyString())).thenReturn(null);
//         mockMvc.perform(post("/auth/register")
//                         .contentType("application/json")
//                         .content("{\"username\":\"jorgetenison\",\"email\":\"jorgetenison@gmail.com\",\"password\":\"password123\",\"role\":\"USER\"}"))
//                 .andExpect(status().isOk());
//     }

//     @Test
//     void testLoginSuccess() throws Exception {
//         Authentication authentication = mock(Authentication.class);

//         when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
//                 .thenReturn(authentication);
//         User user = new User();

//         user.setEmail("test@example.com");

//         when(authentication.getPrincipal()).thenReturn(user);
//         when(tokenService.generateToken(any(User.class))).thenReturn("dummy-token");

//         mockMvc.perform(post("/auth/login")
//                         .contentType("application/json")
//                         .content("{\"email\":\"test@example.com\",\"password\":\"password123\"}"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.token").value("dummy-token"));
//     }

//     @Test
//     void testLoginFailure() throws Exception {
//         when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
//                 .thenThrow(new RuntimeException("CREDENCIAIS INVALIDAS"));

//         mockMvc.perform(post("/auth/login")
//                         .contentType("application/json")
//                         .content("{\"email\":\"test@example.com\",\"password\":\"wrong-password\"}"))
//                 .andExpect(status().isUnauthorized())
//                 .andExpect(content().string("CREDENCIAIS INVALIDAS"));
//     }

// }

