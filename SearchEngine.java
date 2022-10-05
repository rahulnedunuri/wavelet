import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;


class SearchEngine{
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    int num = 0;
    ArrayList<String> list = new ArrayList<>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("Enter /add query into search bar then use /search to search for added items: ", list.toString());
        //} else if (url.getPath().equals("/increment")) {
            // num += 1;
            //return String.format("Number incremented!");
        } else if (url.getPath().contains("/add")){
            System.out.println("Path: " + url.getPath());
            
            String[] parameters = url.getQuery().split("=");
            list.add(parameters[1]);
            return ("Now contains: " + list.toString());

            } else if(url.getPath().contains("/search")){
                System.out.println("Path: " + url.getPath());

                String[] parameters = url.getQuery().split("=");
                String output = "results: ";
                for(String s : list){
                    if(s.contains(parameters[1])) {
                        output = output + " " + s;
                    }
                }
                return output;
            }
            return "Invalid url/entry";
        } 

        
    }

