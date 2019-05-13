//import com.baomidou.mybatisplus.annotation.DbType;
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.annotation.TableId;
//import com.baomidou.mybatisplus.annotation.Version;
//import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
//import com.baomidou.mybatisplus.core.toolkit.StringUtils;
//import com.baomidou.mybatisplus.extension.activerecord.Model;
//import com.baomidou.mybatisplus.generator.InjectionConfig;
//import com.baomidou.mybatisplus.generator.config.*;
//import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
//import com.baomidou.mybatisplus.generator.config.po.TableField;
//import com.baomidou.mybatisplus.generator.config.po.TableFill;
//import com.baomidou.mybatisplus.generator.config.po.TableInfo;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//
//import java.io.File;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.*;
//
//public class SelfConfigBuilder extends ConfigBuilder {
//    public SelfConfigBuilder(PackageConfig packageConfig, DataSourceConfig dataSourceConfig, StrategyConfig strategyConfig, TemplateConfig template, GlobalConfig globalConfig) {
//        super(packageConfig, dataSourceConfig, strategyConfig, template, globalConfig);
//        if (null == globalConfig) {
//            super.globalConfig = new GlobalConfig();
//        } else {
//            this.globalConfig = globalConfig;
//        }
//        if (null == template) {
//            this.template = new TemplateConfig();
//        } else {
//            this.template = template;
//        }
//
//        if (null == packageConfig) {
//            this.handlerPackage(this.template, this.globalConfig.getOutputDir(), new PackageConfig());
//        } else {
//            this.handlerPackage(this.template, this.globalConfig.getOutputDir(), packageConfig);
//        }
//
//        this.dataSourceConfig = dataSourceConfig;
//        this.handlerDataSource(dataSourceConfig);
//        if (null == strategyConfig) {
//            this.strategyConfig = new StrategyConfig();
//        } else {
//            this.strategyConfig = strategyConfig;
//        }
//
//        this.handlerStrategy(this.strategyConfig);
//
//    }
//
//    public Map<String, String> getPackageInfo() {
//        return this.packageInfo;
//    }
//
//    public Map<String, String> getPathInfo() {
//        return this.pathInfo;
//    }
//
//    public String getSuperEntityClass() {
//        return this.superEntityClass;
//    }
//
//    public String getSuperMapperClass() {
//        return this.superMapperClass;
//    }
//
//    public String getSuperServiceClass() {
//        return this.superServiceClass;
//    }
//
//    public String getSuperServiceImplClass() {
//        return this.superServiceImplClass;
//    }
//
//    public String getSuperControllerClass() {
//        return this.superControllerClass;
//    }
//
//    public List<TableInfo> getTableInfoList() {
//        return this.tableInfoList;
//    }
//
//    public SelfConfigBuilder setTableInfoList(List<TableInfo> tableInfoList) {
//        this.tableInfoList = tableInfoList;
//        return this;
//    }
//
//    public TemplateConfig getTemplate() {
//        return this.template == null ? new TemplateConfig() : this.template;
//    }
//
//    public void handlerPackage(TemplateConfig template, String outputDir, PackageConfig config) {
//        if (CollectionUtils.isEmpty(this.packageInfo)) {
//            packageInfo = new HashMap(8);
//        }
//        this.packageInfo.put("ModuleName", config.getModuleName());
//        this.packageInfo.put("Entity", this.joinPackage(config.getParent(), config.getEntity()));
//        this.packageInfo.put("Mapper", this.joinPackage(config.getParent(), config.getMapper()));
//        this.packageInfo.put("Xml", this.joinPackage(config.getParent(), config.getXml()));
//        this.packageInfo.put("Service", this.joinPackage(config.getParent(), config.getService()));
//        this.packageInfo.put("ServiceImpl", this.joinPackage(config.getParent(), config.getServiceImpl()));
//        this.packageInfo.put("Controller", this.joinPackage(config.getParent(), config.getController()));
//        Map<String, String> configPathInfo = config.getPathInfo();
//        if (null != configPathInfo) {
//            this.pathInfo = configPathInfo;
//        } else {
//            this.pathInfo = new HashMap(6);
//            this.setPathInfo(this.pathInfo, template.getEntity(this.getGlobalConfig().isKotlin()), outputDir, "entity_path", "Entity");
//            this.setPathInfo(this.pathInfo, template.getMapper(), outputDir, "mapper_path", "Mapper");
//            this.setPathInfo(this.pathInfo, template.getXml(), outputDir, "xml_path", "Xml");
//            this.setPathInfo(this.pathInfo, template.getService(), outputDir, "service_path", "Service");
//            this.setPathInfo(this.pathInfo, template.getServiceImpl(), outputDir, "service_impl_path", "ServiceImpl");
//            this.setPathInfo(this.pathInfo, template.getController(), outputDir, "controller_path", "Controller");
//        }
//
//    }
//
//    public void setPathInfo(Map<String, String> pathInfo, String template, String outputDir, String path, String module) {
//        if (StringUtils.isNotEmpty(template)) {
//            pathInfo.put(path, this.joinPath(outputDir, (String) this.packageInfo.get(module)));
//        }
//
//    }
//
//    public void handlerDataSource(DataSourceConfig config) {
//        this.connection = config.getConn();
//        this.dbQuery = config.getDbQuery();
//    }
//
//    public void handlerStrategy(StrategyConfig config) {
//        this.processTypes(config);
//        this.tableInfoList = this.getTablesInfo(config);
//    }
//
//    public void processTypes(StrategyConfig config) {
//        if (StringUtils.isEmpty(config.getSuperServiceClass())) {
//            this.superServiceClass = "com.baomidou.mybatisplus.extension.service.IService";
//        } else {
//            this.superServiceClass = config.getSuperServiceClass();
//        }
//
//        if (StringUtils.isEmpty(config.getSuperServiceImplClass())) {
//            this.superServiceImplClass = "com.baomidou.mybatisplus.extension.service.impl.ServiceImpl";
//        } else {
//            this.superServiceImplClass = config.getSuperServiceImplClass();
//        }
//
//        if (StringUtils.isEmpty(config.getSuperMapperClass())) {
//            this.superMapperClass = "com.baomidou.mybatisplus.core.mapper.BaseMapper";
//        } else {
//            this.superMapperClass = config.getSuperMapperClass();
//        }
//
//        this.superEntityClass = config.getSuperEntityClass();
//        this.superControllerClass = config.getSuperControllerClass();
//    }
//
//    public List<TableInfo> processTable(List<TableInfo> tableList, NamingStrategy strategy, StrategyConfig config) {
//        String[] tablePrefix = config.getTablePrefix();
//
//        TableInfo tableInfo;
//        for (Iterator var5 = tableList.iterator(); var5.hasNext(); this.checkImportPackages(tableInfo)) {
//            tableInfo = (TableInfo) var5.next();
//            String entityName = NamingStrategy.capitalFirst(this.processName(tableInfo.getName(), strategy, tablePrefix));
//            if (StringUtils.isNotEmpty(this.globalConfig.getEntityName())) {
//                tableInfo.setConvert(true);
//                tableInfo.setEntityName(String.format(this.globalConfig.getEntityName(), entityName));
//            } else {
//                tableInfo.setEntityName(this.strategyConfig, entityName);
//            }
//
//            if (StringUtils.isNotEmpty(this.globalConfig.getMapperName())) {
//                tableInfo.setMapperName(String.format(this.globalConfig.getMapperName(), entityName));
//            } else {
//                tableInfo.setMapperName(entityName + "Mapper");
//            }
//
//            if (StringUtils.isNotEmpty(this.globalConfig.getXmlName())) {
//                tableInfo.setXmlName(String.format(this.globalConfig.getXmlName(), entityName));
//            } else {
//                tableInfo.setXmlName(entityName + "Mapper");
//            }
//
//            if (StringUtils.isNotEmpty(this.globalConfig.getServiceName())) {
//                tableInfo.setServiceName(String.format(this.globalConfig.getServiceName(), entityName));
//            } else {
//                tableInfo.setServiceName("I" + entityName + "Service");
//            }
//
//            if (StringUtils.isNotEmpty(this.globalConfig.getServiceImplName())) {
//                tableInfo.setServiceImplName(String.format(this.globalConfig.getServiceImplName(), entityName));
//            } else {
//                tableInfo.setServiceImplName(entityName + "ServiceImpl");
//            }
//
//            if (StringUtils.isNotEmpty(this.globalConfig.getControllerName())) {
//                tableInfo.setControllerName(String.format(this.globalConfig.getControllerName(), entityName));
//            } else {
//                tableInfo.setControllerName(entityName + "Controller");
//            }
//        }
//
//        return tableList;
//    }
//
//    public void checkImportPackages(TableInfo tableInfo) {
//        if (StringUtils.isNotEmpty(this.strategyConfig.getSuperEntityClass())) {
//            tableInfo.getImportPackages().add(this.strategyConfig.getSuperEntityClass());
//        } else if (this.globalConfig.isActiveRecord()) {
//            tableInfo.getImportPackages().add(Model.class.getCanonicalName());
//        }
//
//        if (null != this.globalConfig.getIdType()) {
//            tableInfo.getImportPackages().add(IdType.class.getCanonicalName());
//            tableInfo.getImportPackages().add(TableId.class.getCanonicalName());
//        }
//
//        if (StringUtils.isNotEmpty(this.strategyConfig.getVersionFieldName())) {
//            tableInfo.getFields().forEach((f) -> {
//                if (this.strategyConfig.getVersionFieldName().equals(f.getName())) {
//                    tableInfo.getImportPackages().add(Version.class.getCanonicalName());
//                }
//
//            });
//        }
//
//    }
//
//    public List<TableInfo> getTablesInfo(StrategyConfig config) {
//        boolean isInclude = null != config.getInclude() && config.getInclude().length > 0;
//        boolean isExclude = null != config.getExclude() && config.getExclude().length > 0;
//        if (isInclude && isExclude) {
//            throw new RuntimeException("<strategy> 标签中 <include> 与 <exclude> 只能配置一项！");
//        } else {
//            List<TableInfo> tableList = new ArrayList();
//            List<TableInfo> includeTableList = new ArrayList();
//            List<TableInfo> excludeTableList = new ArrayList();
//            HashSet notExistTables = new HashSet();
//
//            try {
//                String tablesSql = this.dbQuery.tablesSql();
//                String schema;
//                if (DbType.POSTGRE_SQL == this.dbQuery.dbType()) {
//                    schema = this.dataSourceConfig.getSchemaName();
//                    if (schema == null) {
//                        schema = "public";
//                        this.dataSourceConfig.setSchemaName(schema);
//                    }
//
//                    tablesSql = String.format(tablesSql, schema);
//                } else if (DbType.ORACLE == this.dbQuery.dbType()) {
//                    schema = this.dataSourceConfig.getSchemaName();
//                    if (schema == null) {
//                        schema = this.dataSourceConfig.getUsername().toUpperCase();
//                        this.dataSourceConfig.setSchemaName(schema);
//                    }
//
//                    tablesSql = String.format(tablesSql, schema);
//                    StringBuilder sb;
//                    if (isInclude) {
//                        sb = new StringBuilder(tablesSql);
//                        sb.append(" AND ").append(this.dbQuery.tableName()).append(" IN (");
//                        Arrays.stream(config.getInclude()).forEach((tbname) -> {
//                            sb.append("'").append(tbname.toUpperCase()).append("',");
//                        });
//                        sb.replace(sb.length() - 1, sb.length(), ")");
//                        tablesSql = sb.toString();
//                    } else if (isExclude) {
//                        sb = new StringBuilder(tablesSql);
//                        sb.append(" AND ").append(this.dbQuery.tableName()).append(" NOT IN (");
//                        Arrays.stream(config.getExclude()).forEach((tbname) -> {
//                            sb.append("'").append(tbname.toUpperCase()).append("',");
//                        });
//                        sb.replace(sb.length() - 1, sb.length(), ")");
//                        tablesSql = sb.toString();
//                    }
//                }
//
//                PreparedStatement preparedStatement = this.connection.prepareStatement(tablesSql);
//                Throwable var11 = null;
//
//                try {
//                    ResultSet results = preparedStatement.executeQuery();
//                    Throwable var13 = null;
//
//                    try {
//                        label544:
//                        while (true) {
//                            String tableName;
//                            String tableComment;
//                            do {
//                                while (true) {
//                                    if (!results.next()) {
//                                        break label544;
//                                    }
//
//                                    tableName = results.getString(this.dbQuery.tableName());
//                                    if (StringUtils.isNotEmpty(tableName)) {
//                                        tableComment = results.getString(this.dbQuery.tableComment());
//                                        break;
//                                    }
//
//                                    System.err.println("当前数据库为空！！！");
//                                }
//                            } while (config.isSkipView() && "VIEW".equals(tableComment));
//
//                            TableInfo tableInfo = new TableInfo();
//                            tableInfo.setName(tableName);
//                            tableInfo.setComment(tableComment);
//                            String[] var16;
//                            int var17;
//                            int var18;
//                            String excludeTable;
//                            if (isInclude) {
//                                var16 = config.getInclude();
//                                var17 = var16.length;
//
//                                for (var18 = 0; var18 < var17; ++var18) {
//                                    excludeTable = var16[var18];
//                                    if (this.tableNameMatches(excludeTable, tableName)) {
//                                        includeTableList.add(tableInfo);
//                                    } else {
//                                        notExistTables.add(excludeTable);
//                                    }
//                                }
//                            } else if (isExclude) {
//                                var16 = config.getExclude();
//                                var17 = var16.length;
//
//                                for (var18 = 0; var18 < var17; ++var18) {
//                                    excludeTable = var16[var18];
//                                    if (this.tableNameMatches(excludeTable, tableName)) {
//                                        excludeTableList.add(tableInfo);
//                                    } else {
//                                        notExistTables.add(excludeTable);
//                                    }
//                                }
//                            }
//
//                            tableList.add(tableInfo);
//                        }
//                    } catch (Throwable var43) {
//                        var13 = var43;
//                        throw var43;
//                    } finally {
//                        if (results != null) {
//                            if (var13 != null) {
//                                try {
//                                    results.close();
//                                } catch (Throwable var42) {
//                                    var13.addSuppressed(var42);
//                                }
//                            } else {
//                                results.close();
//                            }
//                        }
//
//                    }
//                } catch (Throwable var45) {
//                    var11 = var45;
//                    throw var45;
//                } finally {
//                    if (preparedStatement != null) {
//                        if (var11 != null) {
//                            try {
//                                preparedStatement.close();
//                            } catch (Throwable var41) {
//                                var11.addSuppressed(var41);
//                            }
//                        } else {
//                            preparedStatement.close();
//                        }
//                    }
//
//                }
//
//                Iterator var49 = tableList.iterator();
//
//                while (var49.hasNext()) {
//                    TableInfo tabInfo = (TableInfo) var49.next();
//                    notExistTables.remove(tabInfo.getName());
//                }
//
//                if (notExistTables.size() > 0) {
//                    System.err.println("表 " + notExistTables + " 在数据库中不存在！！！");
//                }
//
//                if (isExclude) {
//                    tableList.removeAll(excludeTableList);
//                    includeTableList = tableList;
//                }
//
//                if (!isInclude && !isExclude) {
//                    includeTableList = tableList;
//                }
//
//                includeTableList.forEach((ti) -> {
//                    this.convertTableFields(ti, config.getColumnNaming());
//                });
//            } catch (SQLException var47) {
//                var47.printStackTrace();
//            }
//
//            return this.processTable(includeTableList, config.getNaming(), config);
//        }
//    }
//
//    public boolean tableNameMatches(String setTableName, String dbTableName) {
//        return setTableName.equals(dbTableName) || StringUtils.matches(setTableName, dbTableName);
//    }
//
//    public TableInfo convertTableFields(TableInfo tableInfo, NamingStrategy strategy) {
//        boolean haveId = false;
//        List<TableField> fieldList = new ArrayList();
//        List<TableField> commonFieldList = new ArrayList();
//        DbType dbType = this.dbQuery.dbType();
//        String tableName = tableInfo.getName();
//
//        try {
//            String tableFieldsSql = this.dbQuery.tableFieldsSql();
//            Set<String> h2PkColumns = new HashSet();
//            PreparedStatement pkQueryStmt;
//            Throwable var11;
//            ResultSet results;
//            Throwable var13;
//            if (DbType.POSTGRE_SQL == dbType) {
//                tableFieldsSql = String.format(tableFieldsSql, this.dataSourceConfig.getSchemaName(), tableName);
//            } else if (DbType.ORACLE == dbType) {
//                tableName = tableName.toUpperCase();
//                tableFieldsSql = String.format(tableFieldsSql.replace("#schema", this.dataSourceConfig.getSchemaName()), tableName);
//            } else if (DbType.H2 == dbType) {
//                tableName = tableName.toUpperCase();
//                pkQueryStmt = this.connection.prepareStatement(String.format("select * from INFORMATION_SCHEMA.INDEXES WHERE TABLE_NAME = '%s'", tableName));
//                var11 = null;
//
//                try {
//                    results = pkQueryStmt.executeQuery();
//                    var13 = null;
//
//                    try {
//                        while (results.next()) {
//                            String primaryKey = results.getString(this.dbQuery.fieldKey());
//                            if (Boolean.valueOf(primaryKey)) {
//                                h2PkColumns.add(results.getString(this.dbQuery.fieldName()));
//                            }
//                        }
//                    } catch (Throwable var97) {
//                        var13 = var97;
//                        throw var97;
//                    } finally {
//                        if (results != null) {
//                            if (var13 != null) {
//                                try {
//                                    results.close();
//                                } catch (Throwable var92) {
//                                    var13.addSuppressed(var92);
//                                }
//                            } else {
//                                results.close();
//                            }
//                        }
//
//                    }
//                } catch (Throwable var99) {
//                    var11 = var99;
//                    throw var99;
//                } finally {
//                    if (pkQueryStmt != null) {
//                        if (var11 != null) {
//                            try {
//                                pkQueryStmt.close();
//                            } catch (Throwable var91) {
//                                var11.addSuppressed(var91);
//                            }
//                        } else {
//                            pkQueryStmt.close();
//                        }
//                    }
//
//                }
//
//                tableFieldsSql = String.format(tableFieldsSql, tableName);
//            } else {
//                tableFieldsSql = String.format(tableFieldsSql, tableName);
//            }
//
//            pkQueryStmt = this.connection.prepareStatement(tableFieldsSql);
//            var11 = null;
//
//            try {
//                results = pkQueryStmt.executeQuery();
//                var13 = null;
//
//                try {
//                    while (results.next()) {
//                        TableField field = new TableField();
//                        String columnName = results.getString(this.dbQuery.fieldName());
//                        boolean isId;
//                        if (DbType.H2 == dbType) {
//                            isId = h2PkColumns.contains(columnName);
//                        } else {
//                            String key = results.getString(this.dbQuery.fieldKey());
//                            if (DbType.DB2 == dbType) {
//                                isId = StringUtils.isNotEmpty(key) && "1".equals(key);
//                            } else {
//                                isId = StringUtils.isNotEmpty(key) && "PRI".equals(key.toUpperCase());
//                            }
//                        }
//
//                        if (isId && !haveId) {
//                            field.setKeyFlag(true);
//                            if (DbType.H2 == dbType || this.dbQuery.isKeyIdentity(results)) {
//                                field.setKeyIdentityFlag(true);
//                            }
//
//                            haveId = true;
//                        } else {
//                            field.setKeyFlag(false);
//                        }
//
//                        String[] fcs = this.dbQuery.fieldCustom();
//                        if (null != fcs) {
//                            Map<String, Object> customMap = new HashMap();
//                            String[] var19 = fcs;
//                            int var20 = fcs.length;
//
//                            for (int var21 = 0; var21 < var20; ++var21) {
//                                String fc = var19[var21];
//                                customMap.put(fc, results.getObject(fc));
//                            }
//
//                            field.setCustomMap(customMap);
//                        }
//
//                        field.setName(columnName);
//                        field.setType(results.getString(this.dbQuery.fieldType()));
//                        field.setPropertyName(this.strategyConfig, this.processName(field.getName(), strategy));
//                        field.setColumnType(this.dataSourceConfig.getTypeConvert().processTypeConvert(this.globalConfig, field.getType()));
//                        field.setComment(results.getString(this.dbQuery.fieldComment()));
//                        if (this.strategyConfig.includeSuperEntityColumns(field.getName())) {
//                            commonFieldList.add(field);
//                        } else {
//                            List<TableFill> tableFillList = this.getStrategyConfig().getTableFillList();
//                            if (null != tableFillList) {
//                                tableFillList.stream().filter((tf) -> {
//                                    return tf.getFieldName().equalsIgnoreCase(field.getName());
//                                }).findFirst().ifPresent((tf) -> {
//                                    field.setFill(tf.getFieldFill().name());
//                                });
//                            }
//
//                            fieldList.add(field);
//                        }
//                    }
//                } catch (Throwable var93) {
//                    var13 = var93;
//                    throw var93;
//                } finally {
//                    if (results != null) {
//                        if (var13 != null) {
//                            try {
//                                results.close();
//                            } catch (Throwable var90) {
//                                var13.addSuppressed(var90);
//                            }
//                        } else {
//                            results.close();
//                        }
//                    }
//
//                }
//            } catch (Throwable var95) {
//                var11 = var95;
//                throw var95;
//            } finally {
//                if (pkQueryStmt != null) {
//                    if (var11 != null) {
//                        try {
//                            pkQueryStmt.close();
//                        } catch (Throwable var89) {
//                            var11.addSuppressed(var89);
//                        }
//                    } else {
//                        pkQueryStmt.close();
//                    }
//                }
//
//            }
//        } catch (SQLException var101) {
//            System.err.println("SQL Exception：" + var101.getMessage());
//        }
//
//        tableInfo.setFields(fieldList);
//        tableInfo.setCommonFields(commonFieldList);
//        return tableInfo;
//    }
//
//    public String joinPath(String parentDir, String packageName) {
//        if (StringUtils.isEmpty(parentDir)) {
//            parentDir = System.getProperty("java.io.tmpdir");
//        }
//
//        if (!StringUtils.endsWith(parentDir, File.separator)) {
//            parentDir = parentDir + File.separator;
//        }
//
//        packageName = packageName.replaceAll("\\.", "\\" + File.separator);
//        return parentDir + packageName;
//    }
//
//    public String joinPackage(String parent, String subPackage) {
//        return StringUtils.isEmpty(parent) ? subPackage : parent + "." + subPackage;
//    }
//
//    public String processName(String name, NamingStrategy strategy) {
//        return this.processName(name, strategy, this.strategyConfig.getFieldPrefix());
//    }
//
//    public String processName(String name, NamingStrategy strategy, String[] prefix) {
//        boolean removePrefix = false;
//        if (prefix != null && prefix.length != 0) {
//            removePrefix = true;
//        }
//
//        String propertyName;
//        if (removePrefix) {
//            if (strategy == NamingStrategy.underline_to_camel) {
//                propertyName = NamingStrategy.removePrefixAndCamel(name, prefix);
//            } else {
//                propertyName = NamingStrategy.removePrefix(name, prefix);
//            }
//        } else if (strategy == NamingStrategy.underline_to_camel) {
//            propertyName = NamingStrategy.underlineToCamel(name);
//        } else {
//            propertyName = name;
//        }
//
//        return propertyName;
//    }
//
//    public StrategyConfig getStrategyConfig() {
//        return this.strategyConfig;
//    }
//
//    public SelfConfigBuilder setStrategyConfig(StrategyConfig strategyConfig) {
//        this.strategyConfig = strategyConfig;
//        return this;
//    }
//
//    public GlobalConfig getGlobalConfig() {
//        return this.globalConfig;
//    }
//
//    public SelfConfigBuilder setGlobalConfig(GlobalConfig globalConfig) {
//        this.globalConfig = globalConfig;
//        return this;
//    }
//
//    public InjectionConfig getInjectionConfig() {
//        return this.injectionConfig;
//    }
//
//    public SelfConfigBuilder setInjectionConfig(InjectionConfig injectionConfig) {
//        this.injectionConfig = injectionConfig;
//        return this;
//    }
//}