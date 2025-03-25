package org.example.security;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedHashMap;

@AllArgsConstructor
public class UserData {
    public static Long UserId;
    public static ArrayList<LinkedHashMap<String, String>> roles;
}
