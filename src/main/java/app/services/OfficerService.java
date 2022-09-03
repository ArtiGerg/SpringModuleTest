package app.services;

import app.models.Officer;
import app.repositories.OfficerRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
public class OfficerService implements UserDetailsService {



    @PersistenceContext
    private EntityManager entityManager;
    private final PasswordEncoder encoder;

    public OfficerService(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

        return entityManager.createQuery("SELECT u FROM Officer u WHERE u.username = :name", Officer.class)
                .setParameter("name", username)
                .getSingleResult();

    }


    @Transactional
    public void saveOfficer(Officer officer) {
       officer.setPassword(encoder.encode(officer.getPassword()));
       entityManager.persist(officer);



    }


}
