package repositories;

import java.util.ArrayList;
import java.util.Arrays;

public class Validator {

    public static boolean validarTexto(String texto) {
        if (texto != null) {
            if (!texto.isEmpty()) {
                return texto.chars().allMatch(value -> Character.isWhitespace(value)||Character.isAlphabetic(value));
            } else {
                return false;
            }
        }
        return false;
    }

    public static boolean validarNumero(String texto) {
        if (texto != null) {
            if (!texto.isEmpty()) {
                return texto.chars().allMatch(Character::isDigit);
            } else {
                return false;
            }
        }
        return false;
    }

    public static boolean validarEmail(String texto) {
        if (texto != null) {
            if (!texto.isEmpty()) {
                return (texto.contains("@"));
            }else {
                return false;
            }
        }
        return false;
    }

    public static boolean validarTelefone(String texto){

        ArrayList<String> caracteresValidos = new ArrayList<>(
                Arrays.asList("0","1","2","3","4","5","6","7","8","9","(",")"," ","-")
        );
        if (texto != null) {
            if (!texto.isEmpty()) {
                return texto.chars().allMatch(e-> caracteresValidos.contains(Character.toString(e)));
            }else {
                return false;
            }
        }

        return false;
    }

}
