package pages.epac;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import walle.frw.kernel.common.NoDriverUtils;
import walle.frw.web.page.PageObjectBase;

public class ValorSeguroPage extends PageObjectBase {

    public ValorSeguroPage(WebDriver driver) {
        super(driver);
    }

    //LOCATORS - XPATH
    private static final String OPTION_PAGAMENTO_BY_NUMBER_PACKAGE = "//*[@id='opCompacto%s']";


    private static final String BUTTON_PRINT_QUOTATION = "//*[@id='btnSaveQuotation']";
    private static final String LINHA_QUANTIDADE_PARCELAS_BY_FORMAPAGAMENTO = "(//label[text()='%s']/following::button[@class='header-price-option-payment-grid-subtitle-conteudo'])[%s]";

    private static final String BUTTON_COMPRAR = "//*[@id='btnComprar']";
    private static final String BUTTON_COMPRAR_OPTION_ID = "btnPackage%S";

    private static final String LOADING_BACKGROUND_ID = "loadingBackground";
    private static final String LOADING_BACKGROUND_2 = "//*[@id='divDialogProcess']";



    //CHECKS
    public boolean isValorSeguroPageDisplayed() {
        return isElementVisible(BUTTON_PRINT_QUOTATION, 5);
    }


    //ACTIONS
    @Step("Clica no botão 'Opcões de Pagamento' seleciona  do pacote {0}")
    public void clickOpcoesPagamentoButtonByPackage(String pacote) {
        NoDriverUtils.await(5);
        String numPacote;
        switch (pacote) {
            case "Compacto": {
                numPacote = "1";
            }
            break;
            case "Ampliado 2": {
                numPacote = "3";
            }
            break;
            case "Especial": {
                numPacote = "4";
            }
            break;
            case "Exclusivo": {
                numPacote = "5";
            }
            break;
            case "Ampliado": {
                numPacote = "7";
            }
            break;
            default:
                throw new IllegalArgumentException("O pacote informado não existe. Insira um dos presentes na classe Constants");
        }
        click(String.format(OPTION_PAGAMENTO_BY_NUMBER_PACKAGE, numPacote));
        makeScreenshot();
    }

    public void waitLoadingToFinish() {
        waitForVisible(LOADING_BACKGROUND_2);
        waitforInvisibleHard(LOADING_BACKGROUND_2, 3, 12);
    }

    @Step("Seleciona a quantindade de parcelas {0}x")
    public void selectFormaPagamento_E_NumParcelas(String formaPagamento, String numParcelas) {
        clickByJS(String.format(LINHA_QUANTIDADE_PARCELAS_BY_FORMAPAGAMENTO, formaPagamento, numParcelas));
        makeScreenshot();
    }
    @Step("Clica no botão 'Comprar'")
    public void clickComprar() {
        waitForDialogCoverHide();
        click(BUTTON_COMPRAR);
    }

    @Step("Clica no botão 'Comprar' do pacote {0}")
    public void clickComprarByNumberPackage(String packageNumber) {
        waitLoadingToFinish();

        String numComprarPacote;

        switch (packageNumber) {
            case "Compacto": {
                numComprarPacote = "1";
            }
            break;
            case "Ampliado 2": {
                numComprarPacote = "3";
            }
            break;
            case "Especial": {
                numComprarPacote = "4";
            }
            break;
            case "Exclusivo": {
                numComprarPacote = "5";
            }
            break;
            case "Ampliado": {
                numComprarPacote = "7";
            }
            break;
            default:
                throw new IllegalArgumentException("O pacote informado não existe. Insira um dos presentes na classe Constants");
        }
        click(String.format(BUTTON_COMPRAR_OPTION_ID, numComprarPacote));
        makeScreenshot();
    }

}
