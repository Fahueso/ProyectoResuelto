package com.miempresa.util;



import java.time.LocalDate;

import java.time.format.DateTimeFormatter;

import java.time.format.DateTimeParseException;

import java.util.Scanner;

import java.util.function.Predicate;



public final class InputUtils {

 private final Scanner sc;

 private final DateTimeFormatter dateFmt;



 public InputUtils(Scanner sc) {

 this.sc = sc;

 this.dateFmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");

 }



 public String readNonEmpty(String prompt) {

 while (true) {

 System.out.print(prompt + ": ");

 String s = sc.nextLine().trim();

 if (!s.isEmpty()) return s;

 System.out.println("⚠ Campo obligatorio, no puede estar vacío.");

 }

 }



 public String readOptional(String prompt) {

 System.out.print(prompt + " (ENTER para dejar vacío): ");

 return sc.nextLine().trim();

 }



 public Long readLong(String prompt, boolean required) {

 while (true) {

 System.out.print(prompt + (required ? " (número)" : " (número o ENTER)") + ": ");

 String s = sc.nextLine().trim();

 if (s.isEmpty() && !required) return null;

 try {

 return Long.parseLong(s);

 } catch (NumberFormatException e) {

 System.out.println("⚠ Introduce un número válido.");

 }

 }

 }



 public Integer readInt(String prompt, boolean required) {

 while (true) {

 System.out.print(prompt + (required ? " (número)" : " (número o ENTER)") + ": ");

 String s = sc.nextLine().trim();

 if (s.isEmpty() && !required) return null;

 try {

 return Integer.parseInt(s);

 } catch (NumberFormatException e) {

 System.out.println("⚠ Introduce un número válido.");

 }

 }

 }



 public boolean readBoolean(String prompt, boolean defaultValue) {

 while (true) {

 System.out.print(prompt + " [S/n] (ENTER=" + (defaultValue ? "Sí" : "No") + "): ");

 String s = sc.nextLine().trim().toLowerCase();

 if (s.isEmpty()) return defaultValue;

 if (s.equals("s") || s.equals("si") || s.equals("sí") || s.equals("y")) return true;

 if (s.equals("n") || s.equals("no")) return false;

 System.out.println("⚠ Responde S/N.");

 }

 }



 public LocalDate readDate(String prompt, boolean required) {

 while (true) {

 System.out.print(prompt + " (yyyy-MM-dd" + (required ? "" : " o ENTER") + "): ");

 String s = sc.nextLine().trim();

 if (s.isEmpty() && !required) return null;

 try {

 return LocalDate.parse(s, dateFmt);

 } catch (DateTimeParseException e) {

 System.out.println("⚠ Formato de fecha inválido, usa yyyy-MM-dd.");

 }

 }

 }



 public String readMatching(String prompt, Predicate<String> validator, String errorMsg, boolean required) {

 while (true) {

 System.out.print(prompt + (required ? "" : " (ENTER para omitir)") + ": ");

 String s = sc.nextLine().trim();

 if (s.isEmpty() && !required) return "";

 if (validator.test(s)) return s;

 System.out.println("⚠ " + errorMsg);

 }

 }

}