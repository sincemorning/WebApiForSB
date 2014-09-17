/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.*;
import java.util.*;
/**
 * 外部ファイルにエラーを出力します
 * @author takumi.hoshi
 */
public class ErrorFileWriter {
    /**
     * コンストラクタ
     */
    public ErrorFileWriter() {
        
    } // ErrorFileWriter()
    
    /**
     * ログファイルにメッセージを吐き出す
     * @param errMsg 
     */
    public void stackTrace(String errMsg) {
        Calendar now = Calendar.getInstance();
        //now.get(now.YEAR)
        //now.get(now.MONTH) + 1
        //now.get(now.DATE)
        File f = new File("Log_");
        if(f.canWrite()) {
            // ファイルを書き込める場合
        }
        
    }
} // class ErrorFileWriter
