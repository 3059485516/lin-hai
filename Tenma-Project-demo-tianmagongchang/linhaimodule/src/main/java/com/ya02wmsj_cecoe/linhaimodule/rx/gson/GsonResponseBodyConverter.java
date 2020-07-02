package com.ya02wmsj_cecoe.linhaimodule.rx.gson;

import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.socks.library.KLog;
import com.ya02wmsj_cecoe.linhaimodule.bean.HttpResult;
import com.ya02wmsj_cecoe.linhaimodule.rx.HttpCode;
import com.ya02wmsj_cecoe.linhaimodule.rx.exception.ApiException;
import com.ya02wmsj_cecoe.linhaimodule.rx.exception.JsonException;
import com.ya02wmsj_cecoe.linhaimodule.rx.exception.TokenInvalidException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;


final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, Object> {
    private final TypeAdapter<T> adapter;

    GsonResponseBodyConverter(TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override
    public Object convert(ResponseBody value) {
        try {
            String json = new String(value.bytes());
            KLog.json("json----------", json);
            JSONObject jsonObject = new JSONObject(json);
            String code = jsonObject.optString("code");
            String desc = jsonObject.optString("desc");
            if (HttpCode.SUCCESS_CODE.equals(code) || HttpCode.SIGN_SUCCESS_CODE.equals(code) || HttpCode.SIGN_AM_REPEAT_SIGN.equals(code)) {
                HttpResult h = (HttpResult) adapter.fromJson(json);
                if (h.getResultValue() == null) {
                    //throw new ApiException(HttpCode.CODE_30002.getCode());
                    return "";
                } else {
                    return h.getResultValue();
                }
            } else if (HttpCode.TOKEN_CODE.equals(code)) {
                throw new TokenInvalidException();
            } else {
                throw new ApiException(code);
            }
        } catch (IOException e) {
            throw new ApiException(HttpCode.CODE_30002.getCode());
        } catch (JSONException e) {
            throw new ApiException(HttpCode.CODE_30002.getCode());
        } catch (JsonSyntaxException e) {
            throw new JsonException(HttpCode.CODE_30003.getCode());
        } finally {
            if (value != null) {
                value.close();
            }
        }
    }
}
