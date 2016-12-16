package com.cmiot.hoa.api.resource;

import com.alibaba.fastjson.JSONObject;
import com.cmiot.hoa.api.resource.order.MethodExecutionOrder;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * Created by ZJL on 2016/9/7.
 */
@Path("public")
public class PBossResource extends BaseResource {
    public static Logger logger = LoggerFactory.getLogger(PBossResource.class);

    /**
     * 业务开通接口
     *
     * @param jsonText
     * @return
     */
    @POST
    @Path("/common/order/dealOrder")
    @Produces(MediaType.APPLICATION_JSON)
    public Response dealOrder(@Context ChannelHandlerContext ctx, String jsonText) {
        final String gid = newGid();
        logger.info("【GID={}】业务开通接口，原始请求入参:{}", gid, jsonText);

        String serviceName = "workOrderStandardInterface";
        String methodName = "addNewInstallation";

        JSONObject jsonObject = null;
        if (StringUtils.isBlank(jsonText)) {
            return Response.ok(buildMap(70010, "参数不能为空")).build();
        }
        try {
            jsonObject = JSONObject.parseObject(jsonText);
        } catch (Exception e) {
            return Response.ok(buildMap(70011, "参数格式非法")).build();
        }

        Map<String, Object> resultObjectMap = methodExecution(new MethodExecutionOrder(gid, ctx, serviceName, methodName, jsonObject));
        return Response.ok(resultObjectMap).build();
    }

    /**
     * 查询设备信息
     *
     * @param deviceType
     * @param jsonText
     * @return
     */
    @POST
    @Path("{deviceType}/query/equInfo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response equInfo(@Context ChannelHandlerContext ctx, @PathParam("deviceType") String deviceType, String jsonText) {
        final String gid = newGid();
        logger.info("【GID={}】查询设备信息，原始请求入参:{}", gid, jsonText);

        String serviceName = null;
        String methodName = null;
        if ("hgu".equals(deviceType)) {
            serviceName = "dataQueryService";
            methodName = "queryHguEquipmentInfo";
        } else if ("ihgu".equals(deviceType)) {
            serviceName = "dataQueryService";
            methodName = "queryIhguEquipmentInfo";
        } else if ("ott".equals(deviceType) || "iptv".equals(deviceType)) {
            serviceName = "dataQueryService";
            methodName = "queryStbEquipmentInfo";
        } else {
            return Response.ok(buildMap(70020, "不支持的设备类型")).build();
        }

        JSONObject jsonObject = null;
        if (StringUtils.isBlank(jsonText)) {
            return Response.ok(buildMap(70010, "参数不能为空")).build();
        }
        try {
            jsonObject = JSONObject.parseObject(jsonText);
        } catch (Exception e) {
            return Response.ok(buildMap(70011, "参数格式非法")).build();
        }

        Map<String, Object> resultObjectMap = methodExecution(new MethodExecutionOrder(gid, ctx, serviceName, methodName, jsonObject));
        return Response.ok(resultObjectMap).build();
    }


    /**
     * 查询设备业务状态
     *
     * @param deviceType
     * @param jsonText
     * @return
     */
    @POST
    @Path("{deviceType}/query/servInfo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response servInfo(@Context ChannelHandlerContext ctx, @PathParam("deviceType") String deviceType, String jsonText) {
        final String gid = newGid();
        logger.info("【GID={}】查询设备业务状态，原始请求入参:{}", gid, jsonText);

        String serviceName = null;
        String methodName = null;
        if ("hgu".equals(deviceType) || "ihgu".equals(deviceType)) {
            serviceName = "dataQueryService";
            methodName = "queryHguBusinessStatus";
        } else {
            return Response.ok(buildMap(70020, "不支持的设备类型")).build();
        }

        JSONObject jsonObject = null;
        if (StringUtils.isBlank(jsonText)) {
            return Response.ok(buildMap(70010, "参数不能为空")).build();
        }
        try {
            jsonObject = JSONObject.parseObject(jsonText);
        } catch (Exception e) {
            return Response.ok(buildMap(70011, "参数格式非法")).build();
        }

        Map<String, Object> resultObjectMap = methodExecution(new MethodExecutionOrder(gid, ctx, serviceName, methodName, jsonObject));
        return Response.ok(resultObjectMap).build();
    }


