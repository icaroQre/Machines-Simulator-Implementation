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
    private String direction;

    public TM(String initialState, String[] finalStates, List<String[]> conditions) throws IOException{
        this.initialState = initialState;
        this.finalStates = finalStates;
        this.conditions = conditions;
        message= new ArrayList<String>();
        inputFormatted = new ArrayList<String[]>();
        this.direction = "R";
    }

    public String toString(String[] input){
        String list = Arrays.toString(input).replace("[", "").replace("]", "").replace(",", "").replace(" ", "");
        return list;
    }
    
    public void verify() throws IOException{

        Path arquivo = Paths.get(System.getProperty("user.dir") + "/src/files/input.txt");
        if(!Files.exists(arquivo)){
            System.out.println("Não existe");
        }
        List<String> input = Files.readAllLines(arquivo);
        for(int i  = 0; i < input.size(); i++ ){
            inputFormatted.add(input.get(i).concat("_").split(""));
        }

        for(int i=0;i<this.inputFormatted.size();i++){
            int tapePosition=-1;
            String[] tape = inputFormatted.get(i);
            state = initialState;

            while(true){
                
                Integer find = 0;

                if(direction.equals("R")){
                    tapePosition++;
                }
                else if(direction.equals("L")){
                    tapePosition--;
                }

                if(tapePosition==tape.length){
                    break;
                }

                for(String[] aux:conditions){

                    String[] aux2 = aux[1].split(";");
                    if(state.equals(aux[0])){

                        if(tape[tapePosition].equals(aux2[0])){
                            tape[tapePosition] = aux2[1];
                            direction = aux2[2];
                            state=aux[2];                            
                            find=1;
                        }
                    }
                }

                if(find==0){
                    state = "ERRO";
                    break;
                }
            } 

            for (String finalAux : this.finalStates) {
                
                if (state.equals(finalAux)) {
                    output = "A";
                    break;
                }else {
                    output = "R";
                }
            } 

            message.add(output+ ";" + toString(tape)); 

        }

        writeFile(message);
        System.out.println("\n Finish");
    }
}
