package com.projeto.seguros.pojos;

/**
 * POJO para modelagem de dados de usuário
 * Utilizado para mapper dados do Excel
 */
public class Usuario {

    private String email;
    private String senha;
    private String nome;
    private String perfil;

    // Constructors
    public Usuario() {
    }

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Usuario(String email, String senha, String nome, String perfil) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.perfil = perfil;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    // Builder
    public static class Builder {
        private String email;
        private String senha;
        private String nome;
        private String perfil;

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder senha(String senha) {
            this.senha = senha;
            return this;
        }

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder perfil(String perfil) {
            this.perfil = perfil;
            return this;
        }

        public Usuario build() {
            return new Usuario(email, senha, nome, perfil);
        }
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", perfil='" + perfil + '\'' +
                '}';
    }
}
