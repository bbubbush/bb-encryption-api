package com.bb.encryption.api;

import com.bb.encryption.service.DecryptService;
import com.bb.encryption.type.AesType;
import com.bb.encryption.vo.req.DecryptAesReqVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Import(HttpEncodingAutoConfiguration.class)
class DecryptionControllerTest {
  private final String ENCODING_TEXT = "Y+Qx8ykqfFrRlGzkRSfSDhA9TLrxV7gnBs2dgTNni/HXfuDgwFT5PYNbVUB939lDMjGrnXqscdsiyvsPjynOHg==";
  private final String SECRET_KEY = "bbubbush!@#$%^&*";
  @Autowired
  private WebApplicationContext webApplicationContext;
  @Autowired
  private ObjectMapper objectMapper;
  @MockBean
  private DecryptService service;

  private MockMvc mockMvc;

  @BeforeEach
  public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
      .addFilters(new CharacterEncodingFilter("UTF-8", true))
      .alwaysDo(print())
      .build();
  }

  @Test
  public void decryptAesCase01() throws Exception {
    // given
    DecryptAesReqVO param = DecryptAesReqVO
      .builder()
      .encodingText(this.ENCODING_TEXT)
      .secretKey(this.SECRET_KEY)
      .type(AesType.CBC)
      .build();
    final String decodingText = "안녕하세요 반가워요 잘있어요 다시 만나요";

    // when
    when(service.decodeAes(any(DecryptAesReqVO.class))).thenReturn(decodingText);

    // then
    this.mockMvc
      .perform(post("/api/dec/aes")
        .content(objectMapper.writeValueAsString(param))
        .contentType(MediaType.APPLICATION_JSON))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().string(containsString(decodingText)))
    ;
  }
}