package encryption;

/**
 * Created by elroi on 2015-09-01.
 */
public interface Cypherer {

    String encrypt(String s);
    String decrypt(String s);

    String getEncryptionType();
    String getKey();
    String getPublicKey();

}
