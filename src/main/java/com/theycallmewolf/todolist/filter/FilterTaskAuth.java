package com.theycallmewolf.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.theycallmewolf.todolist.users.IUserRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component // required for Spring to recognize this as a bean
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

            // Get path from request
            var path = request.getServletPath();

            // If path is `/tasks/create`
            if(path.equals("/tasks/create")) {
                // Get authentication (username and password) from request
                // - If authentication is not present, return 401 Unauthorized
                var auth = request.getHeader("Authorization");
                var authEnconded = auth.substring("Basic".length()).trim(); // removes `Basic ` from auth

                byte[] authDecode = Base64.getDecoder().decode(authEnconded);

                var authString = new String(authDecode); // username:password
                String[] credentials = authString.split(":"); // [username, password]
                String username = credentials[0];
                String password = credentials[1];

                // Validate user
                // - If user is valid, continue
                // - If user is not valid, return 401 Unauthorized
                var user = this.userRepository.findByUsername(username);
                if(user == null) {
                    response.sendError(401, "No user found");;
                    return;
                }

                // Validate password
                // - If password is valid, continue
                // - If password is not valid, return 401 Unauthorized
                var passwordCheck = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());

                if(passwordCheck.verified) {
                    // Continue
                    filterChain.doFilter(request, response);
                    return;
                };
                
                response.sendError(401, "wrong username or password");

            // If path is not `/tasks/create`
            } else {
                // Continue
                filterChain.doFilter(request, response);
            }
            

            
    }    
}
