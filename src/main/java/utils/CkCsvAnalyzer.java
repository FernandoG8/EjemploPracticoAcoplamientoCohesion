package utils;

import java.io.*;
import java.util.*;

public class CkCsvAnalyzer {

    // Estructura de métricas de clase
    static class ClassMetrics {
        String className;
        double cbo, lcomStar, rfc, loc;
        List<MethodMetrics> methods = new ArrayList<>();
    }

    // Estructura de métricas de método
    static class MethodMetrics {
        String methodName;
        double wmc, rfc, loc;

        public MethodMetrics(String methodName, double wmc, double rfc, double loc) {
            this.methodName = methodName;
            this.wmc = wmc;
            this.rfc = rfc;
            this.loc = loc;
        }
    }

    public static void main(String[] args) {
        String classFile = "ckoutclass.csv";
        String methodFile = "ckoutmethod.csv";
        String outputFile = "ckoutsummary.csv";

        Map<String, ClassMetrics> metricsMap = new HashMap<>();

        // 1️⃣ Leer métricas de clase
        readClassMetrics(classFile, metricsMap);

        // 2️⃣ Leer métricas de métodos
        readMethodMetrics(methodFile, metricsMap);

        // 3️⃣ Filtrar y mostrar resultados
        System.out.printf("%n%-40s %-8s %-8s %-8s %-8s%n", "Clase", "CBO", "LCOM*", "RFC", "LOC");
        System.out.println("--------------------------------------------------------------------");

        try (PrintWriter pw = new PrintWriter(new FileWriter(outputFile))) {
            pw.println("Class,CBO,LCOM*,RFC,LOC,TopMethods");

            metricsMap.values().stream()
                    .filter(m -> m.cbo > 3 || m.lcomStar > 0.5)
                    .forEach(m -> {
                        // Mostrar en consola
                        System.out.printf("%-40s %-8.2f %-8.2f %-8.2f %-8.0f%n",
                                m.className, m.cbo, m.lcomStar, m.rfc, m.loc);

                        // Métodos más complejos
                        List<MethodMetrics> topMethods = m.methods.stream()
                                .filter(mm -> mm.loc > 1)
                                .sorted(Comparator.comparingDouble(mm -> -mm.loc))
                                .limit(3)
                                .toList();

                        topMethods.forEach(mm ->
                                System.out.printf("     🔸 %-30s LOC: %-4.0f RFC: %-4.0f WMC: %-4.0f%n",
                                        mm.methodName, mm.loc, mm.rfc, mm.wmc));

                        // Escribir al CSV resumen
                        String methodsSummary = topMethods.isEmpty() ? "N/A" :
                                String.join(" | ",
                                        topMethods.stream()
                                                .map(mm -> mm.methodName + " (LOC=" + (int) mm.loc + ")")
                                                .toList());

                        pw.printf("%s,%.2f,%.2f,%.2f,%.0f,%s%n",
                                m.className, m.cbo, m.lcomStar, m.rfc, m.loc, methodsSummary);
                    });

            System.out.println("\n✅ Resumen exportado a: " + outputFile);

        } catch (IOException e) {
            System.err.println("❌ Error escribiendo resumen: " + e.getMessage());
        }
    }

    // 📘 Lectura de class.csv
    private static void readClassMetrics(String file, Map<String, ClassMetrics> map) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine(); // encabezado
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if (data.length < 15) continue;

                String className = data[1].trim();
                ClassMetrics m = new ClassMetrics();
                m.className = className;
                m.cbo = parseDoubleSafe(data[3]);
                m.lcomStar = parseDoubleSafe(data[12]);
                m.rfc = parseDoubleSafe(data[10]);
                m.loc = parseDoubleSafe(data[33]);
                map.put(className, m);
            }
        } catch (IOException e) {
            System.err.println("❌ Error leyendo " + file + ": " + e.getMessage());
        }
    }

    // 📗 Lectura de method.csv
    private static void readMethodMetrics(String file, Map<String, ClassMetrics> map) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine(); // encabezado
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if (data.length < 13) continue;

                String className = data[1].trim();
                String methodName = data[2].trim();
                double wmc = parseDoubleSafe(data[9]);
                double rfc = parseDoubleSafe(data[10]);
                double loc = parseDoubleSafe(data[12]);

                ClassMetrics cm = map.get(className);
                if (cm != null) cm.methods.add(new MethodMetrics(methodName, wmc, rfc, loc));
            }
        } catch (IOException e) {
            System.err.println("❌ Error leyendo " + file + ": " + e.getMessage());
        }
    }

    private static double parseDoubleSafe(String value) {
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            return 0.0;
        }
    }
}
