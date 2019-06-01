package bhz.oneday.backcall;

public class Worker {

    public void doWork() {
        Fetcher fetcher = new MyFetcher(new Data(1, 1));

        /**
         * 这里是被回调时
         * FetcherCallback 是一个接口,直接通过匿名类实现;
         */
        fetcher.fetchData(new FetcherCallback() {
            @Override
            public void onError(Throwable cause) {
                System.out.println("我被回调了(出错): " + cause.getMessage());
            }

            @Override
            public void onData(Data data) {
                System.out.println("我被回调了(正常): " + data);
            }
        });
    }

    /**
     * 普通回调还有基于future的回调在子包里
     */
    public static void main(String[] args) {
        Worker w = new Worker();
        w.doWork();
    }

}