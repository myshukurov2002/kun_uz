package com.example.util;import lombok.experimental.UtilityClass;import java.security.MessageDigest;import java.security.NoSuchAlgorithmException;@UtilityClasspublic class MD5Util {    public String encode(String input) {        try {            MessageDigest md = MessageDigest.getInstance("MD5");            byte [] inputBytes = input.getBytes();            byte [] hashBytes = md.digest(inputBytes);                        StringBuilder builder = new StringBuilder();            for (byte b : hashBytes) {                builder.append(String.format("%02x", b));            }            return builder.toString();        } catch (NoSuchAlgorithmException e) {            throw new RuntimeException(e.getMessage());        }    }}