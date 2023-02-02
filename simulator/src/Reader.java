import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/* The machines Head */

public class Reader {

    private String machineType;
    private String initialState;
    private String[] finalState;
    private List<String[]> conditions = new ArrayList<String[]>();

    File specs = new File("specs.txt");

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
    


    /* Method to read the file and to define variables */
    public void readFile() throws IOException {

        Path pathFileSpecs = Paths.get("src/files/specs.txt");

        /* verify if the file exists */
        if(Files.exists(pathFileSpecs)){
            System.out.println("\n The file specs exists ! :) \n");
        }else{
            System.out.println("\n The file specs not exists ! :( \n");
            return;
        }

        List<String> lines = Files.readAllLines(pathFileSpecs);
        
        setMachineType(lines.get(0));
        setInitialState(lines.get(1));
        setFinalState(lines.get(2).split(","));

        List<String[]> conditionsAux = new ArrayList<String[]>(); 
        for(int i = 3; i < lines.size(); i++){
            conditionsAux.add(lines.get(i).split(","));
        }
        setConditions(conditionsAux);
            
    }

    public void writeFile(List<String> message) throws IOException{
        Path output = Paths.get("src/files/output.txt");
        Files.write(output, message);
        System.out.println("\n written output \n");
    }
}
