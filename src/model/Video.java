package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Video {
    private String titulo;
    private String descricao;
    private int duracao; // em minutos
    private String categoria;
    private Date dataPublicacao;

    public Video(String titulo, String descricao, int duracao, String categoria, Date dataPublicacao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.duracao = duracao;
        this.categoria = categoria;
        this.dataPublicacao = dataPublicacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getDuracao() {
        return duracao;
    }

    public String getCategoria() {
        return categoria;
    }

    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return titulo + ";" + descricao + ";" + duracao + ";" + categoria + ";" + simpleDateFormat.format(dataPublicacao);
    }

    public static Video fromString(String linha) {
        try {
            String[] partes = linha.split(";");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return new Video(partes[0], partes[1], Integer.parseInt(partes[2]), partes[3], simpleDateFormat.parse(partes[4]));
        } catch (Exception e) {
            return null; // Ignora erros de parsing
        }
    }

    public void validarDadosDeInput (String date){
        if (duracao <= 0){
            throw new IllegalArgumentException("A duração do vídeo deve ser maior do que zero minutos.");
        } else if (titulo == null || titulo == " " || descricao == null || descricao == " ") {
            throw new IllegalArgumentException("O título e a descrição do vídeo devem ser preenchidos de forma válida.");
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat.setLenient(false);
        try {
            simpleDateFormat.parse(date);
        } catch (ParseException e) {
            System.out.println("Por favor, insira um formato válido de data dd/MM/yyyy.");
        }
    }
}