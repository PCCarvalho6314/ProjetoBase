package auxiliar.constants;

public interface PortalAccessBR {

    interface LoginUser {
        //Epac
        String USER1_EPAC = "BA261870";
        String USER2_EPAC = "BA919190";

        //Intranet
        String PRODUCCION = "Produção e recibos";
        String FINANCAS_REPORTING = "Finanças e reporting";

        String SINIESTROS = "Sinistros, GE e Impressão";
        String MARKETING = "Marketing, Comercial e Suporte";
        String VIDA = "Vida, Resseguros e RH";
        String TECNICO_PRODUCCION = "Técnico Produção";
    }

    interface MenuOption {
        String PRODUCCION = "Produção & Sinistros";
        String COMERCIAL_ADMIN = "Gestão & Marketing";
        String CONSULTAS = "Consultas & Estatísticas";
        String MANTENIMIENTO_GENERAL = "Manutenção Geral";
    }

    interface MenuOptionEpac {
        String VENTA = "Vendas";
        String ADMINISTRACION = "Gestão";
        String CONSULTAS = "Consultas";
    }

    interface SubMenuOptionEpac {
        String FINANCIERO = "Financeiro";
        String ENDOSSO = "Endossos";
        String SINIESTROS = "Sinistros";
        String CONSULTAS = "Consultas";
    }

    interface PortalOptionEpac {
        String AUTOLIQUIDACION = "Autoliquidación";
        String ENDOSSO = "Endosso";
        String CUENTA_AGENTE = "Cuenta agente angular";
        String NOTIF_SINIESTROS = "Abertura de Sinistros";
        String RENOVACIONES = "Gestão Renovações";
        String CONSULTAS_GERAIS = "Consultas Gerais";
        String COTACOES_REALIZADAS = "Cotações realizadas";
    }

    interface TabOption {
        String AUTOS = "Auto";
        String RESIDENCIA = "Residência";
        String CONDOMINIO = "Condomínio";
        String EMPRESAS = "Empresarial";
        String VIDA_SAUDE = "Vida e Saude";
        String AGRO = "Agronegócios";
        String OTHERS = "Demais Produtos";
        String GRANDES_RISCOS = "Grandes Riscos";
        String BENEFICIOS = "Benefícios";


    }

    interface PortalOption {
        String PRODUCCION = "Trem de Producão";
        String PRODUCCION_BR = "Produção";
        String ADMINISTRACAO = "Admnistração";

        String CONSULTAS = "Consultas Gerais";
        String AUTOLIQUIDACION = "Auto-liquidação SEA";
        String AUTOLIQUIDACION_BR = "Gestão recibos";
        String PRESTADORES = "Prestadores";
        String SINIESTRO_SALUD = "Sinistros de Saude";
        String DIALLZ = "Dia-Allz";
        String RENOVACIONES = "Gestão renovações";
        String GO = "GO!";
        String GIS = "GIS";
        String SINIESTROS_SALUD = "Sinistros de Saude";
        String UMA = "UMA";
        String ECONTACT = "e-Contact Allianz";
        String GESTION_CLIENTES = "Gestão Clientes";
        String FICHA_GESTION = "Nueva Ficha de Gestión BR";
        String SINIESTROS = "Notificação de Sinistros";
        String CANCELAMENTO_BR = "Cancelamento";

    }

    interface PortalProduct {
        String OTHERS_01 = "RD Equipamentos";
        String OTHERS_02 = "Náutica";

        String VIDA_00 = "Acidentes Pessoais Individual +";
        String VIDA_01 = "Vida PME";
        String VIDA_02 = "Acidentes Coletivo";
        String VIDA_03 = "PME";
        String VIDA_04 = "Vida Individual";
        String VIDA_05 = "Acidentes Pessoais Individual";
        String VIDA_06 = "Vida Global Tradicional Digital";


        String AGRO_01 = "Agrícola";
        String AGRO_02 = "Allianz Grãos Granizo";
        String AGRO_03 = "Allianz Frutas & Hortaliças";
        String AGRO_04 = "Allianz Equipamentos Agrícolas";
        String PROP_RURAIS = "Propriedades Rurais";


        String AUTO_01 = "Automóvel";
        String AUTO_02 = "Moto";
        String AUTO_03 = "Caminhão";
        String AUTO_04 = "Frotas FastTrack";

        String EMPRESA_01 = "Corporate - VR acima R$ 10 MM";
        String EMPRESA_02 = "Empresa PME Multi Itens - VR até R$ 10 MM";
        String EMPRESA_03 = "Empresa PME - VR até R$ 20 MM";
        String EMPRESA_04 = "Corporate LMI Único";
        String EMPRESA_05 = "Corporate Multi Itens - VR acima R$ 10 MM";


        String CONDOMINIO_01 = "Condomínio Amplo Digital";
        String CONDOMINIO_02 = "Condomínio Simples Digital";

        String RESIDENCIA_01 = "Residência";
    }
}