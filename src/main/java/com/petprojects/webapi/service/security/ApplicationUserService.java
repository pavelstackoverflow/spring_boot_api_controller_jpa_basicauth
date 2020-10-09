package com.petprojects.webapi.service.security;

import com.petprojects.webapi.entity.security.ApplicationUser;
import com.petprojects.webapi.entity.security.ApplicationUserPermission;
import com.petprojects.webapi.repository.security.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class ApplicationUserService implements UserDetailsService {


    @PersistenceContext
    private EntityManager entityManager;


    @Autowired
    ApplicationUserRepository applicationUserRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ( username == null || username.isEmpty() ){
            throw new UsernameNotFoundException("username is empty");
        }
        ApplicationUser applicationUser = getUserByUsername(username);
        if (applicationUser != null) {
            Set<GrantedAuthority> grantedAuthorityHashSet=new HashSet<>();
            for (ApplicationUserPermission permission : applicationUser.getPermissions()) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getPermission());
                grantedAuthorityHashSet.add(grantedAuthority);
            }
            return new User(applicationUser.getUsername(), applicationUser.getPassword(), grantedAuthorityHashSet);
        } else {
            throw new UsernameNotFoundException("username not exist");
        }
    }


    private ApplicationUser getUserByUsername(String username) {
        ApplicationUser user = (ApplicationUser) entityManager.createQuery("SELECT u from ApplicationUser u " +
                "LEFT JOIN FETCH u.permissions p " +
                "WHERE u.username=:username").setParameter("username", username).getSingleResult();
        return user;
    }

    public void addUser(ApplicationUser applicationUser) {
        applicationUser.setPassword(bCryptPasswordEncoder.encode(applicationUser.getPassword()));
        applicationUserRepository.saveAndFlush(applicationUser);
    }

    public void deleteUser(Long userId) {
        applicationUserRepository.deleteById(userId);
    }

}
