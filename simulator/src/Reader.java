import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* The machines Head */

public class Reader {

    private String machineType;
    private String initialState;
    private String[] finalState;
    private List<String[]> conditions = new ArrayList<String[]>();

    File specs = new File("input.txt");

    /* Variables getters and setters */
    public File getSpecs() {
        return this.specs;
    }

    public void setSpecs(File specs) {
        this.specs = specs;
    }

    /* Machine Type */
    public String getMachineType() {
        return this.machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    /* Initial State */
    public String getInitialState() {
        return this.initialState;
    }

    public void setInitialState(String initialState) {
        this.initialState = initialState;
    }

    /* Final State */
    public String[] getFinalState() {
        return this.finalState;
    }

    public void setFinalState(String[] finalState) {
        this.finalState = finalState;
    }

    /* Conditions */
    public List<String[]> getConditions() {
        return this.conditions;
    }

    public void setConditions(List<String[]> conditions) {
        this.conditions = conditions;
    }
    

    /* Method to verify if the file exists */
    public void fileExists(Path path){

        if(Files.exists(path)){
            System.out.println("\n O arquivo existe! :) \n");
        }else{
            System.out.println("\n O arquivo não existe! :( \n");
        }

    }


    /* Method to read the file and to define variables */
    public void readFile() throws IOException {

        Path pathFileSpecs = Paths.get("C:/Users/icaro/OneDrive/Área de Trabalho/Machines/simulator/input.txt");
        List<String> lines = Files.readAllLines(pathFileSpecs);

        fileExists(pathFileSpecs);
        
        setMachineType(lines.get(0));
        setInitialState(lines.get(1));
        setFinalState(lines.get(2).split(","));

        List<String[]> conditionsAux = new ArrayList<String[]>(); 
        for(int i = 3; i < lines.size(); i++){
            conditionsAux.add(lines.get(i).split(","));
        }
        setConditions(conditionsAux);

        System.out.println(getMachineType());
        System.out.println(getInitialState());
        System.out.println(Arrays.toString(getFinalState()));
        getConditions().forEach(element -> System.out.println(Arrays.toString(element)));
            
    }
}
