package com.example.matadordefome;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe UserManager: Gerencia usuários do sistema.
 * Responsável pelo armazenamento, autenticação e recuperação de informações dos usuários.
 */
public class UserManager {

    // Atributos estáticos
    private static User loggedInUser; // Usuário atualmente autenticado
    private static List<User> userList = new ArrayList<>(); // Lista de todos os usuários cadastrados

    /**
     * Autentica um usuário com base no email e senha fornecidos.
     *
     * @param email    Email do usuário
     * @param password Senha do usuário
     * @return O objeto User autenticado, ou null se as credenciais não forem válidas
     */
    public static User authenticateUser(String email, String password) {
        // Percorre a lista de usuários para verificar as credenciais
        for (User user : userList) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                loggedInUser = user; // Define o usuário como autenticado
                return loggedInUser;
            }
        }
        // Retorna null caso não encontre um usuário com as credenciais fornecidas
        return null;
    }

    /**
     * Obtém o usuário atualmente autenticado no sistema.
     *
     * @return O objeto User autenticado, ou null se nenhum usuário estiver logado
     */
    public static User getLoggedInUser() {
        return loggedInUser;
    }

    /**
     * Adiciona um novo usuário à lista de usuários registrados.
     *
     * @param user O objeto User a ser adicionado
     */
    public static void addUser(User user) {
        userList.add(user);
    }
}
