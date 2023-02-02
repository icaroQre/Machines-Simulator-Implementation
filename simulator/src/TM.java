import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TM extends Reader{
    private String initialState;
    private String[] finalStates;
    private List<String[]> conditions;
    private List<String[]> inputFormatted;
    private List<String> message;
    private String output;
    private String state;

    public TM(String initialState, String[] finalStates, List<String[]> conditions) throws IOException{
        this.initialState = initialState;
        this.finalStates = finalStates;
        this.conditions = conditions;
        message= new ArrayList<String>();
        inputFormatted = new ArrayList<String[]>();
        this.readFile();
    }

    public String toString(String[] input){
        String list = Arrays.toString(input).replace("[", "").replace("]", "").replace(",", "").replace(" ", "");
        return list;
    }

    public void readInput() throws IOException{
        Path arquivo = Paths.get("src/files/input.txt");
        if(!Files.exists(arquivo)){
            System.out.println("the file input not exists");
        }
        List<String> input = Files.readAllLines(arquivo);
        for(int i  = 0; i < input.size(); i++ ){
            inputFormatted.add(input.get(i).concat("_").split(""));
        }
    }

    public void verify() throws IOException{
        String direction = "R";
        for(int i=0;i<this.inputFormatted.size();i++){
            int j=-1;
            String[] tape = inputFormatted.get(i);
            state = initialState;

            while(true){
                if(direction.equals("R")){
                    j++;
                }
                else if(direction.equals("L")){
                    j--;
                }

                if(j==tape.length){
                    break;
                }

                int achou=0;

                for(String[] aux:conditions){

                    String[] aux2 = aux[1].split(";");
                    if(state.equals(aux[0])){
    
                        if(tape[j].equals(aux2[0])){
                            tape[j] = aux2[1];
                            direction = aux2[2];
                            state=aux[2]; achou=1;
                            break;
                        }
                    }
                }
                if(achou==0){
                    state = "ERRO";
                    break;
                }
            } 
            
            for(String aux:this.finalStates){
                if(state.equals(aux)){
                    output = "A";
                    break;
                }
                else{
                    output="R";
                }
            }
            message.add(output+";"+toString(tape));   
        }

        writeFile(message);
        System.out.println("\n Finish \n");
    }
}
