import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PDA extends Reader{
    
    private String initialStateAP;
    private String[] finalStatesAP;
    private List<String[]> conditionsAP;
    private List<String[]> inputFormatted = new ArrayList<String[]>();
    private List<String> message= new ArrayList<String>();
    Stack<String> stack = new Stack<String>();
    private String output;
    private String state;

    public PDA(String initialState, String[] finalStates, List<String[]> conditions) throws IOException{
        this.initialStateAP = initialState;
        this.finalStatesAP = finalStates;
        this.conditionsAP = conditions;
        this.readInput();
    }

    public void readInput() throws IOException{
        Path file = Paths.get("src/files/input.txt");
        if(!Files.exists(file)){
            System.out.println("The file not exist");
        }
        List<String> input = Files.readAllLines(file);
        for(int i  = 0; i < input.size(); i++ ){

            inputFormatted.add(input.get(i).concat("_").split(""));
        }
    }

    static String stack_peek(Stack<String> stack)
    {
        if(stack.empty()){
            return "_";
        }else{

            String element = (String) stack.peek();
            return element;

        }

    }

    public void verify() throws IOException{
        Path file = Paths.get("src/files/input.txt");
        List<String> fileInput = Files.readAllLines(file);

        for(int i=0;i<this.inputFormatted.size();i++){
            
            int j=0;
            stack.clear();
            stack.push("Z");
            
            for(String input:inputFormatted.get(i)){

                if(j==0){                   
                    state=initialStateAP;
                }

                int find=0;
                for(String[] aux:conditionsAP){

                    String[] aux2 = aux[1].split(";");
                    if(state.equals(aux[0])){
                        
                        if (input.equals(aux2[0])){
                            
                            String top = stack_peek(stack);

                            int pop=0;

                            if(aux2[1].equals("_")){
                                pop=1;
                            }

                            else if(top.equals(aux2[1])){
                                stack.pop(); 
                                pop=1;
                            }

                            if(pop==1 && aux2[2].equals("_")){
                                state = aux[2];
                                find=1;
                                break;
                            }
                            else if(pop==1){
                                stack.push(aux2[2]);
                                state = aux[2];
                                find=1;
                                break;
                            }
                        }
                    }
                }
                
                if(find==0){
                    state = "ERRO";
                    break;
                }
                System.out.println(state);
                j++;
            } 
            
            for(String aux:this.finalStatesAP){
                System.out.println(state);
                if(state.equals(aux) && stack.empty()){
                    output = "A";
                    break;
                }
                else{
                    output="R";
                }
            }
            message.add(output+";"+fileInput.get(i));
        }

        writeFile( message);
        System.out.println("\n Finish \n");
    }
}