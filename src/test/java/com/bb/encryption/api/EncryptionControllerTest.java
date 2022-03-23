package com.bb.encryption.api;

import com.bb.encryption.dto.req.EncryptAesReqDto;
import com.bb.encryption.service.EncryptService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EncryptionController.class)
class EncryptionControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  ObjectMapper objectMapper;
  @MockBean
  private EncryptService service;

  @Test
  public void encryptAesCase01() throws Exception {
    // given
    final String expectedText = "안녕하세요 반가워요 잘있어요 다시";
    final String secretKey = "bbubbush!@#$%^&*";
    EncryptAesReqDto param = EncryptAesReqDto
      .builder()
      .planeText(expectedText)
      .secretKey(secretKey)
      .build();
    final String encodingText = "+7LQejMEkauHPHRrZQ9uKA==";

    // when
    when(service.encodeAes(any(EncryptAesReqDto.class))).thenReturn(encodingText);

    // then
    this.mockMvc
      .perform(post("/api/aes/enc")
            .content(objectMapper.writeValueAsString(param))
            .contentType(MediaType.APPLICATION_JSON))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().string(containsString(encodingText)))
    ;
  }
}