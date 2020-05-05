package com.example.optimize;


/**
 * Switch 优化
 */
public class SwitchOptimize {
    static Integer _NUM = 1;

    public static void main(String[] args) {
        switchTest();
        ifTest();
    }

    public static void switchTest() {
        int num1;
        switch (_NUM) {
            case 1:
                num1 = 1;
                break;
            case 3:
                num1 = 3;
                break;
            case 5:
                num1 = 5;
                break;
            case 7:
                num1 = 7;
                break;
            case 9:
                num1 = 9;
                break;
            default:
                num1 = -1;
                break;
        }
    }


    public static void ifTest() {
        int num1;
        if (_NUM == 1) {
            num1 = 1;
        } else if (_NUM == 3) {
            num1 = 3;
        } else if (_NUM == 5) {
            num1 = 5;
        } else if (_NUM == 7) {
            num1 = 7;
        } else if (_NUM == 9) {
            num1 = 9;
        } else {
            num1 = -1;
        }
    }
}