    /**
     * 查询DNS地址和拨号错误码
     *
     * @param deviceType
     * @param jsonText
     * @return
     */
    @POST
    @Path("{deviceType}/query/dnsdhcpInfo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response dnsdhcpInfo(@Context ChannelHandlerContext ctx, @PathParam("deviceType") String deviceType, String jsonText) {
        final String gid = newGid();
        logger.info("【GID={}】查询DNS地址和拨号错误码，原始请求入参:{}", gid, jsonText);

        String serviceName = null;
        String methodName = null;
        if ("hgu".equals(deviceType) || "ihgu".equals(deviceType)) {
            serviceName = "dataQueryService";
            methodName = "queryHguDnsdhcpInfo";
        } else {
            return Response.ok(buildMap(70020, "不支持的设备类型")).build();
        }

        JSONObject jsonObject = null;
        if (StringUtils.isBlank(jsonText)) {
            return Response.ok(buildMap(70010, "参数不能为空")).build();
        }
        try {
            jsonObject = JSONObject.parseObject(jsonText);
        } catch (Exception e) {
            return Response.ok(buildMap(70011, "参数格式非法")).build();
        }

        Map<String, Object> resultObjectMap = methodExecution(new MethodExecutionOrder(gid, ctx, serviceName, methodName, jsonObject));
        return Response.ok(resultObjectMap).build();
    }


    /**
     * 告警查询
     *
     * @param deviceType
     * @param jsonText
     * @return
     */
    @POST
    @Path("{deviceType}/query/alarminfo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response alarminfo(@Context ChannelHandlerContext ctx, @PathParam("deviceType") String deviceType, String jsonText) {
        final String gid = newGid();
        logger.info("【GID={}】告警查询，原始请求入参:{}", gid, jsonText);

        String serviceName = null;
        String methodName = null;
        if ("hgu".equals(deviceType) || "ihgu".equals(deviceType)) {
            serviceName = "dataQueryService";
            methodName = "queryHguAlarminfo";
        } else {
            return Response.ok(buildMap(70020, "不支持的设备类型")).build();
        }

        JSONObject jsonObject = null;
        if (StringUtils.isBlank(jsonText)) {
            return Response.ok(buildMap(70010, "参数不能为空")).build();
        }
        try {
            jsonObject = JSONObject.parseObject(jsonText);
        } catch (Exception e) {
            return Response.ok(buildMap(70011, "参数格式非法")).build();
        }

        Map<String, Object> resultObjectMap = methodExecution(new MethodExecutionOrder(gid, ctx, serviceName, methodName, jsonObject));
        return Response.ok(resultObjectMap).build();
    }

    /**
     * 查询LAN口性能
     *
     * @param deviceType
     * @param jsonText
     * @return
     */
    @POST
    @Path("{deviceType}/query/lanInfo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response lanInfo(@Context ChannelHandlerContext ctx, @PathParam("deviceType") String deviceType, String jsonText) {
        final String gid = newGid();
        logger.info("【GID={}】查询LAN口性能，原始请求入参:{}", gid, jsonText);

        String serviceName = null;
        String methodName = null;
        if ("hgu".equals(deviceType) || "ihgu".equals(deviceType)) {
            serviceName = "dataQueryService";
            methodName = "queryHguLanPerformanceInfo";
        } else {
            return Response.ok(buildMap(70020, "不支持的设备类型")).build();
        }

        JSONObject jsonObject = null;
        if (StringUtils.isBlank(jsonText)) {
            return Response.ok(buildMap(70010, "参数不能为空")).build();
        }
        try {
            jsonObject = JSONObject.parseObject(jsonText);
        } catch (Exception e) {
            return Response.ok(buildMap(70011, "参数格式非法")).build();
        }

        Map<String, Object> resultObjectMap = methodExecution(new MethodExecutionOrder(gid, ctx, serviceName, methodName, jsonObject));
        return Response.ok(resultObjectMap).build();
    }


