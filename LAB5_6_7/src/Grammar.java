import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Grammar {
 public List<String> nonTerminals;
 public List<String> terminals;
 public String startingSymbol;
 public Map<String, ArrayList<List<String>>> productionRules;

 public Grammar(){
     this.productionRules = new HashMap<>();
 }
    public void readGrammar(String fileName) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            nonTerminals = getStatesFromLine(br);
            terminals = getStatesFromLine(br);
            startingSymbol = getStatesFromLine(br).get(0);
            br.readLine();
            while ((line = br.readLine()) != null) {
                //lineList will always have 2 elements
                //on the first position the key, and on the  second position the rest->a list
                List<String> lineList = Arrays.asList(line.split("->"));
                String key =lineList.get(0).strip();
                ArrayList<List<String>> value = new ArrayList<>();
                String[] token = lineList.get(1).split("\\|");
                for(var str:token){
                    List<String> prod = Arrays.asList(str.strip().split(" "));
                    value.add(prod);
                }
                productionRules.put(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private List<String> getStatesFromLine(BufferedReader br) throws IOException {
        String line = br.readLine();
        List <String> list =  Arrays.asList(line.split(" "));
        return list.subList(2, list.size());
    }
}
