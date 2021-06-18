package iotsharp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import iotsharp.models.DeviceIdentity;
import iotsharp.models.UserInfo;
import iotsharp.models.UserInfoResult;
import iotsharp.models.UserResult;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String  BASE_URL="http://localhost:2927";
    public static void main(String[] args) {
        System.out.println("this is Demo");
        //SignIn      登录
        RestTemplate testRestTemplate = new RestTemplate();
        var  headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ObjectNode SignInObject = objectMapper.createObjectNode();
        SignInObject.put("password","Wq*1234/wq");
        SignInObject.put("userName","wq1234wq@163.com");
        HttpEntity<String> formEntity = new HttpEntity<String>(SignInObject.toString(), headers);
        var result=   testRestTemplate.postForObject(BASE_URL+"/api/Account/Login",formEntity, UserResult.class);
        if(CommonResult.SUCCESSED.equals(result.getCode())){
            //获取用户详情
            String token=result.getToken().getAccess_token();
            headers=new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Authorization","Bearer "+token);
            HttpEntity<Object> entity = new HttpEntity<Object>(null, headers);
            var UserInfo = testRestTemplate.exchange(BASE_URL+"/api/Account/MyInfo", HttpMethod.GET,entity, UserInfoResult.class);
            //获取设备列表
            var devices = testRestTemplate.exchange(BASE_URL+"/api/Devices/Customers/"+  UserInfo.getBody().getData().getCustomer().getId(), HttpMethod.GET,entity, String.class);
            //获取设备Token
            String DeviceId="febb19a8-265c-4064-bb23-9062a1044e41";
             //  DeviceId=    Arrays.stream(devices).map((x)->x.getData().getId()).filter((x)->x.equals("The DeviceId you want")).findFirst();
            var deviceIdentity = testRestTemplate.exchange(BASE_URL+"/api/Devices/"+DeviceId+"/Identity", HttpMethod.GET,entity, DeviceIdentity.class);
            //上传遥测数据
            var  deviceTelemetryheaders = new HttpHeaders();
            deviceTelemetryheaders.setContentType(MediaType.APPLICATION_JSON);
            deviceTelemetryheaders.add("Authorization","Bearer "+token);
            ObjectNode deviceTelemetryObject = objectMapper.createObjectNode();
            deviceTelemetryObject.put("additionalPropx","string");
            deviceTelemetryObject.put("additionalPropy","string");
            deviceTelemetryObject.put("additionalPropz","string");
            HttpEntity<String>  deviceTelemetryEntity = new HttpEntity<String>(SignInObject.toString(), headers);
            var resultdeviceTelemetryEntity=   testRestTemplate.postForObject(BASE_URL+"/api/Devices/"+deviceIdentity.getBody().getIdentityId()+"/Telemetry",deviceTelemetryEntity, String.class);
            System.out.println(resultdeviceTelemetryEntity.toString());

        } else{

        }




    }
}