    /**
     * 设置WIFI开启
     *
     * @param deviceType
     * @param jsonText
     * @return
     */
    @POST
    @Path("{deviceType}/setting/enableSSID")
    @Produces(MediaType.APPLICATION_JSON)
    public Response enableSSID(@Context ChannelHandlerContext ctx, @PathParam("deviceType") String deviceType, String jsonText) {
        final String gid = newGid();
        logger.info("【GID={}】设置WIFI开启，原始请求入参:{}", gid, jsonText);

        String serviceName = "deviceSettingService";
        String methodName = "enableSSID";

        JSONObject jsonObject = null;
        if (StringUtils.isBlank(jsonText)) {
            return Response.ok(buildMap(70010, "参数不能为空")).build();
        }
        try {
            jsonObject = JSONObject.parseObject(jsonText);
        } catch (Exception e) {
            return Response.ok(buildMap(70011, "参数格式非法")).build();
        }

        Map<String, Object> resultObjectMap = methodExecution(new MethodExecutionOrder(gid, ctx, serviceName, methodName, jsonObject));
        return Response.ok(resultObjectMap).build();
    }

    /**
     * 设置WIFI关闭
     *
     * @param deviceType
     * @param jsonText
     * @return
     */
    @POST
    @Path("{deviceType}/setting/disableSSID")
    @Produces(MediaType.APPLICATION_JSON)
    public Response disableSSID(@Context ChannelHandlerContext ctx, @PathParam("deviceType") String deviceType, String jsonText) {
        final String gid = newGid();
        logger.info("【GID={}】设置WIFI关闭，原始请求入参:{}", gid, jsonText);

        String serviceName = "deviceSettingService";
        String methodName = "disableSSID";

        JSONObject jsonObject = null;
        if (StringUtils.isBlank(jsonText)) {
            return Response.ok(buildMap(70010, "参数不能为空")).build();
        }
        try {
            jsonObject = JSONObject.parseObject(jsonText);
        } catch (Exception e) {
            return Response.ok(buildMap(70011, "参数格式非法")).build();
        }

        Map<String, Object> resultObjectMap = methodExecution(new MethodExecutionOrder(gid, ctx, serviceName, methodName, jsonObject));
        return Response.ok(resultObjectMap).build();
    }

    /**
     * 修改SSID密码
     *
     * @param deviceType
     * @param jsonText
     * @return
     */
    @POST
    @Path("{deviceType}/setting/modifySSIDPwd")
    @Produces(MediaType.APPLICATION_JSON)
    public Response modifySSIDPwd(@Context ChannelHandlerContext ctx, @PathParam("deviceType") String deviceType, String jsonText) {
        final String gid = newGid();
        logger.info("【GID={}】修改SSID密码，原始请求入参:{}", gid, jsonText);

        String serviceName = "deviceSettingService";
        String methodName = "modifySSIDPwd";

        JSONObject jsonObject = null;
        if (StringUtils.isBlank(jsonText)) {
            return Response.ok(buildMap(70010, "参数不能为空")).build();
        }
        try {
            jsonObject = JSONObject.parseObject(jsonText);
        } catch (Exception e) {
            return Response.ok(buildMap(70011, "参数格式非法")).build();
        }

        Map<String, Object> resultObjectMap = methodExecution(new MethodExecutionOrder(gid, ctx, serviceName, methodName, jsonObject));
        return Response.ok(resultObjectMap).build();
    }

