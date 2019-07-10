package RiskFramework;

import com.opencsv.CSVWriter;
import java.io.*;

public class ExpectedThreat extends Simulation {

    final double riskTolerance = 0.3;
    final String final_file = "e:\\r2q\\final_file.txt";
    double expectedThreat;
    double riskScore;
    double PT_value_phi = 0.4;
    double PT_value_xi = 1.35;

    public double modellingUncertaintyFunc(double reputation_score) {
        double uncertainty = 1.0;
        if (reputation_score == 0.0000000000000000) {
            return uncertainty;
        }
        a = -1.0 * poissionLambda / reputation_score;
        uncertainty = (1.0 - Math.pow(Math.E, a));
        return uncertainty;
    }

    public double Modeling_Expected_Utility_Function(double expectedDamage) {
        d = -1.0 * expectedDamage / riskTolerance;
        a = Math.pow(Math.E, d);
        return (1.0 - a);
    }

    public void finalOutput() {
        extractDataLabelledRequest();
        userNo = Integer.parseInt(userBuffer.delete(0, 4).toString());
        calculateExpectedDamage();      //calculates ExpectedDamage..
        expectedThreat = (securityLevelExt * modellingUncertaintyFunc(ReputationMatrix[userNo][k]))
                + (normalisedSensitivityExt * Modeling_Expected_Utility_Function(expectedDamage));

        if (expectedThreat >= 0.250) {
            riskScore = Math.pow(expectedThreat, PT_value_phi);
        } else if (expectedThreat < 0.250) {
            riskScore = Math.pow(expectedThreat, PT_value_xi);
        }
        //store risk score into the RiskScoreMatrix
        RiskScoreMatrix[userNo][k] = riskScore;

        writerTextBuffer.append(sb).append("\t")
                .append(expectedDamage).append("\t")
                .append(modellingUncertaintyFunc(new_reputation[userNo])).append("\t")
                .append(Modeling_Expected_Utility_Function(expectedDamage)).append("\t")
                .append(expectedThreat).append("\t")
                .append(riskScore).append("\n");
        appendToFile(final_file, writerTextBuffer.toString());
    }
}
//last..
