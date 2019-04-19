package com.isttis2019.ex84kakaologintest;

import android.app.Application;
import android.content.Context;

import com.kakao.auth.ApprovalType;
import com.kakao.auth.AuthType;
import com.kakao.auth.IApplicationConfig;
import com.kakao.auth.ISessionConfig;
import com.kakao.auth.KakaoAdapter;
import com.kakao.auth.KakaoSDK;

public class GlobalApplication extends Application {

    //본인 참조변수값의 정적 변수
    static GlobalApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        if (instance==null) instance= this;
        //카카오 sdk와 앱의 applicatiojn객체 연결!! 초기화
        KakaoSDK.init(new KakaoSDKAdapter());


    }

    private static class KakaoSDKAdapter extends KakaoAdapter {
        @Override
        public ISessionConfig getSessionConfig() {
            return new ISessionConfig() {
                @Override
                public AuthType[] getAuthTypes() {
                    return new AuthType[] {AuthType.KAKAO_ACCOUNT};
                }

                @Override
                public boolean isUsingWebviewTimer() {
                    return false;
                }

                @Override
                public boolean isSecureMode() {
                    return false;
                }

                @Override
                public ApprovalType getApprovalType() {
                    return ApprovalType.INDIVIDUAL;
                }

                @Override
                public boolean isSaveFormData() {
                    return true;
                }
            };
        }

        //카카오SDK를 APPLication과 연결해주는 클래스 설계
        @Override
        public IApplicationConfig getApplicationConfig() {
            IApplicationConfig applicationConfig=new IApplicationConfig() {
                @Override
                public Context getApplicationContext() {
                    return instance;
                }
            };

            return applicationConfig;
        }
    }



}
