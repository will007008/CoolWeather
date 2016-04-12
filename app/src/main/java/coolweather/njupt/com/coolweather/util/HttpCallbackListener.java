package coolweather.njupt.com.coolweather.util;

/**
 * Created by DragonWarrior on 2016/4/12.
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
