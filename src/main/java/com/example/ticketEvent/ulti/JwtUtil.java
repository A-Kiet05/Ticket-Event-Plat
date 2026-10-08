package com.example.ticketEvent.ulti;

public static class JwtUtil {
    // Implement JWT utility methods here
    private JwtUtil(){

    }
    
    public static UUID parseUserId(Jwt jwt){
        
        return UUID.fromString(jwt.getSubject());
    }
}