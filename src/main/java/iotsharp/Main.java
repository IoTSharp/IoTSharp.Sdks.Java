package iotsharp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import iotsharp.models.*;
import iotsharp.models.result.DeviceResult;
import iotsharp.models.result.MyInfoResult;
import iotsharp.models.result.entities.Device;
import iotsharp.models.result.entities.DeviceIdentity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class Main {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String  BASE_URL="BASE_URL";
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
        LoginData result=   testRestTemplate.postForObject(BASE_URL+"/api/Account/Login",formEntity, LoginData.class);
        if(CommonResult.SUCCESSED.equals(result != null ? result.getData().getCode() : null)){
            //获取用户详情
            var token=result.getData().getToken() ;
            headers=new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Authorization","Bearer "+token.getAccess_token());
            HttpEntity<Object> entity = new HttpEntity<>(null, headers);
            var UserInfo = testRestTemplate.exchange(BASE_URL+"/api/Account/MyInfo", HttpMethod.GET,entity, MyInfoData.class);
            System.out.println(UserInfo.getBody().getData().getAppName());

            //新建设备
            var  createdeviceheaders = new HttpHeaders();
            createdeviceheaders.setContentType(MediaType.APPLICATION_JSON);
            createdeviceheaders.add("Authorization","Bearer "+token);
            ObjectNode createdeviceObject = objectMapper.createObjectNode();
            createdeviceObject.put("name","targetdevice");
            createdeviceObject.put("deviceType",DeviceType.DEVICE);
            HttpEntity<String>  createdeviceEntity = new HttpEntity<>(createdeviceObject.toString(), headers);
            var  createddevice=   testRestTemplate.postForObject(BASE_URL+"/api/Devices",createdeviceEntity, DevcieData.class);
            System.out.println(createddevice.getData().getId());

            //获取设备列表
            entity = new HttpEntity<>(null, headers);
            var devices = testRestTemplate.exchange(BASE_URL+"/api/Devices/Customers?customerId="+    UserInfo.getBody().getData().getCustomer().getId(), HttpMethod.GET,entity, DeviceListData.class);
            var _createddevice= Arrays.stream(devices.getBody().getData().getRows().toArray(new DeviceResult[0])).filter((x)->x.getId().equals(createddevice.getData().getId())).findFirst().orElse(null);

            //获取设备Token
            entity = new HttpEntity<>(null, headers);
            var deviceIdentity = testRestTemplate.exchange(BASE_URL+"/api/Devices/"+_createddevice.getId()+"/Identity", HttpMethod.GET,entity, DeviceIdentityData.class);
            System.out.println(deviceIdentity.getBody().getData().getIdentityId());


            //使用Token上传属性数据
            var  deviceattrheaders = new HttpHeaders();
            deviceattrheaders.setContentType(MediaType.APPLICATION_JSON);
            deviceattrheaders.add("Authorization","Bearer "+token);
            ObjectNode deviceattrObject = objectMapper.createObjectNode();
            deviceattrObject.put("additionalProp4","string");
            deviceattrObject.put("additionalProp5","string");
            deviceattrObject.put("additionalProp6","string");
            HttpEntity<String>  deviceattrEntity = new HttpEntity<>(deviceattrObject.toString(), headers);
            var resultdeviceattrEntity=   testRestTemplate.postForObject(BASE_URL+"/api/Devices/"+deviceIdentity.getBody().getData().getIdentityId()+"/Attributes",deviceattrEntity, String.class);

            //使用Token上传遥测数据
            var  deviceTelemetryheaders = new HttpHeaders();
            deviceTelemetryheaders.setContentType(MediaType.APPLICATION_JSON);
            deviceTelemetryheaders.add("Authorization","Bearer "+token);
            ObjectNode deviceTelemetryObject = objectMapper.createObjectNode();
            deviceTelemetryObject.put("additionalProp4","string");
            deviceTelemetryObject.put("additionalProp5","string");
            deviceTelemetryObject.put("additionalProp6","string");
            HttpEntity<String>  deviceTelemetryEntity = new HttpEntity<>(deviceTelemetryObject.toString(), headers);
            var resultdeviceTelemetryEntity=   testRestTemplate.postForObject(BASE_URL+"/api/Devices/"+deviceIdentity.getBody().getData().getIdentityId()+"/Telemetry",deviceTelemetryEntity, String.class);
            System.out.println(deviceTelemetryEntity);

        }






    }

    class DeviceType{
        public static final  String DEVICE="Device";
        public static final  String GETWAY="Gateway";

    }


}
