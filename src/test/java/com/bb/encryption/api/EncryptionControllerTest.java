package com.bb.encryption.api;

import com.bb.encryption.service.EncryptService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasLength;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EncryptionController.class)
class EncryptionControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private EncryptService service;

  @Test
  public void encryptAesCase01() throws Exception {
    // given
    final String planeText = "bbubbush";
    final String encodingText = "+7LQejMEkauHPHRrZQ9uKA==";

    // when
    when(service.encodeAes(any())).thenReturn(encodingText);

    // then
    this.mockMvc
      .perform(get("/api/enc/aes").param("planeText", planeText))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().string(hasLength(encodingText.length())))
      .andExpect(content().string(containsString(encodingText)))
    ;
  }
}