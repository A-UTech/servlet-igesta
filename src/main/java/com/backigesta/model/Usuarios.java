package com.backigesta.model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

//Classe criada por Artur ;)
//A Classe Abstrata Usuarios, é a base para as outras classes de contas, contendo métodos
// e atributos básicos para a abstração de uma pessoa.
public abstract class Usuarios {
//=======================ATRIBUTOS=======================\\

    private int id;
    private String nome;
    private String email;
    private String senha;
    private byte[] foto;

//=======================MÉTODOS DA CLASSE=======================\\

    //Criar Arquivo de Imagem
    //Esta função é responsável por gerar o arquivo de foto, na extenção de JPEG
    //em um diretório dado apartir do parâmetro.
    //Ela traduz vetor de Bytes do atributo "Foto" para uma BufferedImage.
    public boolean gerarArquivoFoto(String caminhoSaida){
        if(foto.length!=0) {
            ByteArrayInputStream bais = new ByteArrayInputStream(foto);
            try {
                BufferedImage buffy = ImageIO.read(bais);
                File output = new File(caminhoSaida+"/"+id+".png");
                FileOutputStream fos = new FileOutputStream(output);
                ImageIO.write(buffy, "png", fos);
                fos.close();
                return true;
            }
            catch(IOException ioe){
                System.out.println("Exceção IO encontrada ao chamar 'gerarArquivoFoto()'.");
                ioe.printStackTrace();
                return false;
            }
        }
        return false;
    }

    //Enviar Arquivo de Imagem
    //Essa função é responsável por receber o caminho de uma imagem,
    //e traduzir essa imagem para um Bytea,
    public boolean setFoto(String caminhoEntrada) {
        File img = new File(caminhoEntrada);
        try (FileInputStream fis = new FileInputStream(img)) {
            int tamanhoBytes = fis.available();
            foto = new byte[tamanhoBytes];
            for (int i = 0; i < tamanhoBytes; i++) {
                foto[i] = (byte) fis.read();
            }
            return true;
        } catch (FileNotFoundException fnfe) {
            System.out.println("O Arquivo de Imagem não foi encontrado.");
            fnfe.printStackTrace();
            return false;
        } catch (IOException ioe) {
            System.out.println("Houve problemas ao ler o arquivo de Imagem.");
            ioe.printStackTrace();
            return false;
        }
    }

//=======================CONSTRUTORES=======================\\

    //Vazio
    public Usuarios() {
    }

    //Completo
    public Usuarios(int id, String nome, String email, String senha, byte[] foto) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.foto = foto;
    }

    public Usuarios(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    //Sem foto
    public Usuarios(int id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    //Sem Id
    public Usuarios(String nome, String email, String senha, byte[] foto) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.foto = foto;
    }

    //Sem ID nem Foto
    public Usuarios(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Usuarios(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    //=======================METODOS GET=======================\\


    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public byte[] getFoto() {
        return foto;
    }

//=======================METODOS SET=======================\\

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

//=======================TO STRING=======================\\

    public String toString() {
        return "Usuario{ " +
                "id: " +id+
                " | nome: "+nome+
                " | email: "+email+
                " | senha: "+senha+
                " | tem foto: " + (foto.length!=0 ? "sim" : "nao") +
                " }";
    }
    public String fotoToHex(){
        String hex = "";
        for(int i = 0; i<foto.length; i++){
            hex+=Integer.toHexString(foto[i]);
        }
        return hex;
    }
}