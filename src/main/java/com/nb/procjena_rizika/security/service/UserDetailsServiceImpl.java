package com.nb.procjena_rizika.security.service;

import com.nb.procjena_rizika.model.korisnik.Korisnik;
import com.nb.procjena_rizika.repository.korisnik.KorisnikRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{

    private final KorisnikRepository korisnikRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Korisnik korisnik = korisnikRepository.findByUsername(username);
        if (korisnik == null){
            throw new UsernameNotFoundException(username);
        }
        return korisnik;
    }
}