    /**
     * 恢复出厂设置
     *
     * @param deviceType
     * @param jsonText
     * @return
     */
    @POST
    @Path("{deviceType}/setting/factoryReset")
    @Produces(MediaType.APPLICATION_JSON)
    public Response factoryReset(@Context ChannelHandlerContext ctx, @PathParam("deviceType") String deviceType, String jsonText) {
        final String gid = newGid();
        logger.info("【GID={}】恢复出厂设置，原始请求入参:{}", gid, jsonText);

        String serviceName = null;
        String methodName = null;
        if ("hgu".equals(deviceType) || "ihgu".equals(deviceType)) {
            serviceName = "deviceSettingService";
            methodName = "factoryReset";
        } else if (deviceType.equalsIgnoreCase("ott") || deviceType.equalsIgnoreCase("iptv")) {
            serviceName = "deviceSettingService";
            methodName = "boxFactoryReset";
        } else {
            return Response.ok(buildMap(70020, "不支持的设备类型")).build();
        }

        JSONObject jsonObject = null;
        if (StringUtils.isBlank(jsonText)) {
            return Response.ok(buildMap(70010, "参数不能为空")).build();
        }
        try {
            jsonObject = JSONObject.parseObject(jsonText);
        } catch (Exception e) {
            return Response.ok(buildMap(70011, "参数格式非法")).build();
        }

        Map<String, Object> resultObjectMap = methodExecution(new MethodExecutionOrder(gid, ctx, serviceName, methodName, jsonObject));
        return Response.ok(resultObjectMap).build();
    }


    /**
     * 重启设备
     *
     * @param deviceType
     * @param jsonText
     * @return
     */
    @POST
    @Path("{deviceType}/setting/reboot")
    @Produces(MediaType.APPLICATION_JSON)
    public Response reboot(@Context ChannelHandlerContext ctx, @PathParam("deviceType") String deviceType, String jsonText) {
        final String gid = newGid();
        logger.info("【GID={}】重启设备，原始请求入参:{}", gid, jsonText);

        String serviceName = null;
        String methodName = null;
        if ("hgu".equals(deviceType) || "ihgu".equals(deviceType)) {
            serviceName = "deviceSettingService";
            methodName = "reboot";
        } else if (deviceType.equalsIgnoreCase("ott") || deviceType.equalsIgnoreCase("iptv")) {
            serviceName = "deviceSettingService";
            methodName = "boxReboot";
        } else {
            return Response.ok(buildMap(70020, "不支持的设备类型")).build();
        }

        JSONObject jsonObject = null;
        if (StringUtils.isBlank(jsonText)) {
            return Response.ok(buildMap(70010, "参数不能为空")).build();
        }
        try {
            jsonObject = JSONObject.parseObject(jsonText);
        } catch (Exception e) {
            return Response.ok(buildMap(70011, "参数格式非法")).build();
        }

        Map<String, Object> resultObjectMap = methodExecution(new MethodExecutionOrder(gid, ctx, serviceName, methodName, jsonObject));
        return Response.ok(resultObjectMap).build();
    }

    /**
     * 账号密码重置
     *
     * @param deviceType
     * @param jsonText
     * @return
     */
    @POST
    @Path("{deviceType}/setting/resetpppoe")
    @Produces(MediaType.APPLICATION_JSON)
    public Response resetpppoe(@Context ChannelHandlerContext ctx, @PathParam("deviceType") String deviceType, String jsonText) {
        final String gid = newGid();
        logger.info("【GID={}】账号密码重置，原始请求入参:{}", gid, jsonText);

        String serviceName = "deviceSettingService";
        String methodName = "resetpppoe";

        JSONObject jsonObject = null;
        if (StringUtils.isBlank(jsonText)) {
            return Response.ok(buildMap(70010, "参数不能为空")).build();
        }
        try {
            jsonObject = JSONObject.parseObject(jsonText);
        } catch (Exception e) {
            return Response.ok(buildMap(70011, "参数格式非法")).build();
        }

        Map<String, Object> resultObjectMap = methodExecution(new MethodExecutionOrder(gid, ctx, serviceName, methodName, jsonObject));
        return Response.ok(resultObjectMap).build();
    }

