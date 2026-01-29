package pages.epac.login;

import auxiliar.constants.PortalAccessBR;

public enum PortalProductIssuancePage implements PortalAccessBR {
    AUTO(LoginUser.USER2_EPAC, TabOption.AUTOS, PortalProduct.AUTO_01),
    MOTO(LoginUser.USER2_EPAC, TabOption.AUTOS, PortalProduct.AUTO_02),
    TRUCK(LoginUser.USER2_EPAC, TabOption.AUTOS, PortalProduct.AUTO_03),
    RESIDENCIA(LoginUser.USER2_EPAC, TabOption.RESIDENCIA, PortalProduct.RESIDENCIA_01),
    EMPRESARIAL_PME(LoginUser.USER2_EPAC, TabOption.EMPRESAS, PortalProduct.EMPRESA_03),

    VIDA(LoginUser.USER2_EPAC, TabOption.VIDA_SAUDE, PortalProduct.VIDA_06),

    VIDA_2(LoginUser.USER2_EPAC, TabOption.BENEFICIOS, PortalProduct.VIDA_06),


    CONSULTAS(LoginUser.USER2_EPAC, "", "");


    private final String loginUser;
    private final String tabName;
    private final String productName;

    PortalProductIssuancePage(String loginUser, String tabName, String productName) {
        this.loginUser = loginUser;
        this.tabName = tabName;
        this.productName = productName;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public String getTabName() {
        return tabName;
    }

    public String getProductName() {
        return productName;
    }
}


