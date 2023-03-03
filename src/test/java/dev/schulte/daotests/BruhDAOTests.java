package dev.schulte.daotests;

import dev.schulte.daos.BruhDAO;
import dev.schulte.daos.BruhDAOPostgres;
import dev.schulte.entities.Bruh;
import org.junit.jupiter.api.*;

import java.util.List;

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

    @Test
    @Order(3)
    void get_all_bruhs_test(){
        List<Bruh> bruhList = bruhDAO.getAllBruhs();
        Assertions.assertNotEquals(0, bruhList.size());
    }

    @Test
    @Order(4)
    void update_bruh_test(){
        Bruh bruh1 = new Bruh("321691547529904138", 1500000, 1);
        Bruh updatedBruh = bruhDAO.updateBruh(bruh1);
        Assertions.assertEquals(1500000, updatedBruh.getBruhMoment());
    }
}
