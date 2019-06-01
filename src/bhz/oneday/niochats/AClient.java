package bhz.oneday.niochats;

import java.io.IOException;

public class AClient {

    public static void main(String[] args)
            throws IOException {
        new NioClient().start("AClient");
        System.out.println("1111111111");
    }

}
