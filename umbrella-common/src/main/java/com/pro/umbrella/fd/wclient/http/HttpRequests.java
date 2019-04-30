package com.pro.umbrella.fd.wclient.http;

import com.google.common.util.concurrent.ListenableFuture;
import com.pro.umbrella.common.util.function.VoidFunction;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NoHttpResponseException;
import org.apache.http.conn.ConnectTimeoutException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * AHC Http Client
 */
@Slf4j
@Service
public class HttpRequests {

    @Resource
    private HttpClient<Response> httpClient;

    /**
     * 包含重试功能的http post请求
     * 默认重试3次 间隔10秒
     */
    public Response retryPost(String url, Map<String, String> params, Function<Response, Boolean> condition) throws IOException {
        return retryPost(url, params, condition, null, null);
    }

    /**
     * 包含重试功能的http post请求
     * 默认重试3次 间隔10秒
     */
    public Response retryPost(String url, Map<String, String> params, Function<Response, Boolean> condition, Integer retryTimes, Integer retryIntervalInMs) throws IOException {
        if (retryTimes == null || retryTimes < 0) {
            retryTimes = 3;
        }
        if (retryIntervalInMs == null || retryIntervalInMs < 0) {
            retryIntervalInMs = 10000;
        }
        if (condition == null) {
            condition = (res) -> true;
        }
        while (true) {
            try {
                Response response = this.syncPost(url, params);
                if (retryTimes-- == 0 || condition.apply(response)) {
                    return response;
                } else {
                    try {
                        Thread.sleep(retryIntervalInMs);
                    } catch (InterruptedException ignore) {
                    }
                }
            } catch (ConnectTimeoutException | NoHttpResponseException | SocketTimeoutException e) {
                if (retryTimes-- == 0) {
                    throw e;
                }
                System.out.println("[Http重试, 原因:{"+e.getClass().getSimpleName()+"}]即将重试Http请求：{}"+ url);
            }
        }
    }

    /**
     * 包含重试功能的http请求
     */
    public Response retryPostJson(String url, String json, int retryTimes) throws IOException {
        if (retryTimes < 0) {
            retryTimes = 3;
        }
        while (true) {
            try {
                return this.syncPostJson(url, json);
            } catch (ConnectTimeoutException | NoHttpResponseException | SocketTimeoutException e) {
                if (retryTimes-- == 0) {
                    throw e;
                }
                System.out.println("[Http重试, 原因:{"+e.getClass().getSimpleName()+"}]即将重试Http请求：{}"+  url);
            }
        }
    }

    public Response syncGet(String url) throws IOException {
        Request request = Request.Builder.get().setUrl(url).build();
        return syncRequest(request);
    }

    public Response syncGet(String url, Map<String, String> params) throws IOException {
        Request.Builder builder = Request.Builder.get().setUrl(url);
        if (params != null && !params.isEmpty()) {
            List<Map.Entry<String, String>> entrys = new ArrayList<>(params.entrySet());
            builder.addQueryParams(entrys);
        }
        return syncRequest(builder.build());
    }

    public Response syncPost(String url) throws IOException {
        Request.Builder builder = Request.Builder.post().setUrl(url);
        return syncRequest(builder.build());
    }

    public Response syncPost(String url, Map<String, String> params) throws IOException {
        Request.Builder builder = Request.Builder.post().setUrl(url);
        if (params != null && !params.isEmpty()) {
            List<Map.Entry<String, String>> entrys = new ArrayList<>(params.entrySet());
            builder.addQueryParams(entrys);
        }
        return syncRequest(builder.build());
    }

    public Response syncPost(String url, String data) throws IOException {
        Request.Builder builder = Request.Builder.post().setUrl(url);
        if (StringUtils.isNotBlank(data)) {
            builder.setBody(data);
        }
        return syncRequest(builder.build());
    }

    public Response syncPostJson(String url, String json) throws IOException {
        Request.Builder builder = Request.Builder.post().setUrl(url)
                .setHeader("Content-Type", "application/json; charset=UTF-8");
        if (StringUtils.isNotBlank(json)) {
            builder.setBody(json);
        }
        return syncRequest(builder.build());
    }

    public Response syncPost(String url, byte[] body) throws IOException {
        Request.Builder builder = Request.Builder.post().setUrl(url);
        if (body != null) {
            builder.setBody(body);
        }
        return syncRequest(builder.build());
    }

    public ListenableFuture<Response> asyncGet(String url, VoidFunction<Response> success, VoidFunction<Throwable> error) {
        Request request = Request.Builder.get().setUrl(url).build();
        return asyncRequest(request, success, error);
    }

    public ListenableFuture<Response> asyncGet(String url, Map<String, String> params, VoidFunction<Response> success, VoidFunction<Throwable> error) {
        Request.Builder builder = Request.Builder.get().setUrl(url);
        if (params != null && !params.isEmpty()) {
            List<Map.Entry<String, String>> entrys = new ArrayList<>(params.entrySet());
            builder.addQueryParams(entrys);
        }
        Request request = builder.build();
        return asyncRequest(request, success, error);
    }

    public ListenableFuture<Response> asyncPost(String url, VoidFunction<Response> success, VoidFunction<Throwable> error) {
        Request.Builder builder = Request.Builder.post().setUrl(url);
        return asyncRequest(builder.build(), success, error);
    }

    public ListenableFuture<Response> asyncPost(String url, Map<String, String> params, VoidFunction<Response> success, VoidFunction<Throwable> error) {
        Request.Builder builder = Request.Builder.post().setUrl(url);
        if (params != null && !params.isEmpty()) {
            List<Map.Entry<String, String>> entrys = new ArrayList<>(params.entrySet());
            builder.addQueryParams(entrys);
        }
        return asyncRequest(builder.build(), success, error);
    }

    public ListenableFuture<Response> asyncPost(String url, String body, VoidFunction<Response> success, VoidFunction<Throwable> error) {
        Request.Builder builder = Request.Builder.post().setUrl(url);
        if (StringUtils.isNotBlank(body)) {
            builder.setBody(body);
        }
        return asyncRequest(builder.build(), success, error);
    }

    public ListenableFuture<Response> asyncPostJson(String url, String json, VoidFunction<Response> success, VoidFunction<Throwable> error) {
        Request.Builder builder = Request.Builder.post().setUrl(url)
                .setHeader("Content-Type", "application/json; charset=UTF-8");
        if (StringUtils.isNotBlank(json)) {
            builder.setBody(json);
        }
        return asyncRequest(builder.build(), success, error);
    }

    public ListenableFuture<Response> asyncPost(String url, byte[] body, VoidFunction<Response> success, VoidFunction<Throwable> error) {
        Request.Builder builder = Request.Builder.post().setUrl(url);
        if (body != null) {
            builder.setBody(body);
        }
        return asyncRequest(builder.build(), success, error);
    }

    private Response syncRequest(Request request) throws IOException {
        Invoker<Response> invoker = httpClient.newInvoker(request);
        return invoker.invoke();
    }

    private ListenableFuture<Response> asyncRequest(Request request, VoidFunction<Response> success, VoidFunction<Throwable> error) {
        Invoker<Response> invoker = httpClient.newInvoker(request);
        return invoker.enqueue(new InvokeHandler<Response, Response>() {
            @Override
            public Response onCompleted(Response response) throws Exception {
                if (success != null) {
                    success.apply(response);
                }
                return response;
            }

            @Override
            public void onThrowable(Throwable t) {
                if (error != null) {
                    error.apply(t);
                }
            }
        });
    }
}
