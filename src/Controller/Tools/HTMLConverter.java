package Controller.Tools;

public class HTMLConverter {

    public String convertToHTML(String data){
        return data.replace("\n", "<br>");
    }
}