    /**
     * PING测试
     *
     * @param deviceType
     * @param jsonText
     * @return
     */
    @POST
    @Path("{deviceType}/testing/ping")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ping(@Context ChannelHandlerContext ctx, @PathParam("deviceType") String deviceType, String jsonText) {
        final String gid = newGid();
        logger.info("【GID={}】PING测试，原始请求入参:{}", gid, jsonText);

        String serviceName = null;
        String methodName = null;
        if ("hgu".equalsIgnoreCase(deviceType)) {
            serviceName = "deviceDiagnoseService";
            methodName = "pingHguDiagnose";
        } else if ("stb".equalsIgnoreCase(deviceType)) {
            serviceName = "deviceDiagnoseService";
            methodName = "pingStbDiagnose";
        } else {
            return Response.ok(buildMap(70020, "不支持的设备类型")).build();
        }

        JSONObject jsonObject = null;
        if (StringUtils.isBlank(jsonText)) {
            return Response.ok(buildMap(70010, "参数不能为空")).build();
        }
        try {
            jsonObject = JSONObject.parseObject(jsonText);
        } catch (Exception e) {
            return Response.ok(buildMap(70011, "参数格式非法")).build();
        }

        Map<String, Object> resultObjectMap = methodExecution(new MethodExecutionOrder(gid, ctx, serviceName, methodName, jsonObject));
        return Response.ok(resultObjectMap).build();
    }


    /**
     * PING测试结果
     *
     * @param deviceType
     * @param jsonText
     * @return
     */
    @POST
    @Path("{deviceType}/testing/ping/getpinginfo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPingInfo(@Context ChannelHandlerContext ctx, @PathParam("deviceType") String deviceType, String jsonText) {
        final String gid = newGid();
        logger.info("【GID={}】PING测试结果，原始请求入参:{}", gid, jsonText);

        String serviceName = null;
        String methodName = null;
        if ("hgu".equalsIgnoreCase(deviceType)) {
            serviceName = "deviceDiagnoseService";
            methodName = "getHguPingInfo";
        } else if ("stb".equalsIgnoreCase(deviceType)) {
            serviceName = "deviceDiagnoseService";
            methodName = "getStbPingInfo";
        } else {
            return Response.ok(buildMap(70020, "不支持的设备类型")).build();
        }

        JSONObject jsonObject = null;
        if (StringUtils.isBlank(jsonText)) {
            return Response.ok(buildMap(70010, "参数不能为空")).build();
        }
        try {
            jsonObject = JSONObject.parseObject(jsonText);
        } catch (Exception e) {
            return Response.ok(buildMap(70011, "参数格式非法")).build();
        }

        Map<String, Object> resultObjectMap = methodExecution(new MethodExecutionOrder(gid, ctx, serviceName, methodName, jsonObject));
        return Response.ok(resultObjectMap).build();
    }


    /**
     * PPPOE仿真测试
     *
     * @param deviceType
     * @param jsonText
     * @return
     */
    @POST
    @Path("{deviceType}/testing/pppoediag")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pppoediag(@Context ChannelHandlerContext ctx, @PathParam("deviceType") String deviceType, String jsonText) {
        Map<String, Object> results = null;
        final String gid = newGid();
        logger.info("【GID={}】PPPOE仿真测试，原始请求入参:{}", gid, jsonText);

        String serviceName = null;
        String methodName = null;
        if ("hgu".equalsIgnoreCase(deviceType)) {
            serviceName = "deviceDiagnoseService";
            methodName = "pppoeHguDiagnose";
        } else {
            return Response.ok(buildMap(70020, "不支持的设备类型")).build();
        }

        JSONObject jsonObject = null;
        if (StringUtils.isBlank(jsonText)) {
            return Response.ok(buildMap(70010, "参数不能为空")).build();
        }
        try {
            jsonObject = JSONObject.parseObject(jsonText);
        } catch (Exception e) {
            return Response.ok(buildMap(70011, "参数格式非法")).build();
        }

        Map<String, Object> resultObjectMap = methodExecution(new MethodExecutionOrder(gid, ctx, serviceName, methodName, jsonObject));
        return Response.ok(resultObjectMap).build();
    }


