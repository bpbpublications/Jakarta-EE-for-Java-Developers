package net.rhuanrocha.samplecdi.beans;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import java.io.*;


public class MessageWriter implements Serializable {

    private File file;

    public MessageWriter(String path){
        file = new File(path);
    }

    public void add(String message){

        try(BufferedWriter outputStream = new BufferedWriter(new FileWriter(file,true))) {

          outputStream.write(message);
          outputStream.newLine();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clean(){
        file.delete();
    }


}
