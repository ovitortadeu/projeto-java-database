package br.com.CarroSeguro.model.dto;


import java.util.*;

public class Problemas {

    private int idProblema;
    private int vl_problema;
    private String nmProblema;
    private String tpPecaProblema;
    private String dcProblema;
    private int idCarro;

    public Problemas() {
    }

    public Problemas(int idProblema, int vl_problema, String nmProblema, String tpPecaProblema, String dcProblema, int idCarro) {
        this.idProblema = idProblema;
        this.vl_problema = vl_problema;
        this.nmProblema = nmProblema;
        this.tpPecaProblema = tpPecaProblema;
        this.dcProblema = dcProblema;
        this.idCarro = idCarro;
    }

    public int getIdProblema() {
        return idProblema;
    }

    public void setIdProblema(int idProblema) {
        this.idProblema = idProblema;
    }

    public int getVl_problema() {
        return vl_problema;
    }

    public void setVl_problema(int vl_problema) {
        this.vl_problema = vl_problema;
    }

    public String getNmProblema() {
        return nmProblema;
    }

    public void setNmProblema(String nmProblema) {
        this.nmProblema = nmProblema;
    }

    public String getTpPecaProblema() {
        return tpPecaProblema;
    }

    public void setTpPecaProblema(String tpPecaProblema) {
        this.tpPecaProblema = tpPecaProblema;
    }

    public String getDcProblema() {
        return dcProblema;
    }

    public void setDcProblema(String dcProblema) {
        this.dcProblema = dcProblema;
    }

    public int getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(int idCarro) {
        this.idCarro = idCarro;
    }

    public ArrayList<String> gerarProblemaPeca() {
        ArrayList<String> pecas = new ArrayList<>();
        pecas.add("Bateria");
        pecas.add("Velas de ignição");
        pecas.add("Freios");
        pecas.add("Embreagem");
        pecas.add("Suspensão");

        return pecas;
    }

    public double gerarProblemaValor(String peca) {

        double resultado = 0;
        if (tpPecaProblema.equalsIgnoreCase("Suspensão")) {
            Random r = new Random();

            double randomDouble = r.nextDouble();
            double min = 5000;
            double max = 7000;

            double result = min + (max - min) * randomDouble;
            resultado = Math.round(result * 100.0) / 100.0;
        } else if (tpPecaProblema.equalsIgnoreCase("Bateria")) {
            Random r = new Random();

            double randomDouble = r.nextDouble();
            double min = 1500;
            double max = 2800;

            double result = min + (max - min) * randomDouble;
            resultado = Math.round(result * 100.0) / 100.0;
        } else if (tpPecaProblema.equalsIgnoreCase("Embreagem")) {
            Random r = new Random();

            double randomDouble = r.nextDouble();
            double min = 4000;
            double max = 6000;

            double result = min + (max - min) * randomDouble;
            resultado = Math.round(result * 100.0) / 100.0;
        } else if (tpPecaProblema.equalsIgnoreCase("Freios")) {
            Random r = new Random();

            double randomDouble = r.nextDouble();
            double min = 2500;
            double max = 4000;

            double result = min + (max - min) * randomDouble;
            resultado = Math.round(result * 100.0) / 100.0;
        } else if (tpPecaProblema.equalsIgnoreCase("Velas de ignição")) {
            Random r = new Random();

            double randomDouble = r.nextDouble();
            double min = 500;
            double max = 2000;

            double result = min + (max - min) * randomDouble;
            resultado = Math.round(result * 100.0) / 100.0;
        }
        return resultado;
    }


