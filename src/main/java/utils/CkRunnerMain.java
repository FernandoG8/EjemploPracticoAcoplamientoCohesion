
package utils;

import com.github.mauricioaniche.ck.Runner;   // ← ESTE import

public class CkRunnerMain {
    public static void main(String[] args) throws Exception {
        String projectDir = "src/main/java/com/GoodDemo"; //
        String useJars = "false";
        String maxPerPartition = "0";
        String varsAndFields = "false";
        String outDir = "ckout";

        Runner.main(new String[]{ projectDir, useJars, maxPerPartition, varsAndFields, outDir });
        System.out.println("✅ CK ejecutado. Revisa carpeta: " + outDir);
    }
}
