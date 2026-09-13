package com.sil.springsecurityapp.customSecutityConfig;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SecUserDetailService {
    private final Map<String,SecUserDetails>userDetailsMap = Map.of(
            "Abdullah",new SecUserDetails("Abdullah","123456", List.of("ROLE_USER")),
                   "ADMIN",new SecUserDetails("admin","1234",List.of("ROLE_ADMIN"))
    );

public  SecUserDetails loadUserByUsername(String username)
{
    return Optional.ofNullable(userDetailsMap.get(username)).orElseThrow(()->new UserNotFoundException(
            "User not found for username : "+username));
//    time 33min
}
}
