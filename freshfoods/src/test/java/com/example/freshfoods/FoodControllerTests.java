package com.example.freshfoods;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql({"/test_data.sql"})
public class FoodControllerTests {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void test_findAll() throws Exception {
        this.mvc.perform(get("/api/v1/food"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body", hasSize(8)))
                .andExpect(jsonPath("$.body[0].name", notNullValue()))
                .andExpect(jsonPath("$.body[7].name", notNullValue()));
    }


    @Test
    public void test_findById_success() throws Exception {
        this.mvc.perform(get("/api/v1/food/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body.name", is("Beef Pizza")))
                .andExpect(jsonPath("$.body.category", is("Pizza")));
    }

    @Test
    public void test_findById_notFound() throws Exception {

        this.mvc.perform(get("/api/v1/food/10"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void test_findByCategory() throws Exception {
        this.mvc.perform(get("/api/v1/food/category?category=Pizza"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body", hasSize(3)))
                .andExpect(jsonPath("$.body[1]", notNullValue()));
    }

    @Test
    public void test_findByCategory_notFound() throws Exception {
        this.mvc.perform(get("/api/v1/food/category?category=Appetizers"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void test_getFoodCategories() throws Exception {
        this.mvc.perform(get("/api/v1/food/category/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body", hasSize(3)))
                .andExpect(jsonPath("$.body[0]", is("Pizza")))
                .andExpect(jsonPath("$.body[2]", is("Juice")));
    }

    @Test
    public void test_search() throws Exception {
        this.mvc.perform(get("/api/v1/food/search?category=Pizza&name="))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body", hasSize(3)));

        this.mvc.perform(get("/api/v1/food/search?category=Pizza&name=be"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body", hasSize(1)))
                .andExpect(jsonPath("$.body[0].name", startsWithIgnoringCase("be")))
                .andExpect(jsonPath("$.body[0].category", is("Pizza")));
    }
}
