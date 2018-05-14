import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.http.HttpService;
import process.ContractInput;
import process.Credentials;
import process.Survey;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static spark.Spark.get;
import static spark.Spark.post;

public class App {
    public static final HashMap<String, Object> datamodels = new HashMap<>();
    public static final double GAS_PRICE = 8;
    public static final String CONTRACT_ADDRESS = ""; // Avaiting details from Jasmine

    public static void main(String[] args) {
        get("/login", (request, response) ->
                process.HTMLtoString.loadHTML("html/ClientLogin.html")
        );

        post("/login", (request, response) -> {
            // Sample Publickey: 0x8dacfc01faf352953ff2ca80938647d30bb39b4e
            // Sample Privatekey: 7b172f463cc687dd91fda2ebe6e99b705ae0251d4dd3a920564adc3a4422a67a


            String publickey = request.queryParams("txt_publickey");
            String privatekey = request.queryParams("txt_privatekey");

            datamodels.put("credentials", new Credentials(publickey, privatekey));

            Admin web3j = Admin.build(new HttpService("https://ropsten.infura.io/bzGTMVNZDiJ9N7qv2oUb"));

            try {
                PersonalUnlockAccount personalUnlockAccount = web3j.personalUnlockAccount(publickey, privatekey).send();

                datamodels.put("web3j", web3j);
                datamodels.put("personalUnlockAccount", personalUnlockAccount);
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
                System.out.println("Unable to retrieve Personal Account");
            }

            return process.HTMLtoString.loadHTML("html/ClientSurvey.html");
        });

        get("/survey", (request, response) ->
                process.HTMLtoString.loadHTML("html/ClientSurvey.html")
        );

        post("/survey", (request, response) -> {
            String a, b, c, d, e;
            a = request.queryParams("q1");
            b = request.queryParams("q2");
            c = request.queryParams("q3");
            d = request.queryParams("q4");
            e = request.queryParams("q5");

            Survey survey = new Survey();
            survey.addQuestionResponse("How would you describe your overall level of job satisfaction?", a);
            survey.addQuestionResponse("Do your colleagues contribute to your level of satisfaction?", b);
            survey.addQuestionResponse("Are you satisfied by the physical working conditions?", c);
            survey.addQuestionResponse("Do you have the resources you need to do your job well?", d);
            survey.addQuestionResponse("Are sufficient efforts made to get opinions and feedback from the people who work here?", e);

            datamodels.put("survey", survey);

            for (ContractInput input : survey.getContractInput()) {
                processEtherTransaction("placeResponse", input.getInputsAsList(), null);
            }

            return process.HTMLtoString.loadHTML("html/ClientThankYou.html");
        });

    }

    /**
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     * @source https://docs.web3j.io/transactions.html
     */
    private static String processEtherTransaction(String contractFunction, List<Type> contractInputParameters, List<TypeReference<?>> contractOutputParameters) throws IOException, ExecutionException, InterruptedException {
        Credentials credentials = (Credentials) datamodels.get("credentials");
        Admin web3j = (Admin) (datamodels.get("web3j"));
        PersonalUnlockAccount personalUnlockAccount = (PersonalUnlockAccount) datamodels.get("personalUnlockAccount");

        if (personalUnlockAccount != null && personalUnlockAccount.accountUnlocked()) {
            try {
                Function function = new Function(
                        contractFunction,
                        contractInputParameters,
                        contractOutputParameters);

                String encodedFunction = FunctionEncoder.encode(function);
                Transaction transaction = Transaction.createFunctionCallTransaction(credentials.getPublickey(),
                        new BigInteger(GAS_PRICE + ""), new BigInteger((GAS_PRICE * 100) + ""),
                        new BigInteger(CONTRACT_ADDRESS), ((GAS_PRICE * 1000) + ""), encodedFunction);

                org.web3j.protocol.core.methods.response.EthSendTransaction transactionResponse =
                        web3j.ethSendTransaction(transaction).sendAsync().get();

                String transactionHash = transactionResponse.getTransactionHash();

                // wait for response using EthGetTransactionReceipt...

                return transactionHash;
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
                return "transaction failed";
            }
        }

        return "transaction failed";
    }
}

