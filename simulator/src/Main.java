public class Main {
    public static void main(String[] args) throws Exception {

        Reader reader = new Reader();

        reader.readFile();

        switch (reader.getMachineType()) {
            case "F":{

                    System.out.println("\n Initializing Deterministic Finite Automaton \n");
                    DFA dfa = new DFA(reader.getInitialState(), reader.getFinalState(), reader.getConditions());
                    dfa.readInput();
                    dfa.verify();

                break;
            }

            case "P":{

                    System.out.println("\n Initializing Pushdown Automaton \n");
                    PDA tm = new PDA(reader.getInitialState(), reader.getFinalState(), reader.getConditions());
                    tm.verify();

                break;
            }

            case "T":{

                    System.out.println("\n Initializing Turing Machine \n");
                    TM machine = new TM(reader.getInitialState(), reader.getFinalState(), reader.getConditions());
                    machine.verify();
                
                break;
            }
        
            default:
                break;
        }
        
    }
}
