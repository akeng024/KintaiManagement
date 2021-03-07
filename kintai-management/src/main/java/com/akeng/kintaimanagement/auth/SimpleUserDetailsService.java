package com.akeng.kintaimanagement.auth;


import com.akeng.kintaimanagement.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class SimpleUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        assert(email != null);
        log.debug("loadUserByUsername(email):[{}]", email);
        return userRepository.findByEmail(email)
                .map(SimpleLoginUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found by email:[" + email + "]"));
    }
}
