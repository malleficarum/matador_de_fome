package com.example.matadordefome;

/**
 * Classe User: Representa um usuário no sistema.
 * Contém informações básicas do usuário, como nome, email, endereço e senha.
 */
public class User {

    // Atributos do usuário
    private String name;        // Nome do usuário
    private String email;       // Email do usuário
    private String address;     // Endereço do usuário
    private String password;    // Senha do usuário

    /**
     * Construtor da classe User.
     * Inicializa um novo objeto User com as informações fornecidas.
     *
     * @param name      Nome do usuário
     * @param email     Email do usuário
     * @param address   Endereço do usuário
     * @param password  Senha do usuário
     */
    public User(String name, String email, String address, String password) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.password = password;
    }

    // Métodos Getters e Setters

    /**
     * Obtém o nome do usuário.
     *
     * @return Nome do usuário
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome do usuário.
     *
     * @param name Nome do usuário
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtém o email do usuário.
     *
     * @return Email do usuário
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o email do usuário.
     *
     * @param email Email do usuário
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtém o endereço do usuário.
     *
     * @return Endereço do usuário
     */
    public String getAddress() {
        return address;
    }

    /**
     * Define o endereço do usuário.
     *
     * @param address Endereço do usuário
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Obtém a senha do usuário.
     *
     * @return Senha do usuário
     */
    public String getPassword() {
        return password;
    }

    /**
     * Define a senha do usuário.
     *
     * @param password Senha do usuário
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
