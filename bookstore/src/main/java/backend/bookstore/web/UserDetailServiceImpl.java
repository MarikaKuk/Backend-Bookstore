package backend.bookstore.web;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import backend.bookstore.domain.AppUser;
import backend.bookstore.domain.AppUserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final AppUserRepository repository;

    public UserDetailServiceImpl(AppUserRepository appUserRepository) {
        this.repository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser currentuser = repository.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username,
                currentuser.getPasswordHash(),
                AuthorityUtils.createAuthorityList(currentuser.getRole()));
        return user;
    }

}
