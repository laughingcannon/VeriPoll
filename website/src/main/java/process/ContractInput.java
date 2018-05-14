package process;

import org.web3j.abi.datatypes.Type;

import java.util.ArrayList;
import java.util.List;

public class ContractInput {
    int questionIndex;
    int responseIndex;

    public ContractInput(int questionIndex, String response) {
        this.questionIndex = questionIndex;

        if (response.equalsIgnoreCase("yes"))
            responseIndex = 1;
        else
            responseIndex = 0;
    }

    public List<Type> getInputsAsList() {
        ArrayList<Type> toReturn = new ArrayList<>();
        toReturn.add(new Response(questionIndex, Response.TYPE_QUESTION));
        toReturn.add(new Response(responseIndex, Response.TYPE_RESPONSE));

        return toReturn;
    }

    class Response implements Type<Integer> {
        static final int TYPE_QUESTION = 10;
        static final int TYPE_RESPONSE = 20;
        int indexData;
        int type;


        public Response(int indexData, int type) {
            this.indexData = indexData;
            this.type = type;
        }

        @Override
        public Integer getValue() {
            return indexData;
        }

        @Override
        public String getTypeAsString() {
            return indexData + "";
        }
    }
}
