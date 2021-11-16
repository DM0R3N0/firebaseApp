package br.com.dannes.firebaseapp;

public class Produtos {
        String descricao;
        String marca;
        String modelo;
        int preco;

        public Produtos() {
        }

        public String getDescricao() {
                return descricao;
        }

        public void setDescricao(String descricao) {
                this.descricao = descricao;
        }

        public String getMarca() {
                return marca;
        }

        public void setMarca(String marca) {
                this.marca = marca;
        }

        public String getModelo() {
                return modelo;
        }

        public void setModelo(String modelo) {
                this.modelo = modelo;
        }

        public int getPreco() {
                return preco;
        }

        public void setPreco(int preco) {
                this.preco = preco;
        }
}
