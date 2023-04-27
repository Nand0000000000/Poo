package br.gov.cesarschool.poo.fidelidade.util;

public class ValidadorCPF {

    private ValidadorCPF() {}

    public static boolean ehCpfValido(String cpf) {
        CpfMediator cpfMediator = new CpfMediator();

        return cpfMediator.validarCPF(cpf);
    }
}

class CpfMediator {

    public boolean validarCPF(String cpf) {
        if (cpf == null ||  cpf.length() != 11) {
            return false;
        }

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (cpf.charAt(i) - '0') * (10 - i);
        }
        int digito1 = 11 - (soma % 11);
        if (digito1 > 9) {
            digito1 = 0;
        }

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += (cpf.charAt(i) - '0') * (11 - i);
        }
        int digito2 = 11 - (soma % 11);
        if (digito2 > 9) {
            digito2 = 0;
        }

        if (cpf.charAt(9) - '0' != digito1 || cpf.charAt(10) - '0' != digito2) {
            return false;
        }

        return true;
    }
}
