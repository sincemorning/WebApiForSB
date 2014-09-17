/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

/**
 * プロパティファイルの読み込み
 *
 * @author Takumi
 */
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {

    public String read(String key) {
        final Properties prop = new Properties();
        InputStream inStream = null;
        try {
            inStream = new BufferedInputStream(
                    new FileInputStream("properties/prop.properties"));
            prop.load(inStream);
        } catch (IOException e) {
            // TODO:エラーログへの出力処理を記載
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (inStream != null) {
                    inStream.close();
                }
            } catch (IOException e) {
                // TODO:エラーログへの出力処理を記載
                e.printStackTrace();
                return null;
            }
        }
        return prop.getProperty(key);
    }
}
