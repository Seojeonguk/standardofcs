//package com.js.freeproject.domain.problem;
//
//import com.js.freeproject.domain.answer.domain.Answer;
//import com.js.freeproject.domain.category.domain.Category;
//import com.js.freeproject.domain.category.domain.CategoryRepository;
//import com.js.freeproject.domain.problem.application.ProblemService;
//import com.js.freeproject.domain.problem.domain.Problem;
//import com.js.freeproject.domain.problem.domain.ProblemRepository;
//import com.js.freeproject.domain.problem.domain.ProblemStatus;
//import com.js.freeproject.domain.problempicture.domain.ProblemPicture;
//import com.js.freeproject.domain.problempicture.domain.ProblemPictureRepository;
//import com.js.freeproject.domain.user.domain.User;
//import com.js.freeproject.domain.user.domain.UserRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
//@Rollback(false)
//class ProblemServiceTest {
//
//    @Autowired
//    EntityManager em;
//
//    @Autowired
//    ProblemService problemService;
//
//    @Autowired
//    ProblemRepository problemRepository;
//
//    @Autowired
//    ProblemPictureRepository problemPictureRepository;
//
//    @Autowired
//    CategoryRepository categoryRepository;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Test
//    @Rollback(false)
//    public void deleteCategory(){
//        Category category = categoryRepository.findById(14L).get();
//        em.remove(category);
//        em.flush();
//        em.clear();
//    }
//
//    @Test
//    @Rollback(false)
//    void saveProblem() {
//        //Category category = new Category("?????????", "???????????? ????????????");
//        //em.persist(category);
//        Category category = categoryRepository.findById(3L).get();
//        //User user = new User("sgs1159@naver.com", "????????????", "?????????", "1234", "??????", "????????????????????????");
//        User user = userRepository.findByNickName("?????????")
//                .orElseThrow(() -> new IllegalArgumentException("?????????"));
//
////        em.persist(user);
//        ProblemPicture problemPicture = new ProblemPicture("?????????????????? url");
//
////        Answer answer1 = Answer.createAnswer("??????");
//        Answer answer2 = Answer.createAnswer("??????????????????");
//        Answer answer3 = Answer.createAnswer("??????");
//        List<Answer> list = new ArrayList<>();
//        //list.add(answer1);
//        list.add(answer2);
//        list.add(answer3);
//
//        Problem problem = Problem.createProblem("DTO??? ??????????????????.",
//                category, user, new ArrayList<>(), list);
//        System.out.println(problem);
//        em.persist(problem);
//        em.flush();
//        em.clear();
//
//
//        List<Problem> all = problemRepository.findAll();
//        for (Problem p : all) {
//            System.out.println(p);
//        }
//    }
//
//    @Test
//    void findAnswer(){
//        Problem problem = problemRepository.findById(1L).get();
//        for (Answer answer:problem.getAnswers()) {
//            System.out.println(answer.getWord());
//        }
//    }
//
//    @Test
//    @Rollback(false)
//    void updateProblem(){
//        Problem p = problemRepository.findById(133L).orElseThrow(IllegalArgumentException::new);
//        p.setStatus(ProblemStatus.revoke);
//        em.flush();
//        em.clear();
//    }
//
//    @Test
//    @Rollback(false)
//    void updateStatus(){
//        List<Problem> problems = problemRepository.findByStatus(ProblemStatus.wait);
//        for (Problem p:problems) {
//            p.setStatus(ProblemStatus.accept);
//        }
//        em.flush();
//        em.clear();
//    }
//
//}