    /**
     * PPPOE仿真测试结果
     *
     * @param deviceType
     * @param jsonText
     * @return
     */
    @POST
    @Path("{deviceType}/testing/pppoediag/getpppoeinfo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPppoediagInfo(@Context ChannelHandlerContext ctx, @PathParam("deviceType") String deviceType, String jsonText) {
        final String gid = newGid();
        logger.info("【GID={}】PPPOE仿真测试结果，原始请求入参:{}", gid, jsonText);

        String serviceName = null;
        String methodName = null;
        if ("hgu".equalsIgnoreCase(deviceType)) {
            serviceName = "deviceDiagnoseService";
            methodName = "getHguPppoeInfo";
        } else {
            return Response.ok(buildMap(70020, "不支持的设备类型")).build();
        }

        JSONObject jsonObject = null;
        if (StringUtils.isBlank(jsonText)) {
            return Response.ok(buildMap(70010, "参数不能为空")).build();
        }
        try {
            jsonObject = JSONObject.parseObject(jsonText);
        } catch (Exception e) {
            return Response.ok(buildMap(70011, "参数格式非法")).build();
        }

        Map<String, Object> resultObjectMap = methodExecution(new MethodExecutionOrder(gid, ctx, serviceName, methodName, jsonObject));
        return Response.ok(resultObjectMap).build();
    }

    /**
     * Traceroute诊断测试
     *
     * @param deviceType
     * @param jsonText
     * @return
     */
    @POST
    @Path("{deviceType}/testing/traceroute")
    @Produces(MediaType.APPLICATION_JSON)
    public Response traceroute(@Context ChannelHandlerContext ctx, @PathParam("deviceType") String deviceType, String jsonText) {
        final String gid = newGid();
        logger.info("【GID={}】Traceroute诊断测试，原始请求入参:{}", gid, jsonText);

        String serviceName = null;
        String methodName = null;
        if ("hgu".equalsIgnoreCase(deviceType)) {
            serviceName = "deviceDiagnoseService";
            methodName = "tracerouteHguDiagnose";
        } else if ("stb".equalsIgnoreCase(deviceType)) {
            serviceName = "deviceDiagnoseService";
            methodName = "tracerouteStbDiagnose";
        } else {
            return Response.ok(buildMap(70020, "不支持的设备类型")).build();
        }

        JSONObject jsonObject = null;
        if (StringUtils.isBlank(jsonText)) {
            return Response.ok(buildMap(70010, "参数不能为空")).build();
        }
        try {
            jsonObject = JSONObject.parseObject(jsonText);
        } catch (Exception e) {
            return Response.ok(buildMap(70011, "参数格式非法")).build();
        }

        Map<String, Object> resultObjectMap = methodExecution(new MethodExecutionOrder(gid, ctx, serviceName, methodName, jsonObject));
        return Response.ok(resultObjectMap).build();
    }

    /**
     * Traceroute诊断测试结果
     *
     * @param deviceType
     * @param jsonText
     * @return
     */
    @POST
    @Path("{deviceType}/testing/traceroute/gettrinfo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracerouterInfo(@Context ChannelHandlerContext ctx, @PathParam("deviceType") String deviceType, String jsonText) {
        final String gid = newGid();
        logger.info("【GID={}】Traceroute诊断测试结果，原始请求入参:{}", gid, jsonText);

        String serviceName = null;
        String methodName = null;
        if ("hgu".equalsIgnoreCase(deviceType)) {
            serviceName = "deviceDiagnoseService";
            methodName = "getHguTracerouteInfo";
        } else if ("stb".equalsIgnoreCase(deviceType)) {
            serviceName = "deviceDiagnoseService";
            methodName = "getStbTracerouteInfo";
        } else {
            return Response.ok(buildMap(70020, "不支持的设备类型")).build();
        }

        JSONObject jsonObject = null;
        if (StringUtils.isBlank(jsonText)) {
            return Response.ok(buildMap(70010, "参数不能为空")).build();
        }
        try {
            jsonObject = JSONObject.parseObject(jsonText);
        } catch (Exception e) {
            return Response.ok(buildMap(70011, "参数格式非法")).build();
        }

        Map<String, Object> resultObjectMap = methodExecution(new MethodExecutionOrder(gid, ctx, serviceName, methodName, jsonObject));
        return Response.ok(resultObjectMap).build();
    }
}
