package com.zhitao.structuredetector.checkers;

import com.zhitao.structuredetector.report.CheckResult;

public interface Checker {
    String getName();
    CheckResult check(String projectPath);
}
