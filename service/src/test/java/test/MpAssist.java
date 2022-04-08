package test;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.assertj.core.util.Lists;

import java.util.ArrayList;
import java.util.Collections;

public class MpAssist {
    public static void main(String[] args) {
        String outputDir = "/Users/wangbintao/workspace/src";
        ArrayList<String> tables = Lists.newArrayList();
        tables.add("project_info");
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/engineer_db?serverTimezone=UTC&characterEncoding=utf-8", "root", "12345678")
                .globalConfig(builder -> {

                    builder.author("wbt") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(outputDir); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.engineer") // 设置父包名
                            .moduleName("assist") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, outputDir + "/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tables); // 设置需要生成的表名
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
