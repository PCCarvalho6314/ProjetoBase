package dataHelper;

public class DataHelper {

    public static String tipoSeguroTxt(String tipoSeguro) {
        if (tipoSeguro.toLowerCase().contains("novo")) {
            tipoSeguro = "Seguro Novo";
        }
        if (tipoSeguro.toLowerCase().contains("allianz")) {
            if (tipoSeguro.toLowerCase().contains("com")) {
                tipoSeguro = "Renovacao Allianz com sinistro";
            } else {
                tipoSeguro = "Renovacao Allianz sem sinistro";
            }
        } else if (tipoSeguro.toLowerCase().contains("renovação")) {
            if (tipoSeguro.toLowerCase().contains("com")) {
                tipoSeguro = "o de outra seguradora com sinistro";
            } else {
                tipoSeguro = "o de outra seguradora sem sinistro";
            }
        }

        return tipoSeguro;
    }

    public static String estadoCivilTxt(String estadoCivil) {
        if (estadoCivil.toLowerCase().contains("casad") ||
                estadoCivil.toLowerCase().contains("união")) {
            estadoCivil = "Casado[a] ou convive em união estável";
        }
        if (estadoCivil.toLowerCase().contains("solteir")) {
            estadoCivil = "Solteiro[a]";
        }
        if (estadoCivil.toLowerCase().contains("separad") ||
                estadoCivil.toLowerCase().contains("divorciad")) {
            estadoCivil = "Separado[a] / Divorciado[a]";
        }
        if (estadoCivil.toLowerCase().contains("viúv") ||
                estadoCivil.toLowerCase().contains("viuv")) {
            estadoCivil = "Viúvo[a]";
        }

        return estadoCivil;
    }

    public static String usoComercialTxt(String uso) {
        if (uso.toLowerCase().contains("táxi") ||
                uso.toLowerCase().contains("taxi")) {
            uso = "Táxi";
        }
        if (uso.toLowerCase().contains("aplicativo") ||
                uso.toLowerCase().contains("transp")) {
            uso = "Aplicativo de Transporte";
        }
        if (uso.toLowerCase().contains("locadora")) {
            uso = "Locadora";
        }
        if (uso.toLowerCase().contains("utiliza") ||
                uso.toLowerCase().contains("empresa")) {
            uso = "Utilização Empresarial";
        }

        return uso;
    }

    public static String residenciaTxt(String residencia) {
        if (residencia.equalsIgnoreCase("casa")) {
            residencia = "Casa";
        }
        if (residencia.toLowerCase().contains("cond") ||
                residencia.toLowerCase().contains("fechad")) {
            residencia = "Casa em condomínio fechado";
        }
        if (residencia.toLowerCase().contains("apart")) {
            residencia = "Apartamento";
        }

        return residencia;
    }

    public static String motoristaQueDirijeTxt(String motoristaPrincipal) {
        if (motoristaPrincipal.toLowerCase().contains("quem dirige")) {
            motoristaPrincipal = "Quem dirige o veículo segurado por mais de 85% do tempo semanal";
        }
        if (motoristaPrincipal.toLowerCase().contains("jovem")) {
            motoristaPrincipal = "Utilizado o mais jovem, pois não há quem dirija por mais de 85% do tempo semanal";
        }

        return motoristaPrincipal;
    }

    public static String seguroCargaTxt(String seguroCarga) {
        if (seguroCarga.toLowerCase().contains("sempre")) {
            seguroCarga = "Sempre possui seguro";
        }
        if (seguroCarga.toLowerCase().contains("eventual")) {
            seguroCarga = "Possui seguro eventualmente";
        }
        if (seguroCarga.toLowerCase().contains("nunca")) {
            seguroCarga = "Nunca possui seguro";
        }

        return seguroCarga;
    }

    public static String regiaoCirculacaoTxt(String regiaoCirculacao) {
        if (regiaoCirculacao.toLowerCase().contains("100")) {
            regiaoCirculacao = "Municípios e arredores até 100 km da cidade sede";
        }
        if (regiaoCirculacao.toLowerCase().contains("centro")) {
            regiaoCirculacao = "Região Centro-Oeste";
        }
        if (regiaoCirculacao.toLowerCase().contains("sul")) {
            regiaoCirculacao = "Região Sul";
        }
        if (regiaoCirculacao.toLowerCase().contains("norte")) {
            regiaoCirculacao = "Região Norte";
        }
        if (regiaoCirculacao.toLowerCase().contains("nordeste")) {
            regiaoCirculacao = "Região Nordeste";
        }
        if (regiaoCirculacao.toLowerCase().contains("sudeste")) {
            if (regiaoCirculacao.toLowerCase().contains("exceto")) {
                regiaoCirculacao = "Região Sudeste, exceto Estado de São Paulo";
            } else {
                regiaoCirculacao = "Região Sudeste, exceto Estado de São Paulo";
            }
        }
        if (regiaoCirculacao.toLowerCase().contains("mercosul")) {
            regiaoCirculacao = "Região Mercosul - Necessária extensão de Perímetro";
        }
        if (regiaoCirculacao.toLowerCase().contains("américa")) {
            regiaoCirculacao = "Região América do Sul, exceto países do Mercosul-Necessária ext. perímetro";
        }
        if (regiaoCirculacao.toLowerCase().contains("mais")) {
            regiaoCirculacao = "Possui mais de uma região de circulação";
        }

        return regiaoCirculacao;
    }

    public static String gerenciamentoRiscoTxt(String risco) {
        if (risco.toLowerCase().contains("cadastro")) {
            risco = "Cadastro de motorista";
        }
        if (risco.toLowerCase().contains("defensiva")) {
            risco = "Direção defensiva";
        }
        if (risco.toLowerCase().contains("preventiva")) {
            risco = "Direção Preventiva";
        }
        if (risco.toLowerCase().contains("escolta")) {
            risco = "Escolta de cargas";
        }
        if (risco.toLowerCase().contains("intel")) {
            risco = "Inteligência embarcada";
        }
        if (risco.toLowerCase().contains("monit")) {
            risco = "Monitoramento via satélite ou celular";
        }
        if (risco.toLowerCase().contains("outros")) {
            risco = "Outros";
        }
        if (risco.toLowerCase().contains("nenhum")) {
            risco = "Nenhum";
        }

        return risco;
    }

    public static String cargaFrequenteTxt(String carga) {
        if (carga.toLowerCase().contains("elétrico")) {
            carga = "Materiais Eletricos";
        }

        return carga;
    }
}
