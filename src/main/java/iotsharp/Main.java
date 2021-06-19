package iotsharp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import iotsharp.models.*;
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
    private static final String  BASE_URL="http://APIURL";
    public static void main(String[] args) {
        System.out.println("this is Demo");
        //SignIn      登录
        RestTemplate testRestTemplate = new RestTemplate();
        var  headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ObjectNode SignInObject = objectMapper.createObjectNode();
        SignInObject.put("password","password");
        SignInObject.put("userName","userName");
        HttpEntity<String> formEntity = new HttpEntity<>(SignInObject.toString(), headers);
        var result=   testRestTemplate.postForObject(BASE_URL+"/api/Account/Login",formEntity, UserResult.class);
        if(CommonResult.SUCCESSED.equals(result != null ? result.getCode() : null)){
            //获取用户详情
            String token=result.getToken().getAccess_token();
            headers=new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Authorization","Bearer "+token);
            HttpEntity<Object> entity = new HttpEntity<>(null, headers);
            var UserInfo = testRestTemplate.exchange(BASE_URL+"/api/Account/MyInfo", HttpMethod.GET,entity, UserInfoResult.class);

            //新建设备
            var  createdeviceheaders = new HttpHeaders();
            createdeviceheaders.setContentType(MediaType.APPLICATION_JSON);
            createdeviceheaders.add("Authorization","Bearer "+token);
            ObjectNode createdeviceObject = objectMapper.createObjectNode();
            createdeviceObject.put("name","targetdevice");
            createdeviceObject.put("deviceType",DeviceType.DEVICE.ordinal());
            HttpEntity<String>  createdeviceEntity = new HttpEntity<>(createdeviceObject.toString(), headers);
            var  createddevice=   testRestTemplate.postForObject(BASE_URL+"/api/Devices",createdeviceEntity, Device.class);

            //获取设备列表
            entity = new HttpEntity<>(null, headers);
            var devices = testRestTemplate.exchange(BASE_URL+"/api/Devices/Customers/"+  UserInfo.getBody().getData().getCustomer().getId(), HttpMethod.GET,entity, Device[].class);
            var _createddevice= Arrays.stream(devices.getBody()).filter((x)->x.getId().equals(createddevice.getId())).findFirst().orElse(null);

            //获取设备Token
            entity = new HttpEntity<>(null, headers);
            var deviceIdentity = testRestTemplate.exchange(BASE_URL+"/api/Devices/"+_createddevice.getId()+"/Identity", HttpMethod.GET,entity, DeviceIdentity.class);

            //使用Token上传遥测数据
            var  deviceTelemetryheaders = new HttpHeaders();
            deviceTelemetryheaders.setContentType(MediaType.APPLICATION_JSON);
            deviceTelemetryheaders.add("Authorization","Bearer "+token);
            ObjectNode deviceTelemetryObject = objectMapper.createObjectNode();
            deviceTelemetryObject.put("additionalProp4","string");
            deviceTelemetryObject.put("additionalProp5","string");
            deviceTelemetryObject.put("additionalProp6","string");
            HttpEntity<String>  deviceTelemetryEntity = new HttpEntity<>(deviceTelemetryObject.toString(), headers);
            var resultdeviceTelemetryEntity=   testRestTemplate.postForObject(BASE_URL+"/api/Devices/"+deviceIdentity.getBody().getIdentityId()+"/Telemetry",deviceTelemetryEntity, String.class);
            System.out.println(deviceTelemetryEntity);

        }






    }

    enum DeviceType{
        DEVICE, GETWAY
    }


}
