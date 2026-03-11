package com.zhitao.structuredetector.checkers;

import com.zhitao.structuredetector.report.CheckResult;

public class ClassNameChecker implements Checker {
    @Override
    public String getName() {
        return "ClassNameChecker";
    }

    @Override
    public CheckResult check(String projectPath) {
        CheckResult checkResult = new CheckResult(getName());
        return checkResult;
    }
}
