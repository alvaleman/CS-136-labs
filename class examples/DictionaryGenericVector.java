
import structure5.*;

/* 
 * A simple dictionary demonstrating
 * Vectors and Associations.
 */
class Dictionary {
    protected Vector< Association<String,String> > defs;

    public Dictionary() {
        defs = new Vector();
    }

    public void addWord(String word, String def) {
        defs.add(new Association<String, String>(word, def));
    }

    // post: returns the definition of word, or "" if not found.
    public String lookup(String word) {
        for (int i = 0; i < defs.size(); i++) {
            Association<String,String> a = defs.get(i);
            if (a.getKey().equals(word)) {
                return a.getValue();  
            }
        }
        return "";
    }

    // A method to print the defs of the words from the command line.
    public static void main(String args[]) {
        Dictionary dict = new Dictionary();

        dict.addWord("perception", "Awareness of an object of thought");
        dict.addWord("person", "An individual capable of moral agency");
        dict.addWord("pessimism", "Belief that things generally happen for the worst");
        dict.addWord("philosophy", "Literally, love of wisdom.");
        dict.addWord("premise", "A statement whose truth is used to infer that of others");

        for (int i = 0; i < args.length; i++) {
            String answer = dict.lookup(args[i]);
            System.out.println(args[i] + ": " + answer);
        }
    }
}

