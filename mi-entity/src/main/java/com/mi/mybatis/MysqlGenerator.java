package com.mi.mybatis;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Arrays;

/**
 * @ClassName MysqlGenerator
 * @Author yangli
 * @Date 2021/10/25 11:11
 * @Description:
 */
public class MysqlGenerator {

    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        // 选择 freemarker 引擎，默认 Veloctiy
        //mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir("F:\\idea_workspace\\mi-project\\mi-entity\\src\\main\\java");
        globalConfig.setAuthor("yangli");
        globalConfig.setFileOverride(true);
        globalConfig.setOpen(false);
        mpg.setGlobalConfig(globalConfig);

        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://127.0.0.1:3306/electronic?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("root");
        mpg.setDataSource(dataSourceConfig);


        // 3、包配置
        PackageConfig pc = new PackageConfig();
        //pc.setModuleName("mi_user");
        pc.setParent("com.mi");
        mpg.setPackageInfo(pc);


        // 4、策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperControllerClass("com.mi.controller.BaseController");
//        strategy.setSuperEntityClass("com.mi.entity.BaseEntity");
        strategy.setTablePrefix("mi_"); // 表名前缀
        strategy.setEntityLombokModel(true); //使用lombok
        String[] tableNames = {"mi_goods","mi_orders"};
        strategy.setInclude(tableNames);  // 逆向工程使用的表   如果要生成多个,这里可以传入String[]
        mpg.setStrategy(strategy);

        //5、执行
        mpg.execute();
    }
}
