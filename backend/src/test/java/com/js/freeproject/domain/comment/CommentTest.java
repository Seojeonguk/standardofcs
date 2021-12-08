//package com.js.freeproject.domain.comment;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.js.freeproject.domain.comment.application.CommentService;
//import com.js.freeproject.domain.comment.dto.CommentRequest;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class CommentTest {
//
//    @Autowired
//    MockMvc mvc;
//
//    @Autowired
//    private ObjectMapper OM;
//
//    @Autowired
//    CommentService commentService;
//
//    @Test
//    @Order(1)
//    @Rollback(false)
//    void saveComment() throws Exception {
//        CommentRequest cr = new CommentRequest("알았다 이새끼들아", 83L, 6L);
//        commentService.saveComment(cr, 55L);
//    }
//}
