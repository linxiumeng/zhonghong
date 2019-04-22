package org.springblade.information.controller;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;

public class BannerControllerTest extends BaseControllerTest {

    @Test
    public void getBanner() throws Exception{

        createBanner();

        JSONObject jsonObject = new JSONObject();

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/banner/getBanner")
                .content(jsonObject.toJSONString())
                .contentType(MediaType.APPLICATION_JSON_UTF8));

        resultActions.andDo(MockMvcResultHandlers.print());

        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.result[0].path").value("这是banner路径"));
    }
}