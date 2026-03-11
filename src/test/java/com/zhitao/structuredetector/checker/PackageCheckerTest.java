package com.zhitao.structuredetector.checker;

import com.zhitao.structuredetector.checkers.PackageStructureCehcker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PackageCheckerTest {

    @Autowired
    PackageStructureCehcker packageStructureCehcker;

    @Test
    public void pathScanResult(){
        assertThat(packageStructureCehcker.scanPackages(Paths.get("D:\\Users\\GummiGu\\IdeaProjects\\javaspring\\structureDetector","src", "main", "java")))
                .contains("config");
    }

    @Test
    public void packageStructureCehckerResult(){
        assertThat(packageStructureCehcker.check("D:\\Users\\GummiGu\\IdeaProjects\\javaspring\\structureDetector").toString())
                .contains("fail","missing");
    }
}
