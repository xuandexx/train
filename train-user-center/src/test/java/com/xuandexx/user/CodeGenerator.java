package com.xuandexx.user;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * mp生成器
 * 
 * @author LJH
 */
public class CodeGenerator {

	public static void main(String[] args) {
		// 代码生成器
		AutoGenerator mpg = new AutoGenerator();
		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		String projectPath = System.getProperty("user.dir");
		gc.setOutputDir(projectPath + "/src/main/java");
		// gc.setOutputDir("D:\\workspace-sts\\0520adv\\02_mp_springboot/src/main/java");
		gc.setAuthor("Simon");
		gc.setOpen(false);// 当代码生成完成之后是否打开代码所在的文件夹
		// gc.setSwagger2(true); 实体属性 Swagger2 注解
		// gc.setServiceName("%sService");
		mpg.setGlobalConfig(gc);
		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setUrl(
				"jdbc:mysql://47.110.38.195:3306/train?useUnicode=true&characterEncoding=utf8&useSSL=true&serverTimezone=UTC");
		// dsc.setSchemaName("public");
		dsc.setDriverName("com.mysql.cj.jdbc.Driver");
		dsc.setUsername("simon");
		dsc.setPassword("123456");
		mpg.setDataSource(dsc);

		// 包配置
		PackageConfig pc = new PackageConfig();
//        pc.setModuleName(scanner("模块名"));
		pc.setParent("com.xuandexx.user");// controller entity service service.impl
		pc.setController("controller");
		pc.setEntity("domain");
		pc.setMapper("mapper");
		pc.setService("service");
		pc.setServiceImpl("service.impl");
		pc.setXml("mapper.xml");
		mpg.setPackageInfo(pc);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		// 设置字段和表名的是否把下划线完成驼峰命名规则
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		// 设置生成的实体类继承的父类
//        strategy.setSuperEntityClass("com.ksshop.heyopet.BaseEntity");
		strategy.setEntityLombokModel(true);// 是否启动lombok
		strategy.setRestControllerStyle(true);// 是否生成resetController
		// 公共父类
//        strategy.setSuperControllerClass("com.ksshop.heyopet.BaseController");
		// 写于父类中的公共字段
//        strategy.setSuperEntityColumns("person_id","person_name");
		// 要设置生成哪些表 如果不设置就是生成所有的表
//        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
		strategy.setControllerMappingHyphenStyle(true);
//        strategy.setTablePrefix(pc.getModuleName() + "_");
		strategy.setTablePrefix("t_");
		mpg.setStrategy(strategy);
		mpg.execute();
	}

}