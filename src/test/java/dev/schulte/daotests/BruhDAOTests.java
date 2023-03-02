package dev.schulte.daotests;

import dev.schulte.daos.BruhDAO;
import dev.schulte.daos.BruhDAOPostgres;
import dev.schulte.entities.Bruh;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BruhDAOTests {

    static BruhDAO bruhDAO = new BruhDAOPostgres();

    @Test
    @Order(1)
    void create_user_test(){
        Bruh bruh = new Bruh("321691547529904138", 1000000, 0);
        Bruh savedBruh = bruhDAO.createBruh(bruh);
        Assertions.assertEquals("321691547529904138", savedBruh.getUserId());
    }

    @Test
    @Order(2)
    void get_bruh_by_id_test(){
        Bruh bruh = bruhDAO.getBruhByUserId("321691547529904138");
        Assertions.assertEquals(1000000, bruh.getBruhMoment());
    }
}
