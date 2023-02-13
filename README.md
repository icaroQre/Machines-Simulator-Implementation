### Ícaro
### Oswaldo

# Machines Simulator Implementation ⚙️

Developed for the Theory of Conputation subject, the aplication is a Deterministic Finite Automaton, Pushdown Automaton and Turing Machine simulator.

<br/>

Using the programming language Java to manipulate files🍕


## Operation
  
  <p>
  The software reads the file "/files/specs.txt" for select a machine and its settings, in sequence reads the file "/files/input.txt" where are the inputs for the         machine to process.
  </p>
  <p>
  After this an file named "output.txt" is generated, this file contains the outputs of the machine selected.
  </p>
  <p>
  To use, write in files of input and specs what you want.
  </p>
  
  
### specs.m

1 X (type of machine [T/P/F])
2 I (initial state (number))
3 F1,F2,F3 (final states (number))
4 F,COND,T
5 ()
...
N ()


### input.in

1 abababababab
2 bbbbbbababababa
3 abababababbaaaaa
4 abababab


### output.out
  (S = Accept/Reject)
1 S;tape
2 A;bbbbbbababababa
3 R;abababababbaaaaa
4 R;abababab
