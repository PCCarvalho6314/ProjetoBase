package bases;

import auxiliar.constants.ProjectConfigConstants;
import generateReport.CsvReport;
import generateReport.FileUtil;
import generateReport.ZipUtils;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import walle.frw.kernel.common.TargetUrlFactory;
import walle.frw.kernel.common.enums.Database;
import walle.frw.kernel.persistance.legacy.DBMainframe;
import walle.frw.web.DriverManager;
import walle.frw.web.base.UiBaseTest;
import walle.frw.web.utils.SeleniumUtils;

import java.lang.reflect.Method;

import static auxiliar.constants.ProjectConfigConstants.reportPath;

public class Base extends UiBaseTest {

    protected DBMainframe dbMainframe;
    protected SeleniumUtils utils;

    @Override
    @BeforeMethod(alwaysRun = true)
    public void onBeforeMethod(Method m) {
        super.onBeforeMethod(m);
        DriverManager.getDriver().get(TargetUrlFactory.provideFrontUrl(m));
        dbMainframe = DBMainframe.getInstance(Database.AZB_EPAC);
        utils = new SeleniumUtils(DriverManager.getDriver());
    }


    @BeforeSuite(alwaysRun = true)
    @Override
    public void onBeforeSuite(ITestContext ctx) {
        super.onBeforeSuite(ctx);
        FileUtil.deleteDirectory(reportPath);
    }

    @AfterSuite(alwaysRun = true)
    @Override
    public void onAfterSuite() {
        super.onAfterSuite();
        CsvReport.createExcelReport();
        ZipUtils.generateZipReport();
        FileUtil.deleteFolder(ProjectConfigConstants.pathReportTemp);
    }
}
