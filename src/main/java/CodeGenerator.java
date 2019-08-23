import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Slf4j
public class CodeGenerator {
    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static String dbDriver = "com.mysql.cj.jdbc.Driver";
    public static String[] tables = {
            "payment", "payment_bank_history", "payment_channel", "payment_merchant", "payment_order", "payment_order_bank_detail", "payment_support_bank"
    };//"kk_platform_info_log",
    public static String codePackage = "system";
    public static String codeModule = "com.max";

    public static void main(String[] args) {
        //设置数据库
        //CodeGeneratorDatabase.setOnlineDB();
        CodeGeneratorDatabase.setOfflineDB();
        // 代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();
        // set freemarker engine
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        globalConfig.setOutputDir(projectPath + "/src/main/java");
        globalConfig.setAuthor("yeats");
        globalConfig.setOpen(false);
        globalConfig.setFileOverride(true);
        globalConfig.setSwagger2(true);
        globalConfig.setBaseResultMap(true);
        globalConfig.setBaseColumnList(true);
        //globalConfig.setDateType(true);
        globalConfig.setServiceName("%sService");
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        autoGenerator.setGlobalConfig(globalConfig);

        // 数据源配置
        DataSourceConfig dataSource = new DataSourceConfig();
        dataSource.setUrl("jdbc:mysql://" + CodeGeneratorDatabase.dbURL + "?useUnicode=true&serverTimezone=UTC&useSSL=false&autoReconnect=true&characterEncoding=utf-8");
        // dsc.setSchemaName("public");
        dataSource.setDriverName(dbDriver);
        dataSource.setUsername(CodeGeneratorDatabase.dbUSER);
        dataSource.setPassword(CodeGeneratorDatabase.dbPass);
        autoGenerator.setDataSource(dataSource);

        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setModuleName(codePackage);
        packageConfig.setParent(codeModule);

        //pc.setPathInfo(pageInfo);

        autoGenerator.setPackageInfo(packageConfig);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/ftl/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/java/" + codeModule + "/" + codePackage + "/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });*/
        cfg.setFileOutConfigList(focList);
        autoGenerator.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setController("/ftl/controller.java");
        templateConfig.setEntity("/ftl/entity.java");
        templateConfig.setMapper("/ftl/mapper.java");
        templateConfig.setService("/ftl/service.java");
        templateConfig.setServiceImpl("/ftl/serviceImpl.java");
        templateConfig.setDto("/ftl/dto.java");
        templateConfig.setReqDto("/ftl/reqDto.java");
        templateConfig.setResponseDto("/ftl/responseDto.java");
        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        autoGenerator.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        //strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
        strategy.setInclude(tables);
        strategy.setSuperEntityColumns("id");
        //strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(packageConfig.getModuleName() + "_");
        strategy.setTablePrefix("f_tbl_info_activity_");
        autoGenerator.setStrategy(strategy);
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());


       /* InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {

            }
        };
        autoGenerator.setCfg(injectionConfig);*/

//        if (null != this.injectionConfig) {
//            this.injectionConfig.setConfig(this.config);
//        }
        //self.setPathInfo(self.pathInfo, "/ftl/dto.java", self.globalConfig.getOutputDir(), "dto_path", "Controller");
        //autoGenerator.setConfig(self);
        autoGenerator.execute();
    }
}
