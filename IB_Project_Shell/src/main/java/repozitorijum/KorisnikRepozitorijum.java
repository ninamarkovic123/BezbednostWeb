package repozitorijum;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Korisnik;

public interface KorisnikRepozitorijum extends JpaRepository<Korisnik, Long> {

    Korisnik findByEmail( String email );
    List<Korisnik> findByActive(Boolean active);
}
