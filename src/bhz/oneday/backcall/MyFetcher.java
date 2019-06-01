package bhz.oneday.backcall;

public class MyFetcher implements Fetcher {

    final Data data;

    public MyFetcher(Data data) {
        this.data = data;
    }

    /**
     * 这里发起回调
     */
    @Override
    public void fetchData(FetcherCallback callback) {
        try {
            callback.onData(data);
        } catch (Exception e) {
            callback.onError(e);
        }
    }

}