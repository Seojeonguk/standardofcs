//package com.js.freeproject.domain.category;
//
//import com.js.freeproject.domain.category.application.CategoryService;
//import com.js.freeproject.domain.category.domain.Category;
//import com.js.freeproject.domain.category.domain.CategoryRepository;
//import com.js.freeproject.domain.user.domain.User;
//import com.js.freeproject.domain.user.domain.UserRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.swing.text.html.parser.Entity;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
//class CategoryServiceTest {
//    @Autowired
//    EntityManager em;
//
//    @Autowired
//    CategoryService categoryService;
//
//    @Autowired
//    CategoryRepository categoryRepository;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Test
//    @Rollback(false)
//    public void saveCategoryTest(){
//
//        Category category = new Category("개발자공통","개발자가 되고 싶은가");
//        em.persist(category);
//
//        Category category2 = new Category("백엔드공통","백엔드가 하고 싶은가");
//        em.persist(category2);
//
//        Category category3 = new Category("프론트 공통","프론트가 하고 싶은가");
//        em.persist(category3);
//
//        Category category4 = new Category("데이터베이스","DB는 알면알수록 어려워~!");
//        em.persist(category4);
//
//        em.flush();
//        em.clear();
//
//        List<Category> categories = categoryService.findCategorys();
//
//        for (Category category1: categories) {
//            System.out.println(category1.getName());
//        }
//    }
//}