    public String problemaNome(String tpPecaProblema, String peca) {
        String nome = "";

        if (tpPecaProblema.equalsIgnoreCase("Embreagem")) {
            if (peca.equalsIgnoreCase("Desgaste do Disco de Embreagem")) {
                nome = "Desgaste do Disco";
            } else if (peca.equalsIgnoreCase("Problemas no Cabo ou Sistema Hidráulico")) {
                nome = "Cabo/Sist. Falhos";
            } else if (peca.equalsIgnoreCase("Patins da Embreagem Gastos")) {
                nome = "Patins Gastos";
            }
        } else if (tpPecaProblema.equalsIgnoreCase("Freios (pastilhas e discos)")) {
            if (peca.equalsIgnoreCase("Desgaste das Pastilhas")) {
                nome = "Desgaste da Pastilha";
            } else if (peca.equalsIgnoreCase("Deformação dos Discos")) {
                nome = "Deformação dos Disco";
            } else if (peca.equalsIgnoreCase("Fugas de Fluido de Freio")) {
                nome = "Fuga Fluido de Freio";
            }
        } else if (tpPecaProblema.equalsIgnoreCase("Velas de ignição")) {
            if (peca.equalsIgnoreCase("Desgaste do Eletrodo")) {
                nome = "Desgaste do Eletrodo";
            } else if (peca.equalsIgnoreCase("Acúmulo de Carbono")) {
                nome = "Acúmulo de Carbono";
            } else if (peca.equalsIgnoreCase("Falha no Isolador")) {
                nome = "Falha no Isolador";
            }
        } else if (tpPecaProblema.equalsIgnoreCase("Bateria")) {
            if (peca.equalsIgnoreCase("Descarga Completa")) {
                nome = "Descarga Completa";
            } else if (peca.equalsIgnoreCase("Corrosão dos Terminais")) {
                nome = "Corrosão no Terminal";
            } else if (peca.equalsIgnoreCase("Danos Físicos")) {
                nome = "Danos Físicos";
            }
        } else if (tpPecaProblema.equalsIgnoreCase("Suspensão (amortecedores e molas)")) {
            if (peca.equalsIgnoreCase("Amortecedores Desgastados")) {
                nome = "Amort. Desgastado";
            } else if (peca.equalsIgnoreCase("Molas Quebradas")) {
                nome = "Molas Quebradas";
            } else if (peca.equalsIgnoreCase("Vazamento de Fluido")) {
                nome = "Vazamento de Fluido";
            }
        }
        return nome;
    }

    public String problemaDescricao(String problemaNome) {

        String desc = "";

        switch (problemaNome) {
            case "Desgaste do Disco de Embreagem" -> {
                return desc = "Troca urgente do disco.";
            }
            case "Problemas no Cabo ou Sistema Hidráulico" -> {
                return desc = "Cabo ou sistema com falha.";
            }
            case "Patins da Embreagem Gastos" -> {
                return desc = "Patins precisam de troca.";
            }
            case "Desgaste das Pastilhas" -> {
                return desc = "Pastilhas de freio gastas.";
            }
            case "Deformação dos Discos" -> {
                return desc = "Discos estão deformados.";
            }
            case "Fugas de Fluido de Freio" -> {
                return desc = "Vazamento de fluido detectado.";
            }
            case "Desgaste do Eletrodo" -> {
                return desc = "Eletrodo precisa ser trocado.";
            }
            case "Acúmulo de Carbono" -> {
                return desc = "Limpeza de carbono necessária.";
            }
            case "Falha no Isolador" -> {
                return desc = "Isolador da vela com falha.";
            }
            case "Descarga Completa" -> {
                return desc = "Bateria descarregada.";
            }
            case "Corrosão dos Terminais" -> {
                return desc = "Terminais corroídos.";
            }
            case "Danos Físicos" -> {
                return desc = "Bateria danificada.";
            }
            case "Amortecedores Desgastados" -> {
                return desc = "Amortecedores sem ação.";
            }
            case "Molas Quebradas" -> {
                return desc = "Molas quebradas.";
            }
            case "Vazamento de Fluido" -> {
                return desc = "Amortecedor com vazamento.";
            }
            default -> {
                return desc = "Problema desconhecido.";
            }
        }
        }
    }

