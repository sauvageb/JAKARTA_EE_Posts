package com.example.demo.model;

import org.junit.jupiter.api.*;

class UserTest {

    @BeforeAll
    static void beforeAll() {
        // exécutée une seule fois avant le lancement de tous les tests de la classe
        System.out.println("@BeforeAll");
    }

    @BeforeEach
    void setUp() {
        // exécutée avant le lancement de chaque test de la classe
        System.out.println("@BeforeEach");
    }

    @AfterEach
    void tearDown() {
        // exécutée après le lancement de chaque test de la classe
        System.out.println("@AfterEach");
    }

    @AfterAll
    static void after() {
        // exécutée une seule fois après le lancement de tous les tests de la classe
        System.out.println("@AfterAll");
    }

    @Test
    void testExample() {
        int value = 100;
        Assertions.assertEquals(100, value);
    }

    // GIVEN - WHEN - THEN
    @Test
    void testConstructor() {
        // GIVEN
        Long id = 1L;
        String username = "sauvageb";
        String password = "qwerty";

        // WHEN
        User user = new User(id, username, password);

        // THEN
        Assertions.assertEquals(id, user.getId());
        Assertions.assertEquals(username, user.getUsername());
        Assertions.assertEquals(password, user.getPassword());
    }


}
