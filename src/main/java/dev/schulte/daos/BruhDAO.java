package dev.schulte.daos;

import dev.schulte.entities.Bruh;

import java.util.List;

public interface BruhDAO {

    Bruh createBruh(Bruh bruh);

    Bruh getBruhByUserId(String userId);

    List<Bruh> getAllBruhs();

    Bruh updateBruh(Bruh bruh);
}
