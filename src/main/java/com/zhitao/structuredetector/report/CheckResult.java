package com.zhitao.structuredetector.report;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class CheckResult {
    private String checkerName;
    private boolean passed;
    private List<String> errors;
    private List<String> warnings;

    public CheckResult(String checkerName) {
        this.checkerName = checkerName;
        this.passed = true;
        this.errors = new ArrayList<>();
        this.warnings = new ArrayList<>();
    }

    public String toString(){
        if (!errors.isEmpty() || !warnings.isEmpty()){
            passed=false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("checker：").append(checkerName).append("\n");
        sb.append("result：").append(passed ? "✅ pass" : "❌ fail").append("\n");

        if (!errors.isEmpty()) {
            sb.append("errors：\n");
            for (String error : errors) {
                sb.append("  - ").append(error).append("\n");
            }
        }

        if (!warnings.isEmpty()) {
            sb.append("warning：\n");
            for (String warning : warnings) {
                sb.append("  - ").append(warning).append("\n");
            }
        }

        return sb.toString();
    }
}
