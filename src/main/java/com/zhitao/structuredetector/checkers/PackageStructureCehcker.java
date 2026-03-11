package com.zhitao.structuredetector.checkers;

import com.zhitao.structuredetector.config.CheckerProperties;
import com.zhitao.structuredetector.report.CheckResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class PackageStructureCehcker implements Checker {
    private List<String> packageRequiredPackages;

    public  PackageStructureCehcker(CheckerProperties checkerProperties) {
        this.packageRequiredPackages = checkerProperties.getPackageRequiredPackages();
    }

    @Override
    public String getName() {
        return "PackageStructureCehcker";
    }

    @Override
    public CheckResult check(String projectPath) {
        CheckResult checkResult = new CheckResult(getName());

        Path javaPath = Paths.get(projectPath,"src", "main", "java");

        if (!Files.exists(javaPath)) {
            checkResult.addError("找不到源代码目录：" + javaPath);
            return checkResult;
        }

        List<String> foundPackages = scanPackages(javaPath);

        for (String required : packageRequiredPackages) {
            boolean found = foundPackages.stream()
                    .anyMatch(pkg -> pkg.contains(required));
            if (!found) {
                checkResult.addError("missing packages：" + required);
            }
        }

        return checkResult;
    }
    public List<String> scanPackages(Path javaPath) {
        try {
            return Files.walk(javaPath)
                    .filter(Files::isDirectory)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            log.error("scan {} fail：{}", javaPath, e.getMessage(), e);
            return new ArrayList<>();
        }
    }

}
