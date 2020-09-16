package servis;
import java.util.List;

import model.Autorizacija;

public interface AuthorityServisInterfejs {
	List<Autorizacija> findById(Long id);
	List<Autorizacija> findByname(String name);
}
