package pages.intra.login;

import auxiliar.constants.PortalAccessBR;

public enum Portal implements PortalAccessBR {

    AUTOLIQUIDACION(LoginUser.MARKETING, MenuOption.COMERCIAL_ADMIN, PortalOption.AUTOLIQUIDACION_BR),
    PRODUCAO(LoginUser.PRODUCCION, MenuOption.PRODUCCION, PortalOption.PRODUCCION_BR),
    FINANCAS(LoginUser.FINANCAS_REPORTING, MenuOption.COMERCIAL_ADMIN, PortalOption.ADMINISTRACAO),
    PROVEEDORES(LoginUser.SINIESTROS, MenuOption.PRODUCCION, PortalOption.PRESTADORES),
    CONSULTAS(LoginUser.VIDA, MenuOption.CONSULTAS, PortalOption.CONSULTAS),
    CONSULTAS_MARKETING(LoginUser.MARKETING, MenuOption.CONSULTAS, PortalOption.CONSULTAS),
    SINIESTROS(LoginUser.SINIESTROS, MenuOption.PRODUCCION, PortalOption.SINIESTROS),
    SINIESTRO_SALUD(LoginUser.SINIESTROS, MenuOption.PRODUCCION, PortalOption.SINIESTRO_SALUD),
    DIALLZ(LoginUser.PRODUCCION, MenuOption.CONSULTAS, PortalOption.DIALLZ),
    RENOVACIONES(LoginUser.PRODUCCION, MenuOption.PRODUCCION, PortalOption.RENOVACIONES),
    GO(LoginUser.SINIESTROS, MenuOption.CONSULTAS, PortalOption.GO),
    GIS(LoginUser.SINIESTROS, MenuOption.PRODUCCION, PortalOption.GIS),
    UMA(LoginUser.TECNICO_PRODUCCION, MenuOption.MANTENIMIENTO_GENERAL, PortalOption.UMA),
    GESTION_CLIENTES(LoginUser.MARKETING, MenuOption.COMERCIAL_ADMIN, PortalOption.GESTION_CLIENTES),
    FICHA_GESTION(LoginUser.SINIESTROS, MenuOption.CONSULTAS, PortalOption.FICHA_GESTION),
    ECONTACT(LoginUser.MARKETING, MenuOption.COMERCIAL_ADMIN, PortalOption.ECONTACT),
    CANCELAMENTO(LoginUser.PRODUCCION, MenuOption.PRODUCCION, PortalOption.CANCELAMENTO_BR);

    private final String loginUser;
    private final String menuName;
    private final String portalname;

    Portal(String loginUser, String menuName, String portalName) {
        this.loginUser = loginUser;
        this.menuName = menuName;
        this.portalname = portalName;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public String getMenuName() {
        return menuName;
    }

    public String getPortalname() {
        return portalname;
    }
}


