public class Main {
    public static void main(String[] args) throws Exception {

        Reader reader = new Reader();

        System.out.println("\n Working Directory: " + System.getProperty("user.dir"));

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
                    PDA pda = new PDA(reader.getInitialState(), reader.getFinalState(), reader.getConditions());
                    pda.verify();

                break;
            }

            case "T":{

                    System.out.println("\n Initializing Turing Machine \n");
                    TM tm = new TM(reader.getInitialState(), reader.getFinalState(), reader.getConditions());
                    tm.verify();

                break;
            }
        
            default:
                break;
        }
        
    }
}
