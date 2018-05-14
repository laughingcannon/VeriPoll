package process;

import java.util.ArrayList;
import java.util.HashMap;

public class Survey {
    HashMap<String, String> responses;

    public Survey() {
        responses = new HashMap<>();
    }

    public void addQuestionResponse(String question, String response) {
        responses.put(question, response);
    }

    public String produceExportString() {
        String toReturn = "";

        for (String key : responses.keySet()) {
            toReturn += "(" + key + "$" + responses.get(key) + ")";
        }

        return toReturn;
    }

    public ArrayList<ContractInput> getContractInput() {
        ArrayList<ContractInput> toReturn = new ArrayList<>();

        int index = 0;
        for (String key : responses.keySet()) {
            toReturn.add(new ContractInput(index, responses.get(key)));
            index++;
        }

        return toReturn;
    }
}
