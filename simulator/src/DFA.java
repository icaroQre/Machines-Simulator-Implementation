import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DFA extends Reader{

    private String initialStateDFA;
    private String[] finalStatesDFA;
    private List<String[]> conditionsDFA;
    private List<String[]> inputFormatted = new ArrayList<String[]>();
    private String state;
    private List<String> message= new ArrayList<String>();
    private String output;

    public DFA(String initialState, String[] finalStates, List<String[]> conditions) throws IOException{

        initialStateDFA = initialState;
        finalStatesDFA = finalStates;
        conditionsDFA = conditions;

    }

    /* Initial State */
    public String getInitialState() {
        return this.initialStateDFA;
    }

    public void setInitialState(String initialStateDFA) {
        this.initialStateDFA = initialStateDFA;
    }

    /* Final State */
    public String[] getFinalState() {
        return this.finalStatesDFA;
    }

    public void setFinalStateDFA(String[] finalStateDFA) {
        this.finalStatesDFA = finalStateDFA;
    }

    /* Conditions */
    public List<String[]> getConditions() {
        return this.conditionsDFA;
    }

    public void setConditions(List<String[]> conditionsDFA) {
        this.conditionsDFA = conditionsDFA;
    }

    public void readInput() throws IOException {

        Path path = Paths.get("src/files/input.txt");

        /* verify if the file exists */
        if(Files.exists(path)){
            System.out.println("\n The file input exists ! :) \n");
        }else{
            System.out.println("\n The file input not exists ! :( \n");
        }

        /* read all input and formats it to a list */
        List<String> input = Files.readAllLines(path);

        for(int i  = 0; i < input.size(); i++ ){
            inputFormatted.add(input.get(i).split(""));
        }
    }


    public void verify() throws IOException {

        Path path = Paths.get("src/files/input.txt");
        List<String> fileInput = Files.readAllLines(path);

        for(int i=0;i<this.inputFormatted.size();i++){
            int j=0;

            for(String input:inputFormatted.get(i)){
                
                if(j==0){
                    state = initialStateDFA;
                }

                int find = 0;
                for(String[] aux:conditionsDFA){

                    if(state.equals(aux[0])){
                        
                        if(input.equals(aux[1])){
                            find=1;
                            state = aux[2];
                            break;
                        }
                    }
                }
                if(find==0){
                    state = "ERRO";
                    break;
                }
                j++;
            } 
            
            for(String aux:this.finalStatesDFA){
                if(state.equals(aux)){
                    output = "A";
                    break;
                }
                else{
                    output="R";
                }
            }
            message.add(output+";"+fileInput.get(i));
        }

        writeFile(message);
        System.out.println("\n Finish \n ");
    }
}