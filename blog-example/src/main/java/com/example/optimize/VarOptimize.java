package com.example.optimize;

/**
 * 局部变量性能评测
 */
public class VarOptimize {
    char[] myChars = ("Oracle Cloud Infrastructure Low data networking fees and " +
            "automated migration Oracle Cloud Infrastructure platform is built for " +
            "enterprises that are looking for higher performance computing with easy " +
            "migration of their on-premises applications to the Cloud.").toCharArray();

    public static void main(String[] args) {
        VarOptimize varOptimize = new VarOptimize();
        varOptimize.globalVarTest();
        varOptimize.localityVarTest();
    }

    public void globalVarTest() {
        int cCount = 0;
        for (int i = 0; i < myChars.length; i++) {
            if (myChars[i] == 'c') {
                cCount++;
            }
        }
    }

    public void localityVarTest() {
        char[] localityChars = myChars;
        int cCount = 0;
        for (int i = 0; i < localityChars.length; i++) {
            if (localityChars[i] == 'c') {
                cCount++;
            }
        }
    }

}
