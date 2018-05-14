package process;

public class Credentials {
    String publickey;
    String privatekey;

    public Credentials(String publickey, String privatekey) {
        this.publickey = publickey;
        this.privatekey = privatekey;
    }

    public String getPublickey() {
        return publickey;
    }


    public String getPrivatekey() {
        return privatekey;
    }

}
