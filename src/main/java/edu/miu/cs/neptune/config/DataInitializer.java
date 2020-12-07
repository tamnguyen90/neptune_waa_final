package edu.miu.cs.neptune.config;

import edu.miu.cs.neptune.domain.*;
import edu.miu.cs.neptune.service.CategoryService;
import edu.miu.cs.neptune.service.ProductService;
import edu.miu.cs.neptune.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

        private final UserService userService;
        private final CategoryService categoryService;
//        private final ProductService productService;

    public DataInitializer(UserService userService, CategoryService categoryService) {
        this.userService = userService;
        this.categoryService = categoryService;
//        this.productService = productService;
    }

    private void loadUsers() {
        Address add1 = new Address(111L, "10 N Court", "FairField", "IA", "52556");
        Address add2 = new Address(222L, "11 N Court", "FairField", "IA", "52556");
        Address add3 = new Address(333L, "12 N Court", "FairField", "IA", "52556");
        Address add4 = new Address(444L, "15 N Court", "FairField", "IA", "52556");

//        User user1 = new User(111L, "hale", "dthle@miu.edu", "123", "Ha", "Le", "123-456-789",ProfileVerificationType.VERIFIED , add1);
//        User user2 = new User(222L, "test1", "test1@gmail.com", "123", "Test1", "Test1", "444-111-889",ProfileVerificationType.VERIFIED , add2);
//        User user3 = new User(333L, "test1", "test1@gmail.com", "123", "Test1", "Test1", "333-222-111",ProfileVerificationType.NEED_TO_VERIFY , add3);
//        User user4 = new User(444L, "test1", "test1@gmail.com", "123", "Test1", "Test1", "555-121-212",ProfileVerificationType.VERIFIED , add4);
//        List<User> user = Arrays.asList(user1, user2, user3, user4);
//        Role role1 = new Role(RoleCode.ADMIN,"Admin");
//        Role role2 = new Role(RoleCode.BUYER,"Buyer");
//        Role role3 = new Role(RoleCode.SELLER,"Seller");
//
//
//        User user1 = new User(111L, "hale", "dthle@miu.edu", "123", "Ha", "Le", "123-456-789",ProfileVerificationType.VERIFIED , Boolean.FALSE,add1);
//        User user2 = new User(222L, "test1", "test1@gmail.com", "123", "Test1", "Test1", "444-111-889",ProfileVerificationType.VERIFIED , Boolean.FALSE,add2);
//        User user3 = new User(333L, "test1", "test1@gmail.com", "123", "Test1", "Test1", "333-222-111",ProfileVerificationType.NEED_TO_VERIFY , Boolean.FALSE,add3);
//        User user4 = new User(444L, "test1", "test1@gmail.com", "123", "Test1", "Test1", "555-121-212",ProfileVerificationType.VERIFIED , Boolean.FALSE,add4);
//        user1.addRole(role1);
//        user2.addRole(role2);
//        user3.addRole(role3);
//        user4.addRole(role2);
//
//        List<User> user = Arrays.asList(user1, user2, user3, user4);

    }



    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadUsers();
    }

}
