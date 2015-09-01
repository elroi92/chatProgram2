package encryption;

/**
 * Created by elroi on 2015-09-01.
 */
public class clearTextPasser implements Cypherer {
    @Override
    public String encrypt(String s) {
        return s;
    }

    @Override
    public String decrypt(String s) {
        return s;
    }

    @Override
    public String getEncryptionType() {
        return null;
    }

    @Override
    public String getKey() {
        return null;
    }

    @Override
    public String getPublicKey() {
        return null;
    }
}
