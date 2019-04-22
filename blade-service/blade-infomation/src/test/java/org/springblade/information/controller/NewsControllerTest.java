package org.springblade.information.controller;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;

public class NewsControllerTest extends BaseControllerTest {

    @Test
    public void getNewDetail() throws Exception{

        createNews();
        JSONObject jsonObject = new JSONObject();

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/news/detail")
                .content(jsonObject.toJSONString()).param("id","1")
                .contentType(MediaType.APPLICATION_JSON_UTF8));

        resultActions.andDo(MockMvcResultHandlers.print());

        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.row.title").value("这是新闻标题"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.row.editor").value("hhb"));
    }

    @Test
    public void listNewsByPage() throws Exception{
        createNews();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("page",1);
        jsonObject.put("size",10);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/news/list")
                .content(jsonObject.toJSONString())
                .contentType(MediaType.APPLICATION_JSON_UTF8));

        resultActions.andDo(MockMvcResultHandlers.print());

        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.result.records[0].title").value("这是新闻标题"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result.records[0].editor").value("hhb"));
    }
}