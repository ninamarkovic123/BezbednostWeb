package repozitorijum;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Autorizacija;

public interface AuthorityRepozitorijum  extends JpaRepository<Autorizacija, Long> {

	Autorizacija findByName(String name);
}
